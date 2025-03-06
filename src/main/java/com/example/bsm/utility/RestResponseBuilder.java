package com.example.bsm.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.JstlUtils;
@Component

public class RestResponseBuilder {
    public <T>ResponseEntity<ResponseStructure<T>> success(HttpStatus status, String message, T data){
        return ResponseEntity
                .status(status)
                .body(ResponseStructure.<T>builder()
                        .status(status.value())
                        .message(message)
                        .data(data)
                        .build());
    }

    public <T>ResponseEntity<ErrorStructure<T>> error(HttpStatus status, String message,T rootCause){
        return ResponseEntity
                .status(status)
                .body(ErrorStructure.<T>builder()
                        .status(status.value())
                        .message(message)
                        .rootCause(rootCause)
                        .build());
    }
    public <T> ResponseEntity<PageStructure<T>> success(HttpStatus status, String message, T data,int page,int totalpage,int size){
        PageStructure <T> structure = new PageStructure<>();
        structure.setStatus(status.value());
        structure.setMessage(message);
        structure.setData(data);
        structure.setPage(page);
        structure.setTotalpage(totalpage);
        structure.setSize(size);

        return ResponseEntity
                .status(status)
                .body(structure);
    }

}
