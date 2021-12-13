package com.spring.boot.blog.Springbootblog.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//As username and email for a user should be unique.
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String userName;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", //this name will be the new 3rd table name
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, //this id ref is from User entity primary key id
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}) //this id ref name is from Role entity primary key id
    private Set<Role> roles;
}
