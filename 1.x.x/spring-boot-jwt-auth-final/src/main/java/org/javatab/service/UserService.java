package org.javatab.service;

import org.javatab.domain.User;

/**
 * Created by nasir on 29/1/16.
 */
public interface UserService {

    void addUser(String userName, String password);
    User getUserByUsername(String username);
}
