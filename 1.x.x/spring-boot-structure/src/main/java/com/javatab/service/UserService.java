package com.javatab.service;

import com.javatab.model.User;

import java.util.List;

/**
 * Created by nasruddin on 18/6/16.
 */
public interface UserService {

    User save(User user);
    List<User> getAllUsers();
}
