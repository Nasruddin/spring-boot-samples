package com.javatab.repository;

import com.javatab.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by nasir on 22/12/15.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    //Repository Queries
    @Query(value = "Select name from User u where u.name=?1")
    List<User> findByName(String name);
    // Query will be used from Named query defined at Entity class
    List<User> findByAge(int age);
}
