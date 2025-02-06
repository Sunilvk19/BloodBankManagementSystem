package com.example.bsm.exceptionhandler;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FieldErrorStructure {
    protected String field;
    protected Object rejectedValue;
    protected String message;
}
