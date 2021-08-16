package com.javatab.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Starter!
 *
 */
@SpringBootApplication
public class Application
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class, args);
    }
}

@RestController
class Hello {
    @RequestMapping("/")
    public String sayHello() {
        return "You are good";
    }
}