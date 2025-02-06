package com.example.bsm.serviceimpl;

import com.example.bsm.entity.*;
import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Role;
import com.example.bsm.exception.BloodBankNotFoundByIdException;
import com.example.bsm.mapper.AddressMapper;
import com.example.bsm.mapper.SampleMapper;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.repository.SampleRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.requestdto.BloodBankRequest;
import com.example.bsm.responsedto.BloodBankPageResponse;
import com.example.bsm.responsedto.BloodBankResponse;
import com.example.bsm.responsedto.SampleResponse;
import com.example.bsm.service.BloodBankService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.bsm.enums.BloodGroup.*;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class BloodBankServiceImpl implements BloodBankService {
    private final BloodBankRepository bloodBankRepository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;
    private final SampleMapper sampleMapper;
    private final SampleRepository sampleRepository;

    private BloodBankResponse mapToBloodBankResponse(BloodBank bloodBank){
        return BloodBankResponse.builder()
                .bloodbankId(bloodBank.getBloodbankId())
                .bankName(bloodBank.getBankName())
                .emergencyUnitCount(bloodBank.getEmergencyUnitCount())
                .build();
    }
    private BloodBank mapToBloodBank(BloodBankRequest bloodBankRequest, BloodBank bloodBank){
        bloodBank.setBankName(bloodBankRequest.getBankName());
        bloodBank.setEmergencyUnitCount(bloodBankRequest.getEmergencyUnitCount());

        return bloodBank;
    }
    @Override
    public List<BloodBankPageResponse> genratebloodbankpageresponse(BloodGroup bloodGroup, Page<BloodBank> bloodBankList) {
        List<BloodBank> bloodBankList1 = bloodBankList.toList();
        List<BloodBankPageResponse> bloodBankPageResponses = new ArrayList<>();
        for (BloodBank bloodBank : bloodBankList1){
            List<Sample> samples = sampleRepository.findByBloodBankAndBloodGroup(bloodBank, bloodGroup);
            List<SampleResponse> sampleResponses = new ArrayList<>();
            for (Sample sample : samples){
                sampleResponses.add(sampleMapper.mapToResponse(sample));
            }
            bloodBankPageResponses.add(mapper(bloodBank,sampleResponses));
        }
        return bloodBankPageResponses;
    }
    private BloodBankPageResponse mapper(BloodBank bloodBank1,List<SampleResponse> sampleResponses) {
        return  BloodBankPageResponse.builder().bloodbankid(bloodBank1.getBloodbankId())
                .bankName(bloodBank1.getBankName())
                .address(addressMapper.mapToResponse(new Address()))
                .sample(sampleResponses)
                .build();
    }

    @Override
    public BloodBankResponse registerbloodbank(BloodBankRequest bloodBankRequest, int adminid) {
        Optional<Admin> optional = adminRepository.findById(adminid);
        if(optional.isEmpty()){
            throw new RuntimeException("Failed to fetch the admin");
        }
        Admin admin = optional.get();
        User user = admin.getUser();
        user.setRole(Role.OWNER_ADMIN);
        List<Admin> adminList = new ArrayList<>();
        adminList.add(admin);
        BloodBank bloodBank = this.mapToBloodBank(bloodBankRequest,new BloodBank());
        bloodBank.setAdminList(adminList);
        bloodBank = bloodBankRepository.save(bloodBank);
        userRepository.save(user);
        adminRepository.save(admin);
        return this.mapToBloodBankResponse(bloodBank);
    }

    @Override
    public BloodBankResponse findbloodbankbyId(int bloodbankId) {
        Optional<BloodBank> optional = bloodBankRepository.findById(bloodbankId);
        if(optional.isEmpty()){
            throw new BloodBankNotFoundByIdException("BloodBank not found by id");
        }
        BloodBank bloodBank = optional.get();
        return this.mapToBloodBankResponse(bloodBank);
    }

    @Override
    public BloodBankResponse updateBloodBank(BloodBankRequest bloodBankRequest, int bloodbankId) {
        Optional<BloodBank> optional = bloodBankRepository.findById(bloodbankId);
        if(optional.isEmpty()){
            throw new BloodBankNotFoundByIdException("Blood bank is not found");
        }
        BloodBank bloodBank = this.mapToBloodBank(bloodBankRequest, optional.get());
        bloodBankRepository.save(bloodBank);
        return this.mapToBloodBankResponse(bloodBank);
    }

    @Override
    public Page<BloodBank> findAllbloodbank(List<String> location, BloodGroup bloodGroup,int page, int pagesize) {
        List<BloodGroup> l=new ArrayList<>();
        switch (bloodGroup){
            case  A_POSITIVE:l.add(A_POSITIVE);
                l.add(O_NEGATIVE);l.add(O_POSITIVE);l.add(A_NEGATIVE);break;
            case A_NEGATIVE:
                l.add(O_NEGATIVE);l.add(A_NEGATIVE);break;
            case B_POSITIVE:
                l.add(B_POSITIVE);
                l.add(O_NEGATIVE);l.add(O_POSITIVE);l.add(B_NEGATIVE);break;
            case B_NEGATIVE:
                l.add(O_NEGATIVE);l.add(B_NEGATIVE);break;
            case O_NEGATIVE:
                l.add(O_NEGATIVE);break;
            case O_POSITIVE:
                l.add(O_NEGATIVE);l.add(O_POSITIVE);break;
            case AB_NEGATIVE:l.add(A_POSITIVE);l.add(AB_NEGATIVE);l.add(AB_POSITIVE);
                l.add(O_NEGATIVE);l.add(O_POSITIVE);l.add(A_NEGATIVE);break;
            default:
                break;
        }

        Pageable pageable = PageRequest.of(page,pagesize);
        Page<BloodBank> bloodBankList = bloodBankRepository.findByAddress_CityInAndSample_BloodGroup(location,bloodGroup,pageable);
        if(bloodBankList.isEmpty()){
            throw new BloodBankNotFoundByIdException("Not Found");
        }
        List<BloodBankPageResponse> bloodBankPageResponses = genratebloodbankpageresponse(bloodGroup, bloodBankList);
        return bloodBankList;
    }



}
