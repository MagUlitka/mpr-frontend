package com.example.mprprojectmvn.resource;

import com.example.mprprojectmvn.data.StudentUnit;

import java.util.UUID;

public record StudentDto(UUID id, String name, StudentUnit unit, Long index) {
}
