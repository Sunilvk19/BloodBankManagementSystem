package com.example.bsm.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BloodBankResponse {

    private int bloodbankId;
    private String bankName;
    private int emergencyUnitCount;
}
