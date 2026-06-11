package com.example.employee_managment.contollers;

import com.example.employee_managment.Shared.GlobalResponse;
import com.example.employee_managment.abstrac.ThemeService;
import com.example.employee_managment.entities.Employee;
import com.example.employee_managment.entities.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/themes")
public class ThemeController {
    @Autowired
    private ThemeService themeService;
    @PostMapping
    public Theme add(@RequestBody Theme theme){
        return themeService.addCategory(theme);
    }


    @GetMapping
    public ResponseEntity<GlobalResponse<List<Theme>>> getAll(){

        List<Theme> themes = themeService.getAllCategories();
        return new ResponseEntity<>(new GlobalResponse<>(themes), HttpStatus.OK);
    }



}
