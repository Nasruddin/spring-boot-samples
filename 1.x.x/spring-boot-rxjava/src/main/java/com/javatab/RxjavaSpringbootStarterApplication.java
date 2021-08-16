package com.javatab;

import com.javatab.examples.Example1;
import com.javatab.examples.Example2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rx.Observable;
import rx.Subscriber;

@SpringBootApplication
public class RxjavaSpringbootStarterApplication implements CommandLineRunner{

	@Autowired private Example1 example1;
	@Autowired private Example2 example2;

	public static void main(String[] args) {
		SpringApplication.run(RxjavaSpringbootStarterApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		example1.main(strings);
		example2.main(strings);

	}
}
