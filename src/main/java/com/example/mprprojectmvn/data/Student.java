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

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private StudentUnit unit;
    @Setter
    private Long index;

}
