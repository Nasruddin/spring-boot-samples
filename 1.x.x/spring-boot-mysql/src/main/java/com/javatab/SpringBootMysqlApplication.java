package com.javatab;

import com.javatab.model.User;
import com.javatab.repository.UserRepository;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootMysqlApplication implements CommandLineRunner{

	@Autowired
	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMysqlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Stream.of("Dyser", "Josh", "Phil")
				.forEach(user -> repository.save(new User(user)));

		repository.findAll().forEach(System.out::println);
	}
}
