package com.example.bsm.requestdto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class AddressRequest {
    private String addressline;
    private String landmark;
    private String area;
    private String city;
    private String state;
    private String country;
    private int pincode;
}
