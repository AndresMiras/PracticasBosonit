package com.example.block7crud.entity;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

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
    @NotBlank(message = "Name is mandatory")
    @NonNull
    @NotNull
    @Size(min = 4, message = "Wrong address, should have a length greater than 4")
    @Size(max = 13, message = "Wrong address, should have a length greater than 13")
    private String name;

    @Column(name="address")
    @NotBlank(message = "Address is mandatory")
    @NonNull
    @NotNull
    @Size(min = 4, message = "Wrong address, should have a length greater than 4")
    @Size(max = 25, message = "Wrong address, should have a length greater than 25")
    private String address;

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
