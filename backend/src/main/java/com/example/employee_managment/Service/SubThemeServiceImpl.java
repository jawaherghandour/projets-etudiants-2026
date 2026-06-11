package com.example.employee_managment.Service;

import com.example.employee_managment.abstrac.SubThemeService;
import com.example.employee_managment.entities.Theme;
import com.example.employee_managment.entities.SubTheme;
import com.example.employee_managment.repositories.ThemeRepo;
import com.example.employee_managment.repositories.SubThemeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.UUID;

@Service
public class SubThemeServiceImpl implements SubThemeService {

    @Autowired
    private SubThemeRepo subThemeRepo;

    @Autowired
    private ThemeRepo themeRepo;

    @Override
    public SubTheme addSubCategory(UUID themeId, SubTheme subTheme) {

        Theme theme = themeRepo.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Theme not found"));

        subTheme.setTheme(theme);

        return subThemeRepo.save(subTheme);
    }

    @Override
    public List<SubTheme> getAll() {
        return subThemeRepo.findAll();
    }





}