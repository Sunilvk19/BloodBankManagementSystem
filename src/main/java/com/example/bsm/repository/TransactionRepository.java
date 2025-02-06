package com.example.bsm.repository;

import com.example.bsm.entity.Transcation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transcation, Integer> {

}
