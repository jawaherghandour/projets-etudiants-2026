package com.example.employee_managment.Service;

import com.example.employee_managment.Dto.SpecialityCreate;
import com.example.employee_managment.Shared.CustomResponseException;
import com.example.employee_managment.abstrac.SpecialityService;
import com.example.employee_managment.entities.Speciality;
import com.example.employee_managment.repositories.SpecialityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class SpecialityServiceImp implements SpecialityService {
    @Autowired
    private SpecialityRepo specialityRepo;
    @Override
    public Speciality findOne(UUID specialityId) {
        Optional<Speciality> speciality = specialityRepo.findById(specialityId);
        if (speciality.isEmpty()) {
            //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            throw CustomResponseException.ResourceNotFound("speciality not found");
        }
        return speciality.get();

    }

    @Override
    public List<Speciality> findAll() {
        return specialityRepo.findAll();
    }

    @Override
    public Speciality CreateOne(SpecialityCreate specialityCreate) {
        Speciality speciality = new Speciality();

        speciality.setName(specialityCreate.name());
          specialityRepo.save(speciality);
        return speciality;

    }



    @Override
    public Void DeleteOne(UUID specialityId) {
        specialityRepo.deleteById(specialityId);
        return null;
    }
}
