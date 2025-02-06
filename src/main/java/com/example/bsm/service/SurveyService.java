package com.example.bsm.service;

import com.example.bsm.requestdto.SureyRequest;
import com.example.bsm.responsedto.SureyResponse;

public interface SurveyService {

    SureyResponse registersurvey(SureyRequest sureyRequest);

    SureyResponse findbyid(int surveyid);

    SureyResponse updatesurvey(SureyRequest sureyRequest, int surveyid);
}
