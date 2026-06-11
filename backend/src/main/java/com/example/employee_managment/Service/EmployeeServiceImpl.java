package com.example.employee_managment.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.employee_managment.entities.UserAccount;
import com.example.employee_managment.repositories.UserAccountRepo;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.UUID;

import com.example.employee_managment.Dto.EmployeeCreate;
import com.example.employee_managment.Dto.EmployeeUpdate;
import com.example.employee_managment.Shared.CustomResponseException;
import com.example.employee_managment.Utils.SecurityUtils;
import com.example.employee_managment.abstrac.EmployeeService;
import com.example.employee_managment.entities.Speciality;
import com.example.employee_managment.entities.Employee;
import com.example.employee_managment.repositories.SpecialityRepo;
import com.example.employee_managment.repositories.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private SpecialityRepo specialityRepo;
    @Autowired
    SecurityUtils securityUtils;

    // ✅ أضف هذه الـ dependencies
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private JavaMailSender mailSender;

    @PreAuthorize("@securityUtils.isOwner(#employeeId)")
    @Override
    public Employee findOne(UUID employeeId) {
        Optional<Employee> employee = employeeRepo.findById(employeeId);
        if (employee.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("employee not found");
        }
        return employee.get();
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public void deleteOne(UUID employeeId) {
        Optional<Employee> employee = employeeRepo.findById(employeeId);
        employee.ifPresent(value -> employeeRepo.deleteById(value.getId()));
    }

    @Override
    public Employee UpdateOne(UUID employeeId, EmployeeUpdate employee) {
        Optional<Employee> ExistingEmployee = employeeRepo.findById(employeeId);

        if (ExistingEmployee.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("employee not found update");
        }
        Employee updatedEmployee = ExistingEmployee.get();
        updatedEmployee.setFirstName(employee.firstName());
        updatedEmployee.setLastName(employee.lastName());
        updatedEmployee.setPhoneNumber(employee.phoneNumber());
        updatedEmployee.setPosition(employee.position());
        return updatedEmployee;
    }

    @Override
    @Transactional
    public Employee CreateOne(EmployeeCreate employeeCreate) {
        // 1. Vérifier la spécialité
        Optional<Speciality> speciality = specialityRepo.findById(employeeCreate.specialityId());
        if (speciality.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("Speciality not found");
        }

        // 2. Créer l'employé
        Employee employee = new Employee();
        employee.setFirstName(employeeCreate.firstName());
        employee.setLastName(employeeCreate.lastName());
        employee.setEmail(employeeCreate.email());
        employee.setPosition(employeeCreate.position());
        employee.setPhoneNumber(employeeCreate.phoneNumber());
        employee.setHireDate(employeeCreate.hireDate());
        employee.setSpeciality(speciality.get());

        Employee savedEmployee = employeeRepo.save(employee);

        // 3. Générer mot de passe aléatoire
        String rawPassword = generateRandomPassword();  // ✅ تعريف rawPassword

        // 4. Créer UserAccount
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(savedEmployee.getEmail());
        userAccount.setPassword(passwordEncoder.encode(rawPassword));
        userAccount.setEmployee(savedEmployee);

        userAccountRepo.save(userAccount);

        // 5. Envoyer email
        sendCredentialsByEmail(savedEmployee.getEmail(), savedEmployee.getEmail(), rawPassword);

        return savedEmployee;
    }

    private String generateRandomPassword() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

    private void sendCredentialsByEmail(String to, String username, String rawPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("🔐 Vos identifiants de connexion");
        message.setText("Bonjour,\n\n"
                + "Un compte a été créé pour vous sur la plateforme Forum Technique.\n\n"
                + "👤 Nom d'utilisateur : " + username + "\n"
                + "🔑 Mot de passe : " + rawPassword + "\n\n"
                + "Veuillez vous connecter ici : http://localhost:4200/auth\n\n"
                + "Ce mot de passe peut être changé après connexion.\n\n"
                + "Cordialement,\nL'équipe technique");
        mailSender.send(message);
    }
}