package com.javatab.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nasir on 22/12/15.
 */
@Entity
@Table(name = "users")
@NamedQuery(name="Book.findByAge",query="select name, age from User u where u.age=?1")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_age")
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
