package com.example.bsm.entity;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transcation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transcationId;
    private LocalDate Date;
    private LocalTime Time;
    private int noofUnit;
    private BloodGroup bloodGroup;

    private TransactionType transactionType;

    @ManyToOne
    private User user;

    @ManyToOne
    private Hospital hospital;

    @ManyToOne
    private BloodBank bloodBank;


}
