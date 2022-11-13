package com.example.block7crudvalidation.person.dto;

import com.example.block7crudvalidation.person.entities.PersonEntity;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import helper.Compare;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class PersonDTORequest implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String company_email;
    private String personal_email;
    private String address;
    private String city;
    private Boolean active;
    private Date created_date;
    private Date termination_date;
    private String imagen_url;

    public PersonDTORequest(Long id, String name, String surname, String password, String personal_email, String address, String city, String imagen_url) {

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.personal_email = personal_email;
        this.company_email = Arrays.stream(personal_email.split("@")).toList().get(0) + "@bosonit.com";
        this.address = address;
        this.city = city;
        this.active = false;
        this.created_date = new Date();
        this.termination_date = new Date();
        this.imagen_url = imagen_url;
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
        } else if (Objects.isNull(personal_email) || !Compare.isEmail(personal_email)) {
            throw new UnprocessableEntityException("Error, Incorrect Personal_email..., avoid putting a null field: ");
        } else if (Objects.isNull(address) || !Compare.isAsciiPrintable(address) || address.isBlank()) {
            throw new UnprocessableEntityException("Error, Incorrect Address..., avoid putting a null field: ");
        } else if (Objects.isNull(city) || !Compare.isAsciiPrintable(city) || city.isBlank()) {
            throw new UnprocessableEntityException("Error, Incorrect City..., avoid putting a null field: ");
        } else if (Objects.isNull(imagen_url) || !Compare.isAsciiPrintable(imagen_url) || imagen_url.isBlank()) {
            throw new UnprocessableEntityException("Error, Incorrect Imagen_url..., avoid putting a null field: ");
        } else {
            return true;
        }
    }

    public PersonEntity getEntity() {
        return new PersonEntity(id, name, surname, password, company_email, personal_email, address, city, active, created_date, termination_date, imagen_url);
    }

    public PersonDTOResponse getResponseDTO() {
        return new PersonDTOResponse(id, name, surname, company_email, address, city, imagen_url);
    }
}
