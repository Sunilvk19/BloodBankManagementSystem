package com.example.bsm.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SureyResponse {
    private int surveyid;
    private boolean preMedicalCondition;
    private boolean consumedAlcohol;
    private boolean consumedTobaco;
    private boolean addictives;
    private boolean mediciense;
}
