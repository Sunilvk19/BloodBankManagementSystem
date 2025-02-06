package com.example.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hospitalid;
    private String name;

    @OneToMany
    private List<Admin> admin;

    @OneToOne
    private Address addresses;

    @OneToMany
    private List<DonationRequest> donationRequest;
}
