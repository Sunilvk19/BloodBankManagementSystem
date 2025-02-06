package com.example.bsm.controller;

import com.example.bsm.requestdto.TranscationRequest;
import com.example.bsm.responsedto.TranscationResponse;
import com.example.bsm.service.TranctionService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TransactionController {

    private TranctionService tranctionService;
    private RestResponseBuilder restResponseBuilder;

    public ResponseEntity<ResponseStructure<TranscationResponse>>transction(@RequestBody TranscationRequest transcationRequest, @PathVariable int hospitalid,int userid){
        TranscationResponse transcationResponse = tranctionService.transction(transcationRequest,hospitalid,userid);
        return restResponseBuilder.success(HttpStatus.CREATED,"transcation done",transcationResponse);
    }
}
