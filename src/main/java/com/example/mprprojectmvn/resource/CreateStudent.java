package com.example.mprprojectmvn.resource;

import com.example.mprprojectmvn.data.StudentUnit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateStudent(@NotBlank String name, @NotNull StudentUnit unit) {
}
