package com.codegym.lib.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @JsonIgnore
    @ManyToMany( cascade = CascadeType.ALL , fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    @Override
    public String getAuthority() {
        return this.role;
    }
}
