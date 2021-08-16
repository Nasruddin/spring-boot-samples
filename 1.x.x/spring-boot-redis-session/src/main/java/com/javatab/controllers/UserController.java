package com.javatab.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nasir on 23/12/15.
 */
@RestController
public class UserController {

    @RequestMapping("/api/users")
    public String authorized() {
        return "Hello Secured World";
    }
}
