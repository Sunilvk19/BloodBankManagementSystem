package com.example.bsm.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminid;

    @OneToOne
    private User user;

    @OneToOne
    private BloodBank bloodBank;

}
