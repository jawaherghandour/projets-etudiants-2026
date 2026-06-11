package com.example.employee_managment.Service;

import com.example.employee_managment.abstrac.ThemeService;
import com.example.employee_managment.entities.Theme;
import com.example.employee_managment.repositories.ThemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ThemeServiceImpl implements ThemeService {
    @Autowired
    private ThemeRepo themeRepo;
    @Override
    public Theme addCategory(Theme theme) {


        return  themeRepo.save(theme);
    }

    @Override
    public List<Theme> getAllCategories() {
        return themeRepo.findAll();
    }
}
