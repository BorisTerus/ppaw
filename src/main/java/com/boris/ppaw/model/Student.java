package com.boris.ppaw.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "grades",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Set<Course> studentSet = new HashSet<>();
}
