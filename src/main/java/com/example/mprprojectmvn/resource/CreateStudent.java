package com.example.mprprojectmvn.resource;

import com.example.mprprojectmvn.data.StudentUnit;
import com.example.mprprojectmvn.data.StudyCourseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudent {
    @NotBlank String name;
    @NotBlank String surname;
    @NotNull StudyCourseType studyCourseType;
    @NotNull StudentUnit unit;
}
