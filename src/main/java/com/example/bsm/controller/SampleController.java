package com.example.bsm.controller;

import com.example.bsm.requestdto.SampleRequest;
import com.example.bsm.responsedto.SampleResponse;
import com.example.bsm.service.SampleService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class SampleController {
    private final SampleService sampleService;
    private final RestResponseBuilder restResponseBuilder;
    @PostMapping("/samples")
    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN')")
    public ResponseEntity<ResponseStructure<SampleResponse>> registersample(@Valid @RequestBody SampleRequest sampleRequest, int bloodbankid){
        SampleResponse sampleResponse = sampleService.registersample(sampleRequest,bloodbankid);
        return restResponseBuilder.success(HttpStatus.CREATED,"sample created",sampleResponse);
    }
    @GetMapping("/samples/{sampleid}")
    public ResponseEntity<ResponseStructure<SampleResponse>> findall(@PathVariable int sampleid){
        SampleResponse sampleResponse = sampleService.findall(sampleid);
        return restResponseBuilder.success(HttpStatus.FOUND,"sample found",sampleResponse);
    }
}
