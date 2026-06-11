package com.example.employee_managment.abstrac;

import com.example.employee_managment.entities.SubTheme;

import java.util.List;
import java.util.UUID;

public interface SubThemeService {
    SubTheme addSubCategory(UUID themeId , SubTheme subTheme);
    List<SubTheme> getAll();
   

}
