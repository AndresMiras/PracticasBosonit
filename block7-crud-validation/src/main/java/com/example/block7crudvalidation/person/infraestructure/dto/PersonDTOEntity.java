package com.example.block7crudvalidation.person.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTOEntity implements Serializable {

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

}
