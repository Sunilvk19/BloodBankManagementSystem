package com.example.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int surveyid;
    private boolean preMedicalCondition;
    private boolean consumedAlcohol;
    private boolean consumedTobaco;
    private boolean addictives;
    private boolean mediciense;

    @ManyToOne
    private User user;
}
