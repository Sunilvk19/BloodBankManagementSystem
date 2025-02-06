package com.example.bsm.requestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SureyRequest {
    private boolean preMedicalCondition;
    private boolean consumedAlcohol;
    private boolean consumedTobaco;
    private boolean addictives;
    private boolean mediciense;
}
