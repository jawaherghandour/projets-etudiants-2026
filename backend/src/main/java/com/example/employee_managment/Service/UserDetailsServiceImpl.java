package com.example.employee_managment.Service;

import com.example.employee_managment.Shared.CustomResponseException;
import com.example.employee_managment.entities.UserAccount;
import com.example.employee_managment.repositories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserAccountRepo userAccountRepo;
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserAccount> account = userAccountRepo.findOneByUsername(username);
        if(account.isEmpty()){
            throw new UsernameNotFoundException("User not found ");
        }
        UserAccount user = account.get();

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

    }
}
