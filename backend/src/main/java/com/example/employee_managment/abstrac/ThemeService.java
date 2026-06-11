package com.example.employee_managment.abstrac;

import com.example.employee_managment.entities.Theme;

import java.util.List;

public interface ThemeService {
    Theme addCategory(Theme theme);
    List<Theme> getAllCategories();
}
