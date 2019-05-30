package com.project.backend.Character_Recognition.entity;

import javax.persistence.*;

@Entity
@Table(name = "login")
@NamedQueries({
        @NamedQuery(
                name = "Login.fetchByUserDetails",
                query = "Select u FROM Login u where u.email=:email and u.password=:password"
        )
})
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idlogin")
    private Long idlogin;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIdlogin() {
        return idlogin;
    }

    public void setIdlogin(Long idlogin) {
        this.idlogin = idlogin;
    }
}
