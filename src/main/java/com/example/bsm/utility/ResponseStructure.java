package com.example.bsm.utility;

import lombok.*;

@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure <T>{
    private  int status;
    private String message;
    private T data;
}
