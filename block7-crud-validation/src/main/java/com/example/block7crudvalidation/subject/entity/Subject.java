package com.example.block7crudvalidation.subject.entity;
import com.example.block7crudvalidation.base.entity.Base;
import com.example.block7crudvalidation.student.entity.Student;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table
@Entity
public class Subject extends Base {

    @Column
    private String name;

    @Column
    private String comment;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startDate; // Comienza a cursar la asignatura.

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date endDate; // Termina de estudiar la asignatura.


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "student_subjects", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    @JsonIgnore
    private List<Student> studentsSubject;

    @JsonCreator
    public Subject(String name, String comment, Date startDate, Date endDate) {
        this.name = name;
        this.comment = comment;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @JsonCreator
    public Subject(Long id, String name, String comment, Date startDate, Date endDate) {
        super(id);
        this.name = name;
        this.comment = comment;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
