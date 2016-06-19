package org.javatab.controller;

import org.javatab.auth.StatelessAuthenticationFilter;
import org.javatab.domain.User;
import org.javatab.domain.UserRole;
import org.javatab.repository.UserRepository;
import org.javatab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nasir on 28/1/16.
 */
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/api/register",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUser(@RequestBody User user) {

        userService.addUser(user.getUsername(), user.getPassword());

        return new ResponseEntity<>("User Registered", HttpStatus.OK);
    }

    @RequestMapping(value = "/api/users/current", method = RequestMethod.GET)
    public User getCurrent() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof StatelessAuthenticationFilter.UserAuthentication) {
            return ((StatelessAuthenticationFilter.UserAuthentication) authentication).getDetails();
        }
        return new User(authentication.getName()); //anonymous user support
    }

    @RequestMapping(value = "/api/users/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {

        if (StringUtils.isEmpty(username)) {
            return new ResponseEntity("username is required", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User user = userService.getUserByUsername(username);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/users/current", method = RequestMethod.PATCH)
    public ResponseEntity<String> changePassword(@RequestBody final User user) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final User currentUser = userRepository.findByUsername(authentication.getName());

        if (user.getNewPassword() == null || user.getNewPassword().length() < 4) {
            return new ResponseEntity<>("new password to short", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        final BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        if (!pwEncoder.matches(user.getPassword(), currentUser.getPassword())) {
            return new ResponseEntity<>("old password mismatch", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        currentUser.setPassword(pwEncoder.encode(user.getNewPassword()));
        userRepository.saveAndFlush(currentUser);
        return new ResponseEntity<>("password changed", HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/api/users/{user}/grant/role/{role}", method = RequestMethod.POST)
    public ResponseEntity<String> grantRole(@PathVariable User user, @PathVariable UserRole role) {
        if (user == null) {
            return new ResponseEntity<>("invalid user id", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        user.grantRole(role);
        userRepository.saveAndFlush(user);
        return new ResponseEntity<>("role granted", HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/api/users/{user}/revoke/role/{role}", method = RequestMethod.POST)
    public ResponseEntity<String> revokeRole(@PathVariable User user, @PathVariable UserRole role) {
        if (user == null) {
            return new ResponseEntity<>("invalid user id", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        user.revokeRole(role);
        userRepository.saveAndFlush(user);
        return new ResponseEntity<>("role revoked", HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/api/users", method = RequestMethod.GET)
    public List<User> list() {
        return userRepository.findAll();
    }
}

