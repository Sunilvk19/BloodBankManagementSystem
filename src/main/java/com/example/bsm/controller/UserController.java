package com.example.bsm.controller;

import com.example.bsm.entity.User;
import com.example.bsm.requestdto.UserRequest;
import com.example.bsm.responsedto.AdminResponse;
import com.example.bsm.responsedto.UserResponse;
import com.example.bsm.service.UserService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService service;
    private final RestResponseBuilder resourceBuilder;

    @PostMapping("/registers")
   public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody @Valid UserRequest userRequest){
       UserResponse userResponse = service.registerUser(userRequest);
       return resourceBuilder.success(HttpStatus.CREATED,"User created",userResponse);

   }
    @GetMapping("users/{userid}")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(){
       UserResponse userResponse = service.findUserById();
       return resourceBuilder.success(HttpStatus.FOUND,"User Found",userResponse);
    }
    @PutMapping("/users/{userid}")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody UserRequest userRequest){
        UserResponse response = service.updateUser(userRequest);
        return resourceBuilder.success(HttpStatus.OK,"User Updated",response);
    }
    @GetMapping("/admins")
    public ResponseEntity<ResponseStructure<UserResponse>> usertoadmin(@PathVariable int userid){
        UserResponse response = service.usertoadmin(userid);
        return resourceBuilder.success(HttpStatus.CREATED,"user found",response);
    }

    @PostMapping("/admins")
    public ResponseEntity<ResponseStructure<AdminResponse>> registertoadmin(@RequestBody @Valid UserRequest userRequest){
        AdminResponse adminResponse = service.registertoadmin(userRequest);
        return resourceBuilder.success(HttpStatus.CREATED,"Admin added successfully",adminResponse);
    }
    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN')")
    @PatchMapping("/users/{userid}")
    public ResponseEntity<ResponseStructure<UserResponse>> verifyuser(@PathVariable @Valid int userid, boolean isveryfied){
        UserResponse userResponse = service.verifieduser(userid, isveryfied);
        return resourceBuilder.success(HttpStatus.CREATED,"User verify",userResponse);
    }
}
