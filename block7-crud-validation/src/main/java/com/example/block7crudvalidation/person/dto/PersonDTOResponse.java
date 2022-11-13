package com.example.block7crudvalidation.person.dto;

import com.example.block7crudvalidation.person.entities.PersonEntity;
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
    private String company_email;
    private String address;
    private String city;
    private String imagen_url;

    public PersonDTOResponse(PersonEntity personEntity) {
        this.id = personEntity.getId();
        this.completeName = personEntity.getName() + " " + personEntity.getSurname();
        this.company_email = personEntity.getCompany_email();
        this.address = personEntity.getAddress();
        this.city = personEntity.getCity();
        this.imagen_url = personEntity.getImagen_url();
    }

    public PersonDTOResponse(Long id, String name, String surname, String company_email, String address, String city, String imagen_url) {
        this.id = id;
        this.completeName = name + " " + surname;
        this.company_email = company_email;
        this.address = address;
        this.city = city;
        this.imagen_url = imagen_url;
    }
}
