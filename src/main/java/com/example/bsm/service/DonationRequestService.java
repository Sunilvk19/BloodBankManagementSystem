package com.example.bsm.service;

import com.example.bsm.requestdto.DonationRequestReq;
import com.example.bsm.responsedto.DonationRequestResponse;

public interface DonationRequestService {

    DonationRequestResponse registerdonationbyhospital(DonationRequestReq donationrequest, int hospitalid);

    DonationRequestResponse registerdonationbybank(DonationRequestReq donationrequest, int bloodbankid);
}
