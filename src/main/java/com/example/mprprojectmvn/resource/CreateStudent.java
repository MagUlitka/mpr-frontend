package com.example.mprprojectmvn.resource;

import com.example.mprprojectmvn.data.StudentUnit;
import com.example.mprprojectmvn.data.StudyCourseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateStudent(@NotBlank String name, @NotBlank String surname, @NotNull StudyCourseType studyCourseType, @NotNull StudentUnit unit) {
}
