package com.example.bsm.repository;

import com.example.bsm.entity.Admin;
import com.example.bsm.entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    public Optional<Admin> findByUser_Email(String username);
}
