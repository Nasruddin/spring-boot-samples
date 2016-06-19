package com.javatab.service;

import com.javatab.domain.User;

import java.util.List;


/**
 * Created by nasir on 22/12/15.
 */
public interface UserService {

    void saveUser(User user);
    List<User> searchUserByName(String name);
    List<User> searchUserByAge(int age);

}
