package com.example.employee_managment.configuration;

import com.example.employee_managment.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
   // @Autowired
    private final UserDetailsService userDetailsService;
    @Autowired
    JwtAuthFilter jwtAuthFilter;
    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){ //interface

        return new BCryptPasswordEncoder(); // class
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth ->auth
                .requestMatchers("/auth/signup",
                        "/auth/login","/employees/**","/specialitys/**", "/themes/**", "/subthemes/**", "/questions/by-subtheme/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/employees").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.GET,"/employees/{employeeId}").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.POST,"/employees").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.DELETE,"/employees/{employeeId}").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.PUT,"/employees/{employeeId}").hasAnyRole("ADMIN","USER")
                        .anyRequest().permitAll()



                ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
        return config.getAuthenticationManager();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;


    }
}
