package com.example.bsm.service;

import com.example.bsm.requestdto.SampleRequest;
import com.example.bsm.responsedto.SampleResponse;
import jakarta.validation.Valid;

public interface SampleService {

    SampleResponse registersample(@Valid SampleRequest sampleRequest,int bloodbankid);

    SampleResponse findall(int sampleid);
}
