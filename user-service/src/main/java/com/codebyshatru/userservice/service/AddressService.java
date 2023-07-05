package com.codebyshatru.userservice.service;

import com.codebyshatru.userservice.dto.AddressRequest;
import com.codebyshatru.userservice.model.Address;
import com.codebyshatru.userservice.model.User;
import com.codebyshatru.userservice.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository repository;

    public Address saveAddress(AddressRequest request, User users){
        var address = Address.builder()
                .user(users)
                .primary(true)
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .zipcode(request.getZipcode())
                .country(request.getCountry())
                .state(request.getState())
                .city(request.getCity())
                .secondaryMobile(request.getSecondaryMobile())
                .createdDt(LocalDate.now())
                .build();
        repository.save(address);
        return address;
    }

    public List<Address> getByUserId(Long userId) {
        return repository.findByUser(userId);
    }
}
