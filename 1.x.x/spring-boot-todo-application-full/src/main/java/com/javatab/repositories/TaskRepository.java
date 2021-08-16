package com.javatab.repositories;

import com.javatab.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nasir on 25/1/16.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
}
