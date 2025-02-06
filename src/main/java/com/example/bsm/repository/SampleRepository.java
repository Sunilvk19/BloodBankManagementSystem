package com.example.bsm.repository;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.Sample;
import com.example.bsm.enums.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleRepository extends JpaRepository<Sample,Integer> {
  public  List<Sample> findByBloodBankAndBloodGroup(BloodBank bloodBank, BloodGroup bloodGroups);
}
