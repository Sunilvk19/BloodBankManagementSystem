package com.example.bsm.utility;

import lombok.*;

@Setter
@Getter
public class PageStructure <T> extends ResponseStructure <T>{
    private int page;
    private int totalpage;
    private int size;

}
