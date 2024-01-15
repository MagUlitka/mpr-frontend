package com.example.mprprojectmvn.resource;

import com.example.mprprojectmvn.data.StudentUnit;
import com.example.mprprojectmvn.data.StudyCourseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudent {
    @NotBlank String name;
    @NotBlank String surname;
    @NotNull StudyCourseType studyCourseType;
    @NotNull StudentUnit unit;
    @NotNull Long index;

    public CreateStudent(String name, String surname, StudyCourseType studyCourseType, StudentUnit unit) {
        this.name = name;
        this.surname = surname;
        this.studyCourseType = studyCourseType;
        this.unit = unit;
    }
}
