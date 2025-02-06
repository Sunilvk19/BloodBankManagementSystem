package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Admin;
import com.example.bsm.entity.Hospital;
import com.example.bsm.entity.User;
import com.example.bsm.enums.Role;
import com.example.bsm.exception.HospitalNotFoundByIdException;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.requestdto.HospitalRequest;
import com.example.bsm.responsedto.HospitalResponse;
import com.example.bsm.service.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    private HospitalResponse mapToHospitalResponse(Hospital hospital){
        return HospitalResponse.builder()
                .hospitalid(hospital.getHospitalid())
                        .name(hospital.getName())
                .build();
    }
    private Hospital mapToHospital(HospitalRequest hospitalRequest, Hospital hospital){
        hospital.setName(hospitalRequest.getName());
        return hospital;
    }
    @Override
    public HospitalResponse registerHospital(HospitalRequest hospitalRequest,int adminid) {
        Optional<Admin> optional = adminRepository.findById(adminid);
        if(optional.isEmpty()) {
            throw new RuntimeException("Failed to fetch the admin");
        }
        Admin admin = optional.get();
        List<Admin> adminList = new ArrayList<>();
        adminList.add(admin);
        Hospital hospital=new Hospital();
        hospital.setAdmin(adminList);
        hospital = this.mapToHospital(hospitalRequest,new Hospital());

        hospital = hospitalRepository.save(hospital);
        User user = admin.getUser();

        userRepository.save(user);

        return this.mapToHospitalResponse(hospital);
    }

    @Override
    public HospitalResponse findHospitalById(int hospitalId) {
        Optional<Hospital> optional = hospitalRepository.findById(hospitalId);
        if(optional.isEmpty()){
            throw new HospitalNotFoundByIdException("hospital not found by id");
        }
        Hospital hospital = optional.get();
        return this.mapToHospitalResponse(hospital);
    }

    @Override
    public HospitalResponse updateHopital(HospitalRequest hospitalRequest, int hospitalId) {
        Optional<Hospital> optional = hospitalRepository.findById(hospitalId);
        if(optional.isEmpty()) {
            throw new HospitalNotFoundByIdException("Hospital not found ");
        }else {
            Hospital hospital = this.mapToHospital(hospitalRequest, optional.get());
            hospitalRepository.save(hospital);
            return this.mapToHospitalResponse(hospital);
        }
    }

    @Override
    public HospitalResponse deleteHospitalById(int hospitalId) {
        Optional<Hospital> optional = hospitalRepository.findById(hospitalId);
        if(optional.isEmpty()){
            Hospital hospital = optional.get();
            hospitalRepository.delete(hospital);
            return this.mapToHospitalResponse(hospital);
        }else {
            throw new RuntimeException("Failed to delete hospital");
        }
    }
}
