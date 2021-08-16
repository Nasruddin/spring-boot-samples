package com.javatab.service;

import com.javatab.domain.User;
import com.javatab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nasir on 22/12/15.
 */
@Service
@Transactional
public class UserServiceImpl  implements UserService{

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> searchUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> searchUserByAge(int age) {
        return userRepository.findByAge(age);
    }
}
