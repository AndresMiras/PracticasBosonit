package com.example.block7crudvalidation.dto;

import com.example.block7crudvalidation.entities.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO implements Serializable {

    private Long id;
    private String completeName;
    private String company_email;
    private String address;
    private String city;
    private String imagen_url;

    public PersonDTO(PersonEntity personEntity) {
        this.id = personEntity.getId();
        this.completeName = personEntity.getName() + " " + personEntity.getSurname();
        this.company_email = personEntity.getCompany_email();
        this.address = personEntity.getAddress();
        this.city = personEntity.getCity();
        this.imagen_url = personEntity.getImagen_url();
    }
}
