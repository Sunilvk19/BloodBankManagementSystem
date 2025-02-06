package com.example.bsm.responsedto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AddressResponse {
    private int addressid;
    private String addressline;
    private String landmark;
    private String area;
    private String city;
    private String state;
    private String country;
    private int pincode;
}
