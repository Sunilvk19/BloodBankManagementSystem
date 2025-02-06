package com.example.bsm.mapper;

import com.example.bsm.entity.Address;
import com.example.bsm.requestdto.AddressRequest;
import com.example.bsm.responsedto.AddressResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressMapper {
    public AddressResponse mapToResponse(Address address){
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
    public Address mapToAddress(AddressRequest addressRequest, Address address){
        address.setAddressline(addressRequest.getAddressline());
        address.setCountry(addressRequest.getCountry());
        address.setCity(address.getCity());
        address.setLandmark(addressRequest.getLandmark());
        address.setState(addressRequest.getState());
        address.setPincode(addressRequest.getPincode());
        return address;
    }
}
