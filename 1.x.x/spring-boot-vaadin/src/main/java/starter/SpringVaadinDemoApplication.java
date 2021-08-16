package starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringVaadinDemoApplication {

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringVaadinDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeData() {
        return args -> {

            customerRepository.deleteAll();
            customerRepository.save(new Customer("Sachin", "Tendulkar"));

            customerRepository.findAll().forEach(System.out::println);
        };
    }
}
