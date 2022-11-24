package com.example.block7crudvalidation.person.infraestructure.dto;

import com.example.block7crudvalidation.person.entity.Person;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.fasterxml.jackson.annotation.JsonCreator;
import helper.Compare;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
public class PersonDTORequest implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String companyEmail;
    private String personalEmail;
    private String address;
    private String city;
    private Boolean active;
    private Date startDate;
    private Date endDate;
    private String imageUrl;

    public PersonDTORequest(Long id, String name, String surname, String password, String personalEmail, String address, String city, String imageUrl) {

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.personalEmail = personalEmail;
        this.companyEmail = Arrays.stream(personalEmail.split("@")).toList().get(0) + "@bosonit.com";
        this.address = address;
        this.city = city;
        this.active = false;
        this.startDate = new Date();
        this.endDate = new Date();
        this.imageUrl = imageUrl;
    }

    @JsonCreator
    public PersonDTORequest(String name, String surname, String password, String personalEmail, String address, String city, String imageUrl) {

        this.name = name;
        this.surname = surname;
        this.password = password;
        this.personalEmail = personalEmail;
        this.companyEmail = Arrays.stream(personalEmail.split("@")).toList().get(0) + "@bosonit.com";
        this.address = address;
        this.city = city;
        this.active = false;
        this.startDate = new Date();
        this.endDate = new Date();
        this.imageUrl = imageUrl;
    }

    // Method who's validate fields from client...
    public boolean validateData() {
        if (!Compare.isNumber(String.valueOf(id)) && !Objects.isNull(id)) {
            throw new UnprocessableEntityException("Error, Incorrect Id..., avoid putting a null field: ");
        } else if (Objects.isNull(name) || !(name.length() < 10) || !Compare.isAsciiPrintable(name) || name.isBlank()) {
            throw new UnprocessableEntityException("Error, Incorrect Name..., avoid entering a null field or a string with more than 10 characters: ");
        } else if (Objects.isNull(surname) || !Compare.isAsciiPrintable(surname) || surname.isBlank()) {
            throw new UnprocessableEntityException("Error, Incorrect Surname..., avoid putting a null field: ");
        } else if (Objects.isNull(password) || !Compare.isAsciiPrintable(password) || password.isBlank()) {
            throw new UnprocessableEntityException("Error, Incorrect Password..., avoid putting a null field: ");
        } else if (Objects.isNull(personalEmail) || !Compare.isEmail(personalEmail)) {
            throw new UnprocessableEntityException("Error, Incorrect Personal_email..., avoid putting a null field: ");
        } else if (Objects.isNull(address) || !Compare.isAsciiPrintable(address) || address.isBlank()) {
            throw new UnprocessableEntityException("Error, Incorrect Address..., avoid putting a null field: ");
        } else if (Objects.isNull(city) || !Compare.isAsciiPrintable(city) || city.isBlank()) {
            throw new UnprocessableEntityException("Error, Incorrect City..., avoid putting a null field: ");
        } else if (Objects.isNull(imageUrl) || !Compare.isAsciiPrintable(imageUrl) || imageUrl.isBlank()) {
            throw new UnprocessableEntityException("Error, Incorrect Imagen_url..., avoid putting a null field: ");
        } else {
            return true;
        }
    }

    public Person entity() {
        return new Person(name, surname, password, companyEmail, personalEmail, address, city, active, startDate, endDate, imageUrl);
    }


}
