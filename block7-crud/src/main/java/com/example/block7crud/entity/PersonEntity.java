package com.example.block7crud.entity;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Person Entity field is blank, not allow.")
    @NonNull
    @Min(value = 4)
    @Max(value = 13)
    private String name;

    @Column(name="address")
    @NotBlank(message = "Person Entity field is blank, not allow.")
    @NonNull
    @Min(value = 4)
    @Max(value = 25)
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
