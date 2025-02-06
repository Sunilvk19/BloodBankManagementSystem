package com.example.bsm.service;

import com.example.bsm.entity.User;
import com.example.bsm.requestdto.UserRequest;
import com.example.bsm.responsedto.AdminResponse;
import com.example.bsm.responsedto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse registerUser(UserRequest userRequest);

    UserResponse findUserById();

    UserResponse updateUser(UserRequest userRequest);

    UserResponse usertoadmin(int userid);

    AdminResponse registertoadmin(UserRequest userRequest);

    UserResponse verifieduser(int userid, boolean isveryfied);
}
