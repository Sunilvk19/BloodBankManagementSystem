package com.example.bsm.repository;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.DonationRequest;
import com.example.bsm.enums.BloodGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Integer> {
    public Page<BloodBank> findByAddress_CityInAndSample_BloodGroup(List<String> cities, BloodGroup bloodGroups, Pageable pageable);

    Optional<BloodBank> findByDonationRequest(DonationRequest request);
}
