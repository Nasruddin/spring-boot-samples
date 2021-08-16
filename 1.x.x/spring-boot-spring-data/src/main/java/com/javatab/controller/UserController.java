package com.javatab.controller;

import com.javatab.domain.User;
import com.javatab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by nasir on 22/12/15.
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/add/{name}/{age}", method = RequestMethod.GET)
    public User addUser(@PathVariable String name, @PathVariable int age) {

        User user = new User();
        user.setName(name);
        user.setAge(age);

        userService.saveUser(user);

        return user;
    }

    @RequestMapping(value = "/search/name/{name}", method = RequestMethod.GET)
    public List<User> getUserByName(@PathVariable String name) {
        return userService.searchUserByName(name);
    }

    @RequestMapping(value = "/search/age/{age}", method = RequestMethod.GET)
    public List<User> getUserByAge(@PathVariable int age) {
        return userService.searchUserByAge(age);
    }
}
