package com.example.bsm.service;

import com.example.bsm.requestdto.HospitalRequest;
import com.example.bsm.responsedto.HospitalResponse;

public interface HospitalService {
    HospitalResponse registerHospital(HospitalRequest hospitalRequest,int adminid);

    HospitalResponse findHospitalById(int hospitalid);

    HospitalResponse updateHopital(HospitalRequest hospitalRequest, int hospitalid);

    HospitalResponse deleteHospitalById(int hospitalId);
}
