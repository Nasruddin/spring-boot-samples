package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.stream.Stream;

@EnableDiscoveryClient
@EnableBinding (Sink.class)
@SpringBootApplication
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}
}

@MessageEndpoint
class ReservationProcessor {

	@ServiceActivator (inputChannel = "input")
	public void acceptNewReservation(String rn) {
		this.reservationRepository.save(new Reservation(rn));
	}

	private final ReservationRepository reservationRepository;

	@Autowired
	public ReservationProcessor(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}
}

@Component
class DummyCLR implements CommandLineRunner {

	private final ReservationRepository reservationRepository;

	@Autowired
	public DummyCLR(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

		Stream.of( "Alfyn" , "Fahim" , "Asmit" , "Vikas" , "Feroz" )
				.forEach( n -> reservationRepository.save(new Reservation(n)));

		reservationRepository.findAll().forEach(System.out::println);
	}
}

@RestController
@RefreshScope
class MessageRestController {

	@Value("${message}")
	private String message;

	@RequestMapping ("/message")
	String read() {
		return  this.message;
	}
}

@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long> {

}

@Entity
class Reservation {

	@Id
	@GeneratedValue
	private Long id;
	private String reservationName;

	Reservation() {}

	public Reservation(String reservationName) {
		this.reservationName = reservationName;
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"id=" + id +
				", reservationName='" + reservationName + '\'' +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
}
