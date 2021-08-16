package com.javatab.repositories;

import com.javatab.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nasir on 12/1/16.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
