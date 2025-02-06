package com.example.bsm.entity;

import com.example.bsm.responsedto.AddressResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressid;
    private String addressline;
    private String landmark;
    private String area;
    private String city;
    private String state;
    private String country;
    private int pincode;

    @OneToOne
    private BloodBank bloodBank;
}
