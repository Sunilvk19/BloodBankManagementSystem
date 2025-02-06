package com.example.bsm.service;

import com.example.bsm.requestdto.TranscationRequest;
import com.example.bsm.responsedto.TranscationResponse;

public interface TranctionService {

    TranscationResponse transction(TranscationRequest transcationRequest,int hospitalid,int userid);
}
