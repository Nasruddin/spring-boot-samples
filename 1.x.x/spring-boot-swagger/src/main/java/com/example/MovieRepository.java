package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nasruddin on 16/10/16.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

}
