package com.javatab.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by nasir on 25/1/16.
 */
@Entity
@Table(name = "tasks")
@Getter @Setter
public class Task implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @Column(name = "task_text")
    @NotNull
    private String task;

    @Column(name = "is_completed")
    @NotNull
    private boolean isCompleted;
}
