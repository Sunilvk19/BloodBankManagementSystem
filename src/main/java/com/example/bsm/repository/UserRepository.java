package com.example.bsm.repository;

import com.example.bsm.entity.User;
import com.example.bsm.enums.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

     Optional<User> findByEmail(String email);

   public List<User> findAllByAvailableCityInAndBloodGroupIn(List<BloodGroup> bloodgroupList, List<String> cities);
}
