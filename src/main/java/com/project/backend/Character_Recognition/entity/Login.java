package com.project.backend.Character_Recognition.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "login")
//@NamedQueries({
        @NamedQuery(
                name = "Login.fetchByUserDetails",
                query = "Select u FROM Login u where u.email=:email and u.password=:password"
        )
//})
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idlogin")
    private Long idlogin;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
