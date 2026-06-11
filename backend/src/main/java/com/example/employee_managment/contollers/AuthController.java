package com.example.employee_managment.contollers;

import com.example.employee_managment.Dto.*;
import com.example.employee_managment.Service.AuthService;
import com.example.employee_managment.Shared.CustomResponseException;
import com.example.employee_managment.Shared.GlobalResponse;
import com.example.employee_managment.entities.Employee;
import com.example.employee_managment.entities.UserAccount;
import com.example.employee_managment.repositories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserAccountRepo userAccountRepo;
    @PostMapping("/signup")
    public ResponseEntity<GlobalResponse<String>> signup(@RequestBody SignupRequest signupRequest){
       authService.signup(signupRequest);
        System.out.println(signupRequest.employeeId());
        return new ResponseEntity<>(new GlobalResponse<>("singed up"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<String>> login(@RequestBody LoginRequest loginRequest){
        String token= authService.login(loginRequest);

        return new ResponseEntity<>(new GlobalResponse<>(token), HttpStatus.OK);
    }
    @GetMapping("/me")
    public ResponseEntity<GlobalResponse<UserInfoDTO>> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        // جلب UserAccount من قاعدة البيانات
        UserAccount userAccount = userAccountRepo.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> CustomResponseException.ResourceNotFound("User not found"));

        // جلب Employee المرتبط
        Employee employee = userAccount.getEmployee();

        // إنشاء DTO للإرسال
        UserInfoDTO userInfo = new UserInfoDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                userAccount.getUsername(),
                employee.getPosition(),
                employee.getSpeciality().getName()
        );

        return ResponseEntity.ok(new GlobalResponse<>(userInfo));
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<GlobalResponse<String>> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        authService.sendResetLink(request.email());
        return ResponseEntity.ok(new GlobalResponse<>("Lien de réinitialisation envoyé par email"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<GlobalResponse<String>> resetPassword(@RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request.token(), request.newPassword());
        return ResponseEntity.ok(new GlobalResponse<>("Mot de passe réinitialisé avec succès"));
    }
}
