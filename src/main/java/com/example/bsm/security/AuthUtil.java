package com.example.bsm.security;

import com.example.bsm.entity.Admin;
import com.example.bsm.entity.User;
import com.example.bsm.exception.AdminNotFoundException;
import com.example.bsm.exception.UserNotFoundException;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class AuthUtil {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getCurrentUser(){
      return userRepository.findByEmail(this.getCurrentUsername())
                        .orElseThrow(() -> new UserNotFoundException("Failed to authenticate"));
    }
    public String getCurrentAdminname(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    public Admin getCurrentAdmin(){
        return adminRepository.findByUser_Email(this.getCurrentAdminname())
                .orElseThrow(() -> new AdminNotFoundException("Failed to authenticate"));
    }
}
