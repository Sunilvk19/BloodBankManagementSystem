package com.example.bsm.service;

import com.example.bsm.requestdto.AddressRequest;
import com.example.bsm.responsedto.AddressResponse;

public interface AddressService {



    AddressResponse registeruseraddress(AddressRequest addressRequest);

    AddressResponse registerhospitaladdress(AddressRequest addressRequest, int hospitalid);

    AddressResponse registerbloodbankaddress(AddressRequest addressRequest, int bloodbankid);
}
