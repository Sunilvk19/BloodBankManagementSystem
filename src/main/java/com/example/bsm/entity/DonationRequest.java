package com.example.bsm.entity;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.OrganazationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donationid;
    private LocalDate date;
    private LocalTime time;
    private List<BloodGroup> bloodGroupList;
    private OrganazationType orgType;
    private boolean requestCompleted;
    private List<String> cities;

    @ManyToOne
    private Hospital hospital;
}
