package com.codegym.lib.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Role { //implements GrantedAuthority
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role;

//    @JsonIgnore
//    private List<User> users;
//
//    @Override
//    public String getAuthority() {
//        return this.role;
//    }
}
