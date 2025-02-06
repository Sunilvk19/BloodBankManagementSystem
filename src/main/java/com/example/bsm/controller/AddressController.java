package com.example.bsm.controller;

import com.example.bsm.requestdto.AddressRequest;
import com.example.bsm.responsedto.AddressResponse;
import com.example.bsm.service.AddressService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AddressController {

    private final AddressService addressService;
    private final RestResponseBuilder restResponseBuilder;

    @PostMapping("/address")
    public ResponseEntity<ResponseStructure<AddressResponse>> registeruseraddress(@RequestBody AddressRequest addressRequest){
        AddressResponse addressResponse = addressService.registeruseraddress(addressRequest);
        return restResponseBuilder.success(HttpStatus.CREATED,"user address created",addressResponse);
    }
    @PostMapping("/hospitals/{hopitalid}")
    public ResponseEntity<ResponseStructure<AddressResponse>> registerhospitaladdress(@RequestBody AddressRequest addressRequest, @PathVariable int hospitalid){
        AddressResponse addressResponse = addressService.registerhospitaladdress(addressRequest,hospitalid);
        return restResponseBuilder.success(HttpStatus.CREATED,"Hospital address created",addressResponse);
    }
    @PostMapping("/bloodbanks/{bloodbankid}")
    public ResponseEntity<ResponseStructure<AddressResponse>> registerbloodbankaddress(@RequestBody AddressRequest addressRequest,@PathVariable int bloodbankid){
        AddressResponse addressResponse = addressService.registerbloodbankaddress(addressRequest,bloodbankid);
        return restResponseBuilder.success(HttpStatus.CREATED,"BloodBank Created", addressResponse);
    }
}
