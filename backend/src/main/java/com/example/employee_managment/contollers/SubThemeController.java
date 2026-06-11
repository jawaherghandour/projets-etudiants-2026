package com.example.employee_managment.contollers;

import com.example.employee_managment.abstrac.SubThemeService;
import com.example.employee_managment.entities.SubTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/subthemes")
public class SubThemeController {
    @Autowired
    private SubThemeService subThemeService;
    @PostMapping("/{themeId}")
    public SubTheme add(@PathVariable UUID themeId,@RequestBody SubTheme subTheme){
        return subThemeService.addSubCategory(themeId, subTheme);
    }
    @GetMapping
    public List<SubTheme> getAll(){
        return subThemeService.getAll();
    }


}
