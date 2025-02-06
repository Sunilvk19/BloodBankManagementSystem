package com.example.bsm.responsedto;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Gender;
import com.example.bsm.enums.Role;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private int userid;
    private String username;
    private BloodGroup bloodgroup;
    private LocalDate lastdonatedat;
    private int age;
    private Gender gender;
    private String availablecity;
    private boolean Verified;
    private LocalDateTime createdAt;
    private LocalDateTime lastmodifiesAt;
    private Role role;
}
