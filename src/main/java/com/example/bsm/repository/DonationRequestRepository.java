package com.example.bsm.repository;

import com.example.bsm.entity.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRequestRepository extends JpaRepository<DonationRequest,Integer> {

     public List<DonationRequest> findByRequestCompleted(boolean s);
}
