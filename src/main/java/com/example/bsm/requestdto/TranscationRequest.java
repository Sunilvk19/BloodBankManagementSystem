package com.example.bsm.requestdto;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranscationRequest {
    private int noOfUnit;
    private BloodGroup bloodGroup;
    private TransactionType transactionType;
}
