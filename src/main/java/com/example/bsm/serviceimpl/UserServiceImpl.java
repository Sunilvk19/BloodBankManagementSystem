package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Admin;
import com.example.bsm.entity.User;
import com.example.bsm.enums.Role;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.exception.UserNotFoundException;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.requestdto.UserRequest;
import com.example.bsm.responsedto.AdminResponse;
import com.example.bsm.responsedto.UserResponse;
import com.example.bsm.security.AuthUtil;
import com.example.bsm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthUtil authUtil;
    private final UserRepository userrepository;
    private final AdminRepository adminReository;
    private final PasswordEncoder passwordEncoder;

    private  UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .userid(user.getUserid())
                .username(user.getUsername())
                .bloodgroup(user.getBloodGroup())
                .lastdonatedat(user.getLastdonatedat())
                .role(user.getRole())
                .age(user.getAge())
                .availablecity(user.getAvailableCity())
                .Verified(user.isVerified())
                .createdAt(user.getCreatedAt())
                .lastmodifiesAt(user.getLastmodifiesAt())
                .gender(user.getGender())
                .build();
    }

    private  User mapToUser(UserRequest userRequest,User user) {
                user.setUsername(userRequest.getUsername());
                user.setEmail(userRequest.getEmail());
                user.setPassword(userRequest.getPassword());
                user.setPhoneNumber(userRequest.getPhoneNumber());
                user.setBloodGroup(userRequest.getBloodgroup());
                user.setAge(userRequest.getAge());
                user.setGender(userRequest.getGender());
                user.setAvailableCity(userRequest.getAvailablecity());

        return user;
    }

    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        User user = new User();
        user.setRole(Role.USER);
        user = this.mapToUser(userRequest,user);
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        user = userrepository.save(user);

        return this.mapToResponse(user);
    }

    @Override
    public UserResponse findUserById() {
      int userid = authUtil.getCurrentUser().getUserid();
        Optional<User> optional = userrepository.findById(userid);
        if(optional.isEmpty()){
            throw new UserNotFoundByIdException("user not found");
        }
        return this.mapToResponse(optional.get());
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {

        User user = authUtil.getCurrentUser();

        user = this.mapToUser(userRequest, user);
        return  this.mapToResponse(user);
    }

    @Override
    public UserResponse usertoadmin(int userid) {
       Optional<User>optional = userrepository.findById(userid);
        if (optional.isEmpty())
            throw new UserNotFoundByIdException("Failed to update the user");

        User user = optional.get();
        user.setRole(Role.GUEST_ADMIN);
        userrepository.save(user);

        Admin admin = new Admin();
        admin.setUser(user);
        adminReository.save(admin);

        return this.mapToResponse(user);
    }

    @Override
    public AdminResponse registertoadmin(UserRequest userRequest) {
        User user = new User();
        user.setRole(Role.OWNER_ADMIN);
         user  = this.mapToUser(userRequest, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userrepository.save(user);
        UserResponse userResponse = this.mapToResponse(user);

        Admin admin = new Admin();
        admin.setUser(user);

        adminReository.save(admin);
        return AdminResponse.builder()
                .userResponse(userResponse)
                .adminid(admin.getAdminid())
                .build();
    }

    @Override
    public UserResponse verifieduser(int userid, boolean isveryfied) {

        Optional<User> optional = userrepository.findById(userid);
        if (optional.isEmpty()){
            throw new UserNotFoundByIdException("user not verified");
        }
        User user = optional.get();
        user.setVerified(user.isVerified());
        userrepository.save(user);
        return this.mapToResponse(user);
    }


}
