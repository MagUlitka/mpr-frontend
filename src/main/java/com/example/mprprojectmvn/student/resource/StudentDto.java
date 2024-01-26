package com.example.mprprojectmvn.student.resource;

import com.example.mprprojectmvn.student.data.StudentUnit;

import java.util.UUID;

public record StudentDto(UUID id, String name, String surname, String courseName, StudentUnit unit, Long index) {
}
