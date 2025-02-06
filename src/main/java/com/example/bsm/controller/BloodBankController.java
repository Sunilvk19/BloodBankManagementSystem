package com.example.bsm.controller;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.enums.BloodGroup;
import com.example.bsm.requestdto.BloodBankRequest;
import com.example.bsm.responsedto.BloodBankPageResponse;
import com.example.bsm.responsedto.BloodBankResponse;
import com.example.bsm.service.BloodBankService;
import com.example.bsm.utility.PageStructure;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class BloodBankController {
    private final BloodBankService bloodBankService;
    private final RestResponseBuilder restResponseBuilder;
    private final List<String> strings;

    @PostMapping("/bloodbanks")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> registerbloodbank(@RequestBody @Valid BloodBankRequest bloodBankRequest, int adminid){
        BloodBankResponse bloodBankResponse = bloodBankService.registerbloodbank(bloodBankRequest,adminid);
        return restResponseBuilder.success(HttpStatus.CREATED,"BloodBank created",bloodBankResponse);
    }
    @GetMapping("/bloodbanks/{bloodbankid}")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> findbloodbankbyId(@PathVariable int bloodbankId){
        BloodBankResponse bloodBankResponse = bloodBankService.findbloodbankbyId(bloodbankId);
        return restResponseBuilder.success(HttpStatus.FOUND,"Bloodbank is found",bloodBankResponse);
    }

    @GetMapping("/bloodbanks")
    public ResponseEntity<PageStructure<Page<BloodBank>>> findAllbloodbank(@RequestParam List<String> location, BloodGroup bloodGroups, int page, int size){
        Page<BloodBank> bloodBank = bloodBankService.findAllbloodbank(location,bloodGroups,page,size);
        List<BloodBankPageResponse> bloodBankPage = bloodBankService.genratebloodbankpageresponse(bloodGroups,bloodBank);
        return restResponseBuilder.success(HttpStatus.FOUND,"BloodBankFound",bloodBank, bloodBank.getNumber(), bloodBank.getTotalPages(),bloodBank.getSize());
    }
    @PutMapping("/bloodbanks/{bloodbankid}")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> updateBloodBank(@RequestBody BloodBankRequest bloodBankRequest,
                                                                                @PathVariable int bloodbankId){
        BloodBankResponse bloodBankResponse = bloodBankService.updateBloodBank(bloodBankRequest,bloodbankId);
        return restResponseBuilder.success(HttpStatus.OK,"Updated blood bank",bloodBankResponse);
    }
}
