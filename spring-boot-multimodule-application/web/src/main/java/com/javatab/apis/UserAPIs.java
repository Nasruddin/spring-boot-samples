package com.javatab.apis;

import com.javatab.entities.User;
import com.javatab.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by nasir on 12/1/16.
 */
@RestController
public class UserAPIs {

    private UserService userService;

    @Autowired
    public UserAPIs(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.GET)
    protected String createUsers() {
        User user = new User();
        user.setName("Basir");
        user.setEmail("nasruddin.java3@gmail.com");
        user.setPassword("password");

        userService.CreateUser(user);
        return "Success";
    }

    @RequestMapping(value = "/get-users", method = RequestMethod.GET)
    protected List<User> getAllUsers() {

        List<User> listOfUsers = userService.getAllUsers();

        List<User> filteredUsers =
                                    listOfUsers.stream()
                                            .filter(user -> user.getName().startsWith("N"))
                                            .collect(Collectors.toList());

        return filteredUsers;
    }
}
