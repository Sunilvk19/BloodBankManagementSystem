package com.example.bsm.serviceimpl;

import com.example.bsm.entity.*;
import com.example.bsm.enums.TransactionType;
import com.example.bsm.exception.HospitalNotFoundByIdException;
import com.example.bsm.exception.TransactionNotFoundByIdException;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.repository.TransactionRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.requestdto.TranscationRequest;
import com.example.bsm.responsedto.TranscationResponse;
import com.example.bsm.security.AuthUtil;
import com.example.bsm.service.TranctionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TranscationServiceImpl implements TranctionService {

    private final TransactionRepository transactionRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;
    private final AuthUtil authUtil;

    private TranscationResponse mapToResponse(Transcation transcation) {
        return TranscationResponse.builder()
                .transcationId(transcation.getTranscationId())
                .Date(transcation.getDate())
                .Time(transcation.getTime())
                .noOfUnit(transcation.getNoofUnit())
                .build();
    }

    private Transcation mapToTranscation(TranscationRequest transcationRequest, Transcation transcation) {
        transcation.setBloodGroup(transcationRequest.getBloodGroup());
        transcation.setTransactionType(transcationRequest.getTransactionType());
        transcation.setNoofUnit(transcation.getNoofUnit());

        return transcation;
    }


    @Override
    public TranscationResponse transction(TranscationRequest transcationRequest, int hospitalid, int userid) {
        Optional<User> optional = userRepository.findById(userid);
        Optional<Hospital> optional1 = hospitalRepository.findById(hospitalid);
        if (optional.isEmpty()) {
            throw new UserNotFoundByIdException("Transcation not done");
        }
        if (optional.isEmpty()) {
            throw new HospitalNotFoundByIdException("Transcation not done");
        }
        User user = optional.get();
        Hospital hospital = optional1.get();

        Admin admin = authUtil.getCurrentAdmin();
        BloodBank bloodBank = admin.getBloodBank();
        Transcation transcation = this.mapToTranscation(transcationRequest, new Transcation());

        List<Sample> samples = bloodBank.getSample();

        Sample sample = null;
        for (Sample sample1 : samples) {
            if (sample1.getBloodGroup() == transcation.getBloodGroup()) {
                sample = sample1;
            }
        }
        if (sample == null) {
            throw new TransactionNotFoundByIdException("Failed");
        }
        int i = sample.getAvailableUnits();
        if (transcation.getTransactionType() == TransactionType.NORMAL) {
            if (transcation.getNoofUnit() > i) {
                throw new TransactionNotFoundByIdException("1 Failed");
            } else {
                sample.setAvailableUnits(i - transcation.getNoofUnit());
            }
        } else {
            if (transcation.getNoofUnit() > i) {
                if (transcation.getNoofUnit() > i + sample.getEmergencyUnits()) {
                    throw new TransactionNotFoundByIdException("2 Failed");
                } else {
                    sample.setAvailableUnits(0);
                    sample.setEmergencyUnits(sample.getEmergencyUnits() - transcation.getNoofUnit() + sample.getAvailableUnits());
                }
            } else {
                sample.setAvailableUnits(i - transcation.getNoofUnit());
            }
            transcation.setUser(user);
            transcation.setHospital(hospital);
            transcation.setBloodBank(bloodBank);
            transactionRepository.save(transcation);
        }
        return this.mapToResponse(transcation);
    }

}

