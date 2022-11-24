package com.example.block7crudvalidation.person.infraestructure.dto;

import com.example.block7crudvalidation.person.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTOResponse implements Serializable {

    private Long id;

    private String completeName;
    private String companyEmail;
    private String address;
    private String city;
    private String imageUrl;

    public PersonDTOResponse(Person person) {
        this.id = person.getId();
        this.completeName = person.getName() + " " + person.getSurname();
        this.companyEmail = person.getCompanyEmail();
        this.address = person.getAddress();
        this.city = person.getCity();
        this.imageUrl = person.getImageUrl();
    }

    public PersonDTOResponse(Long id, String name, String surname, String companyEmail, String address, String city, String imageUrl) {
        this.id = id;
        this.completeName = name + " " + surname;
        this.companyEmail = companyEmail;
        this.address = address;
        this.city = city;
        this.imageUrl = imageUrl;
    }

}
