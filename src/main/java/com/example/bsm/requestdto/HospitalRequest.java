package com.example.bsm.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalRequest {
    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
//    @Pattern(regexp = "$[a-zA-Z_]^",message = "username must contain format of alphabets and underscore")
    private String name;
}
