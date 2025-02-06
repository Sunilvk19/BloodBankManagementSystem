package com.example.bsm.serviceimpl;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.DonationRequest;
import com.example.bsm.entity.Hospital;
import com.example.bsm.enums.OrganazationType;
import com.example.bsm.exception.BloodBankNotFoundByIdException;
import com.example.bsm.exception.HospitalNotFoundByIdException;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.repository.DonationRequestRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.requestdto.DonationRequestReq;
import com.example.bsm.responsedto.DonationRequestResponse;
import com.example.bsm.service.DonationRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DonationRequestServiceImpl implements DonationRequestService {
    private final DonationRequestRepository donationreqrepository;
    private final HospitalRepository hospitalRepository;
    private final BloodBankRepository bankRepository;
    //Map Methods
    private DonationRequestResponse mapToResponse(DonationRequest donationRequest){
        return DonationRequestResponse.builder()
                .donationid(donationRequest.getDonationid())
                .date(donationRequest.getDate())
                .time(donationRequest.getTime())
                .bloodGroupList(donationRequest.getBloodGroupList())
                .build();
    }
    private DonationRequest mapToRequest(DonationRequestReq donationRequestReq, DonationRequest donationRequest){
        donationRequest.setDate(donationRequestReq.getDate());
        donationRequest.setTime(donationRequestReq.getTime());
        donationRequest.setBloodGroupList(donationRequestReq.getBloodGroupList());
        return donationRequest;
    }

    @Override
    public DonationRequestResponse registerdonationbyhospital(DonationRequestReq donationrequest, int hospitalid) {

        Optional<Hospital> optional = hospitalRepository.findById(hospitalid);
        if(optional.isEmpty()){
            throw new HospitalNotFoundByIdException("No found");
        }
        Hospital hospital = optional.get();
        DonationRequest donationRequest = this.mapToRequest(donationrequest,new DonationRequest());
        donationRequest.setOrgType(OrganazationType.HOPITAL);
        donationreqrepository.save(donationRequest);
        return this.mapToResponse(donationRequest);
    }

    @Override
    public DonationRequestResponse registerdonationbybank(DonationRequestReq donationrequest, int bloodbankid) {

        Optional<BloodBank> optional = bankRepository.findById(bloodbankid);
        if(optional.isEmpty()){
            throw new BloodBankNotFoundByIdException("Not Found");
        }
        BloodBank bloodBank = optional.get();
        DonationRequest donationRequest = this.mapToRequest(donationrequest,new DonationRequest());
        donationRequest.setOrgType(OrganazationType.BLOODBANK);
        donationreqrepository.save(donationRequest);
        return this.mapToResponse(donationRequest);
    }
}
