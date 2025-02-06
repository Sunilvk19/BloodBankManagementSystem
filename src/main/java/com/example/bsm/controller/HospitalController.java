package com.example.bsm.controller;

import com.example.bsm.requestdto.HospitalRequest;
import com.example.bsm.responsedto.HospitalResponse;
import com.example.bsm.service.HospitalService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;
    private final RestResponseBuilder restResponseBuilder;

    @PostMapping("/hospitals")
    @PreAuthorize("hasAuthority('OWNER_ADMIN')")
    public ResponseEntity<ResponseStructure<HospitalResponse>> registerHospital(@Valid  @RequestBody HospitalRequest hospitalRequest,  int adminid){
        HospitalResponse hospitalResponse = hospitalService.registerHospital(hospitalRequest,adminid);
        return restResponseBuilder.success(HttpStatus.CREATED,"HospitalCreated",hospitalResponse);
    }

    @GetMapping("/hospitals/{hospitalid}")
    public ResponseEntity<ResponseStructure<HospitalResponse>> findHospitalById(@PathVariable int hospitalid){
        HospitalResponse hospitalResponse = hospitalService.findHospitalById(hospitalid);
        return restResponseBuilder.success(HttpStatus.FOUND,"Hospital Found",hospitalResponse);
    }
    @PutMapping("hospitals/{hospitalid}")
    public ResponseEntity<ResponseStructure<HospitalResponse>> updateHospital(@RequestBody HospitalRequest hospitalRequest,
                                                                              @PathVariable int hospitalid){
        HospitalResponse hospitalResponse = hospitalService.updateHopital(hospitalRequest,hospitalid);
        return restResponseBuilder.success(HttpStatus.OK,"Updated Hospital",hospitalResponse);
    }
    @DeleteMapping("/hospitals")
    public ResponseEntity<ResponseStructure<HospitalResponse>> deleteHospitalById(@RequestParam int HospitalId){
        HospitalResponse d=hospitalService.deleteHospitalById(HospitalId);
        return restResponseBuilder.success(HttpStatus.FOUND,"Hospital Deleted",d);
    }


}
