package com.example.mprprojectmvn.data;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    public Student(String name, StudentUnit unit, Long index) {
        this.name = name;
        this.unit = unit;
        this.index = index;
    }
    public Student(String name, StudentUnit unit) {
        this.name = name;
        this.unit = unit;
    }

    public Student(String name, String surname, StudyCourseType studyCourseType, StudentUnit unit, Long index) {
        this.name = name;
        this.surname = surname;
        this.studyCourseType = studyCourseType;
        this.unit = unit;
        this.index = index;
    }

    public Student(String name, String surname, StudyCourseType studyCourseType, StudentUnit unit) {
        this.name = name;
        this.surname = surname;
        this.studyCourseType = studyCourseType;
        this.unit = unit;
    }

    @Id
    @GeneratedValue
    private UUID id;
    @Setter
    private String name;
    @Setter
    private String surname;
    @Setter
    @Enumerated(EnumType.STRING)
    private StudyCourseType studyCourseType;
    @Setter
    @Enumerated(EnumType.STRING)
    private StudentUnit unit;
    @Setter
    private Long index;

}
