package com.example.bsm.controller;

import com.example.bsm.requestdto.SureyRequest;
import com.example.bsm.responsedto.SureyResponse;
import com.example.bsm.service.SurveyService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class SurveyController {
    private final SurveyService service;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/sregisters")
    public ResponseEntity<ResponseStructure<SureyResponse>> registersurvey(@RequestBody SureyRequest sureyRequest){
        SureyResponse sureyResponse = service.registersurvey(sureyRequest);
        return responseBuilder.success(HttpStatus.CREATED,"survey created",sureyResponse);
    }
    @GetMapping("/surveys{surveyid}")
    public ResponseEntity<ResponseStructure<SureyResponse>> findbyid(@PathVariable int surveyid){
        SureyResponse sureyResponse = service.findbyid(surveyid);
        return responseBuilder.success(HttpStatus.FOUND,"survey found",sureyResponse);
    }
    @PutMapping("/sureys")
    public ResponseEntity<ResponseStructure<SureyResponse>> updatesurvey(@RequestBody SureyRequest sureyRequest,@PathVariable int surveyid){
        SureyResponse sureyResponse = service.updatesurvey(sureyRequest,surveyid);
        return responseBuilder.success(HttpStatus.OK,"survey updated",sureyResponse);
    }
}
