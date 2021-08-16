package com.javatab.repository;

import com.javatab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nasruddin on 18/6/16.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
