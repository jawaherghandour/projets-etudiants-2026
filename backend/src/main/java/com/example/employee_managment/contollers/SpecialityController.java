package com.example.employee_managment.contollers;

import com.example.employee_managment.Dto.SpecialityCreate;
import com.example.employee_managment.Shared.GlobalResponse;
import com.example.employee_managment.abstrac.SpecialityService;
import com.example.employee_managment.entities.Speciality;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/specialitys")
public class SpecialityController {
    @Autowired
    private SpecialityService specialityService;

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Speciality>>> findAll() {
        List<Speciality> specialities = specialityService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(specialities), HttpStatus.OK);
    }

    @GetMapping("/{specialityId}")
    public ResponseEntity<Speciality> findOne(@PathVariable UUID specialityId) {
        Speciality speciality = specialityService.findOne(specialityId);

        return new ResponseEntity<Speciality>(speciality, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Speciality> CreateOne(@RequestBody @Valid SpecialityCreate specialityCreate) {
        Speciality newspeciality = specialityService.CreateOne(specialityCreate);
        return new ResponseEntity<Speciality>(newspeciality, HttpStatus.CREATED);

    }

    @DeleteMapping("/{specialityId}")
    public ResponseEntity<Void> DeleteOne(@PathVariable UUID specialityId) {
          specialityService.DeleteOne(specialityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
