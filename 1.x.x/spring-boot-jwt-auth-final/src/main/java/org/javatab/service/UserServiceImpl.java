package org.javatab.service;

import org.javatab.domain.User;
import org.javatab.domain.UserRole;
import org.javatab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nasir on 29/1/16.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(String username, String password) {

        User aUser = new User();
        aUser.setUsername(username);
        aUser.setPassword(new BCryptPasswordEncoder().encode(password));
        aUser.grantRole(username.equals("admin") ? UserRole.ADMIN : UserRole.USER);
        userRepository.save(aUser);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
