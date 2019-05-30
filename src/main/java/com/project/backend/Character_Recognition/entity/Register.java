package com.project.backend.Character_Recognition.entity;

import javax.persistence.*;

@Entity
@Table(name = "register")
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idregister")
    private Long idregister;

    @Column(name = "First_Name")
    private String firstName;
    @Column(name = "Last_Name")
    private String lastName;
    @Column(name = "email")
    private String email;

    public Long getIdregister() {
        return idregister;
    }

    public void setIdregister(Long idregister) {
        this.idregister = idregister;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
