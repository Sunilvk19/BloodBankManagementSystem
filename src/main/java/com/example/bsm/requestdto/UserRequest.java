package com.example.bsm.requestdto;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.repository.query.parser.Part;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {
    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    @Pattern(regexp = "^[a-zA-Z_]+$",message = "username must contain format of alphabets and underscore")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail.com$", message = "email addresses that contain only \"@gmail.com\" and do not consist solely of numbers")
    private String email;

    @NotBlank(message = "password cannot be blank")
    @NotNull(message = "password cannot be null")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must be at least 8 characters long and contain at least one uppercase letter one lowercase letter")
    private String password;

    @NotNull(message = "phoneNumber cannot be null")
    @NotBlank(message = "phoneNumber cannot be blank")
    @Pattern(regexp = "^\\d{10}$",message = "phone number should contain 10 digit number")
    private String phoneNumber;

    private BloodGroup bloodgroup;
    @Min(1)
    @Max(100)
    private int age;

    private Gender gender;
    private String availablecity;
}
