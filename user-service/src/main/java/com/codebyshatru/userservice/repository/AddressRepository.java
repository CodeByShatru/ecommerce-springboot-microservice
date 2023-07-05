package com.codebyshatru.userservice.repository;

import com.codebyshatru.userservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUser(Long userId);
}
