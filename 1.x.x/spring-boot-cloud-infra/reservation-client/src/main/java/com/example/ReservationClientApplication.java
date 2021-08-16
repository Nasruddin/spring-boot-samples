package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@EnableZuulProxy
@EnableBinding (Source.class)
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class ReservationClientApplication {

	@Bean @LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ReservationClientApplication.class, args);
	}
}

@RestController
@RequestMapping("/reservations")
class ReservationApiGatewayController {

	private final RestTemplate restTemplate;

	@Autowired
	public ReservationApiGatewayController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Collection<String> fallback() {
		return new ArrayList<>();
	}

	@Autowired
	private Source outputChannelSource;

	@RequestMapping ( method = RequestMethod.POST)
	public void write (@RequestBody Reservation r) {
		MessageChannel channel = this.outputChannelSource.output();
		channel.send(
				MessageBuilder.withPayload( r.getReservationName() ).build()
		);

	}

	@HystrixCommand (fallbackMethod = "fallback")


	@RequestMapping(method = RequestMethod.GET, value = "/names")
	public Collection<String> names() {

		return this.restTemplate.exchange("http://reservation-service/reservations",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Resources<Reservation>>() {
				})
				.getBody()
				.getContent()
				.stream()
				.map(Reservation::getReservationName)
				.collect(Collectors.toList());
	}
}

class Reservation {

	private String reservationName ;

	public String getReservationName() {
		return reservationName;
	}
}
