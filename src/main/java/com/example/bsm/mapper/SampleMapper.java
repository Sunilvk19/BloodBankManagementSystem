package com.example.bsm.mapper;

import com.example.bsm.entity.Sample;
import com.example.bsm.requestdto.SampleRequest;
import com.example.bsm.responsedto.SampleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SampleMapper {
    public SampleResponse mapToResponse(Sample sample){
        return SampleResponse.builder()
                .sampleid(sample.getSampleid())
                .quantity(sample.getQuantity())
                .build();
    }
    public Sample mapToSample(SampleRequest sampleRequest, Sample sample){
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
}
