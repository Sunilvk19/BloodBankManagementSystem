package com.example.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BloodBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bloodbankId;
    private String bankName;
    private int emergencyUnitCount;

    @OneToMany
    private List<Admin> adminList;

    @OneToOne
    private Address address;

    @OneToMany
    private List<Sample> sample;

    @OneToMany
    private List<DonationRequest> donationRequest;
}
