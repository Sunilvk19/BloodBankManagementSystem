package com.example.bsm.serviceimpl;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.Sample;
import com.example.bsm.exception.BloodBankNotFoundByIdException;
import com.example.bsm.exception.SampleNotFoundByIdException;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.repository.SampleRepository;
import com.example.bsm.requestdto.SampleRequest;
import com.example.bsm.responsedto.SampleResponse;
import com.example.bsm.service.SampleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SampleServiceImpl implements SampleService {
    private final SampleRepository sampleRepository;
    private final BloodBankRepository bloodBankRepository;

    private SampleResponse mapToResponse(Sample sample){
        return SampleResponse.builder()
                .sampleid(sample.getSampleid())
                .quantity(sample.getQuantity())
                .build();
    }
    private Sample mapToSample(SampleRequest sampleRequest, Sample sample){
            sample.setBloodGroup(sampleRequest.getBloodGroup());
            sample.setQuantity(sampleRequest.getQuantity());
            if(sampleRequest.getQuantity() < sample.getEmergencyUnits()){
                sample.setEmergencyUnits(sample.getQuantity());
            } else if (sampleRequest.getQuantity() == sample.getEmergencyUnits()) {
                return sample;
            }else {
                sample.setAvailableUnits(sampleRequest.getQuantity() - sample.getEmergencyUnits());
                return sample;
            }
        return sample;
    }
    @Override
    public SampleResponse registersample(SampleRequest sampleRequest, int bloodbankid) {
        Optional<BloodBank> optional = bloodBankRepository.findById(bloodbankid);
        if(optional.isEmpty()){
            throw new BloodBankNotFoundByIdException("Blood bank not found");
        }
        BloodBank bloodBank = optional.get();
        Sample sample = this.mapToSample(sampleRequest,new Sample());
        sample.setEmergencyUnits(bloodBank.getEmergencyUnitCount());
        bloodBank = bloodBankRepository.save(bloodBank);
        sample.setBloodBank(bloodBank);
        sampleRepository.save(sample);
        return this.mapToResponse(sample);

    }

    @Override
    public SampleResponse findall(int sampleid) {
        Optional<Sample> optional = sampleRepository.findById(sampleid);
        if(optional.isEmpty()){
            throw new SampleNotFoundByIdException("failed to find sample");
        }
        Sample sample = optional.get();
        return this.mapToResponse(sample);
    }
}
