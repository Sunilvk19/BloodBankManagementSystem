package com.example.bsm.entity;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Gender;
import com.example.bsm.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "User_Tan;e")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private BloodGroup bloodGroup;
    private LocalDate lastdonatedat;
    private int age;
    private Gender gender;
    private String availableCity;
    private boolean Verified;
    private LocalDateTime createdAt;
    private LocalDateTime lastmodifiesAt;
    private Role role;

    @OneToOne(mappedBy = "user",fetch = FetchType.EAGER)
    private Admin admin;
}
