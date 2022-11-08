package com.example.block7crudvalidation.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "person")
@NoArgsConstructor
public class PersonEntity implements Serializable {

    @Id
    @Column(name="person_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="password")
    private String password;

    @Column(name="company_email")
    private String company_email;

    @Column(name="personal_email")
    private String personal_email;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="active")
    private Boolean active;

    @Column(name="created_date")
    private Date created_date;

    @Column(name="termination_date")
    private Date termination_date;

    @Column(name="imagen_url")
    private Boolean imagen_url;

    public PersonEntity(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public PersonEntity(PersonEntity user) {
        this.id = user.getId();
        this.name = user.getName();
        this.address = user.getAddress();
    }
}
