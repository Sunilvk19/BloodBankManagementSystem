package com.example.bsm.requestdto;

import com.example.bsm.enums.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SampleRequest {
    private BloodGroup bloodGroup;
    private int quantity;
}
