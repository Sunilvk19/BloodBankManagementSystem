package com.example.bsm.controller;

import com.example.bsm.requestdto.DonationRequestReq;
import com.example.bsm.responsedto.DonationRequestResponse;
import com.example.bsm.service.DonationRequestService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class DonationRequestController {

    private final DonationRequestService donationservice;
    private final RestResponseBuilder responseBuilder;
    @PostMapping("/donation/hopitals")
    public ResponseEntity<ResponseStructure<DonationRequestResponse>> registerdonationbyhospital(@RequestBody DonationRequestReq donationrequest, @PathVariable int hospitalid){
        DonationRequestResponse donationRequestResponse = donationservice.registerdonationbyhospital(donationrequest,hospitalid);
        return responseBuilder.success(HttpStatus.CREATED,"donation done",donationRequestResponse);
    }
    @PostMapping("/donation")
    public ResponseEntity<ResponseStructure<DonationRequestResponse>> registerdonationbybank(@RequestBody DonationRequestReq donationrequest, @PathVariable int bloodbankid){
        DonationRequestResponse donationRequestResponse = donationservice.registerdonationbybank(donationrequest,bloodbankid);
        return responseBuilder.success(HttpStatus.CREATED,"donation done",donationRequestResponse);
    }
}
