package com.example.bsm.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BloodBankPageResponse {
    private int bloodbankid;
    private String bankName;
    private AddressResponse address;
    private List<SampleResponse> sample;
}
