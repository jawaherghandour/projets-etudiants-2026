package com.example.employee_managment.abstrac;

import com.example.employee_managment.Dto.SpecialityCreate;
import com.example.employee_managment.entities.Speciality;

import java.util.List;
import java.util.UUID;

public interface SpecialityService {
    Speciality findOne(UUID specialityId);
    List<Speciality> findAll();
    Speciality CreateOne(SpecialityCreate specialityCreate);
    Void DeleteOne(UUID specialityId);
}
