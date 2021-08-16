package org.javatab;

import org.javatab.domain.User;
import org.javatab.domain.UserRole;
import org.javatab.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

@SpringBootApplication
public class SpringbootStatelessAuthApplication {

    @Bean
    public InitializingBean insertDefaultUsers() {
        return new InitializingBean() {

            @Autowired
            private UserRepository userRepository;

            @Override
            public void afterPropertiesSet() {
                addUser("admin", "admin");
                addUser("user", "user");
                addUser("nasir", "password");
            }

            private void addUser(String username, String password) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(new BCryptPasswordEncoder().encode(password));
                user.grantRole(username.equals("admin") ? UserRole.ADMIN : UserRole.USER);
                userRepository.save(user);
            }
        };
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringbootStatelessAuthApplication.class, args);
	}
}
