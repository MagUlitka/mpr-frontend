package com.example.mprprojectmvn.resource;

import com.example.mprprojectmvn.data.StudentUnit;
import com.example.mprprojectmvn.data.StudyCourseType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddStudent {

    private String name;

    private String surname;

    private StudyCourseType studyCourseType;

    private StudentUnit unit;
}
