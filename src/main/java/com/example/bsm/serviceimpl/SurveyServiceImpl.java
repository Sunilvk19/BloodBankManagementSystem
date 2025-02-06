package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Survey;
import com.example.bsm.entity.User;
import com.example.bsm.enums.Role;
import com.example.bsm.exception.SurveyNotFoundByIdException;
import com.example.bsm.repository.SurveyRepository;
import com.example.bsm.requestdto.SureyRequest;
import com.example.bsm.responsedto.SureyResponse;
import com.example.bsm.security.AuthUtil;
import com.example.bsm.service.SurveyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;
    private final AuthUtil authUtil;

    private SureyResponse mapToResponse(Survey survey){
        return SureyResponse.builder()
                .surveyid(survey.getSurveyid())
                .preMedicalCondition(survey.isPreMedicalCondition())
                .consumedAlcohol(survey.isConsumedAlcohol())
                .consumedTobaco(survey.isConsumedTobaco())
                .addictives(survey.isAddictives())
                .mediciense(survey.isMediciense())
                .build();
    }
    private Survey mapToRequest(SureyRequest sureyRequest,Survey survey){
        survey.setPreMedicalCondition(sureyRequest.isPreMedicalCondition());
        survey.setConsumedAlcohol(sureyRequest.isConsumedAlcohol());
        survey.setConsumedTobaco(sureyRequest.isConsumedTobaco());
        survey.setAddictives(sureyRequest.isAddictives());
        survey.setMediciense(sureyRequest.isMediciense());
        return survey;
    }
    @Override
    public SureyResponse registersurvey(SureyRequest sureyRequest) {
        User user = authUtil.getCurrentUser();
        Survey survey = this.mapToRequest(sureyRequest,new Survey());
        survey.setUser(user);
        surveyRepository.save(survey);

        return this.mapToResponse(survey);
    }

    @Override
    public SureyResponse findbyid(int surveyid) {
        Optional<Survey> optional = surveyRepository.findById(surveyid);
        if(optional.isEmpty()){
            throw new SurveyNotFoundByIdException("Not found");
        }
        Survey survey = optional.get();

        return this.mapToResponse(survey);
    }

    @Override
    public SureyResponse updatesurvey(SureyRequest sureyRequest, int surveyid) {
        Optional<Survey> optional = surveyRepository.findById(surveyid);
        if (optional.isEmpty()){
            throw new SurveyNotFoundByIdException("Not updated");
        }
        Survey survey = this.mapToRequest(sureyRequest,optional.get());
        survey = surveyRepository.save(survey);
        return this.mapToResponse(survey);
    }
}
