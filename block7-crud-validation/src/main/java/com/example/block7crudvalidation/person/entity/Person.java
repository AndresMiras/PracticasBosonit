package com.example.block7crudvalidation.person.entity;

import com.example.block7crudvalidation.base.entity.Base;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonDTOResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "person")
@Entity
public class Person extends Base {

    // Al ser una práctica haré uso de Json Ignore y Create para no mandar datos confidenciales, solamente uso DTOs para los procedimientos explícitos de la práctica.

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    @JsonIgnore
    private String password;

    @Column(name="company_email")
    private String companyEmail;

    @JsonIgnore
    @Column(name="personal_email")
    private String personalEmail;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    @JsonIgnore
    private Boolean active;

    @Column(name="created_date")
    private Date createdDate;

    @JsonIgnore
    @Column(name="termination_date")
    private Date terminationDate;

    @Column(name="imagen_url")
    private String imageUrl;

    @JsonCreator
    public Person( String name, String surname, String password, String companyEmail, String personalEmail, String address, String city, Boolean active, Date createdDate, Date terminationDate, String imageUrl) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.companyEmail = companyEmail;
        this.personalEmail = personalEmail;
        this.address = address;
        this.city = city;
        this.active = active;
        this.createdDate = createdDate;
        this.terminationDate = terminationDate;
        this.imageUrl = imageUrl;
    }

    public PersonDTOResponse convertToPersonDTOResponse() {
        return new PersonDTOResponse(this);
    }

}
