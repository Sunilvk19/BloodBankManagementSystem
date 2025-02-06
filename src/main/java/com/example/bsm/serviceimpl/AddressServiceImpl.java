package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Address;
import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.Hospital;
import com.example.bsm.entity.User;
import com.example.bsm.exception.BloodBankNotFoundByIdException;
import com.example.bsm.exception.HospitalNotFoundByIdException;
import com.example.bsm.repository.AddressRepository;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.requestdto.AddressRequest;
import com.example.bsm.responsedto.AddressResponse;
import com.example.bsm.security.AuthUtil;
import com.example.bsm.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AuthUtil authUtil;
    private final HospitalRepository hospitalRepository;
    private final BloodBankRepository bloodBankRepository;

    private AddressResponse mapToResponse(Address address){
        return AddressResponse.builder()
                .addressid(address.getAddressid())
                .addressline(address.getAddressline())
                .area(address.getArea())
                .state(address.getState())
                .city(address.getCity())
                .landmark(address.getLandmark())
                .pincode(address.getPincode())
                .build();
    }
    private Address mapToAddress(AddressRequest addressRequest, Address address){
            address.setAddressline(addressRequest.getAddressline());
            address.setCountry(addressRequest.getCountry());
            address.setCity(address.getCity());
            address.setLandmark(addressRequest.getLandmark());
            address.setState(addressRequest.getState());
            address.setPincode(addressRequest.getPincode());
            return address;
    }


    @Override
    public AddressResponse registeruseraddress(AddressRequest addressRequest) {
        User user = authUtil.getCurrentUser();
        Address address = new Address();
        address = this.mapToAddress(addressRequest,address);
        userRepository.save(user);
        addressRepository.save(address);
        return this.mapToResponse(address);
    }

    @Override
    public AddressResponse registerhospitaladdress(AddressRequest addressRequest, int hospitalid) {
        Optional<Hospital> optional = hospitalRepository.findById(hospitalid);
        if(optional.isEmpty()){
            throw new HospitalNotFoundByIdException("Hospital not found");
        }
       Hospital hospital = optional.get();
        Address address = this.mapToAddress(addressRequest,new Address());
        addressRepository.save(address);
        hospitalRepository.save(hospital);

        return this.mapToResponse(address);
    }

    @Override
    public AddressResponse registerbloodbankaddress(AddressRequest addressRequest, int bloodbankid) {
        Optional<BloodBank> optional = bloodBankRepository.findById(bloodbankid);
        if(optional.isEmpty()){
            throw new BloodBankNotFoundByIdException("BloodBank not found");
        }
        BloodBank bloodBank = optional.get();

        Address address = this.mapToAddress(addressRequest,new Address());
        addressRepository.save(address);
        bloodBankRepository.save(bloodBank);

        return this.mapToResponse(address);
    }
}
