package com.example.bsm.requestdto;

import com.example.bsm.enums.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonationRequestReq {
    private LocalDate date;
    private LocalTime time;
    private List<BloodGroup> bloodGroupList;
    private List<String> cities;
}
