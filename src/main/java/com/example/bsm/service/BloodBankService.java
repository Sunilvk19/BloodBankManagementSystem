package com.example.bsm.service;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.enums.BloodGroup;
import com.example.bsm.requestdto.BloodBankRequest;
import com.example.bsm.responsedto.BloodBankPageResponse;
import com.example.bsm.responsedto.BloodBankResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BloodBankService {

    BloodBankResponse registerbloodbank(BloodBankRequest bloodBankRequest, int adminid);

    BloodBankResponse findbloodbankbyId(int bloodbankId);

    BloodBankResponse updateBloodBank(BloodBankRequest bloodBankRequest, int bloodbankId);

    Page<BloodBank> findAllbloodbank(List<String> location, BloodGroup bloodGroups, int page, int pagesize);

    public List<BloodBankPageResponse> genratebloodbankpageresponse(BloodGroup bloodGroup, Page<BloodBank> bloodBankList);
}
