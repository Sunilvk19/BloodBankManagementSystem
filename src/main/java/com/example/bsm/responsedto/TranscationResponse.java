package com.example.bsm.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TranscationResponse {
    private int transcationId;
    private LocalDate Date;
    private LocalTime Time;
    private int noOfUnit;
}
