package com.example.mprprojectmvn.resource;

import com.example.mprprojectmvn.data.StudentUnit;
import com.example.mprprojectmvn.data.StudyCourseType;

import java.util.UUID;

public record StudentDto(UUID id, String name, String surname, StudyCourseType studyCourseType, StudentUnit unit, Long index) {
}
