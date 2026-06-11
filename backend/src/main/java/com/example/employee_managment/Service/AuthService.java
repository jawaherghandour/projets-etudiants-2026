package com.example.employee_managment.Service;

import com.example.employee_managment.Dto.LoginRequest;
import com.example.employee_managment.Dto.SignupRequest;
import com.example.employee_managment.Shared.CustomResponseException;
import com.example.employee_managment.configuration.JwtHelper;
import com.example.employee_managment.entities.Employee;
import com.example.employee_managment.entities.UserAccount;
import com.example.employee_managment.repositories.EmployeeRepo;
import com.example.employee_managment.repositories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.mail.javamail.JavaMailSender;
import com.example.employee_managment.Shared.CustomResponseException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserAccountRepo userAccountRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private JavaMailSender mailSender;

    public void signup(SignupRequest signupRequest){
        Employee employee = employeeRepo.findById(signupRequest.employeeId())
                .orElseThrow(()->CustomResponseException
                .ResourceNotFound("employee not found by id"));
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(signupRequest.username());
        userAccount.setPassword(passwordEncoder.encode(signupRequest.password()));
        userAccount.setEmployee(employee);
        userAccountRepo.save(userAccount);

    }
    public String login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );
        UserAccount user = userAccountRepo.findOneByUsername(loginRequest.username())
                .orElseThrow(() -> CustomResponseException.badCredentials());
        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("userId", user.getId());
        customClaims.put("role", user.getRole());
        return jwtHelper.generateToken(customClaims,user);

    }
    public void sendResetLink(String email) {
        // 1. Trouver UserAccount par email
        UserAccount userAccount = userAccountRepo.findByUsername(email)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound("Email non trouvé"));

        // 2. Générer token unique
        String token = UUID.randomUUID().toString();

        // 3. Sauvegarder token et expiry (24 heures)
        userAccount.setResetToken(token);
        userAccount.setResetTokenExpiry(LocalDateTime.now().plusHours(24));
        userAccountRepo.save(userAccount);

        // 4. Créer lien de réinitialisation
        String resetLink = "http://localhost:4200/reset-password?token=" + token;

        // 5. Envoyer email
        sendResetPasswordEmail(userAccount.getEmployee().getEmail(), resetLink);
    }

    private void sendResetPasswordEmail(String to, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("🔐 Réinitialisation de votre mot de passe");
        message.setText("Bonjour,\n\n"
                + "Vous avez demandé la réinitialisation de votre mot de passe.\n\n"
                + "Cliquez sur le lien ci-dessous pour réinitialiser votre mot de passe :\n"
                + resetLink + "\n\n"
                + "Ce lien expirera dans 24 heures.\n\n"
                + "Si vous n'avez pas demandé cette réinitialisation, ignorez cet email.\n\n"
                + "Cordialement,\nL'équipe technique");
        mailSender.send(message);
    }

    public void resetPassword(String token, String newPassword) {
        // 1. Trouver UserAccount par token
        UserAccount userAccount = userAccountRepo.findByResetToken(token)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound("Token invalide ou expiré"));

        // 2. Vérifier si token n'est pas expiré
        if (userAccount.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            throw CustomResponseException.BadRequest("Token expiré");
        }

        // 3. Mettre à jour le mot de passe
        userAccount.setPassword(passwordEncoder.encode(newPassword));
        userAccount.setResetToken(null);
        userAccount.setResetTokenExpiry(null);
        userAccountRepo.save(userAccount);
    }
}
