package com.codebyshatru.userservice.controller;

import com.codebyshatru.userservice.dto.GenericResponse;
import com.codebyshatru.userservice.dto.UserResponse;
import com.codebyshatru.userservice.model.Address;
import com.codebyshatru.userservice.model.User;
import com.codebyshatru.userservice.service.AddressService;
import com.codebyshatru.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final AddressService addressService;

    @GetMapping("/{user_id}")
    public ResponseEntity<GenericResponse> getUser(@PathVariable Long userId) {
        User user = userService.getByUserId(userId);
        List<Address> address = addressService.getByUserId(userId);
        UserResponse response = mapToUserResponse(user, address);
        return ResponseEntity.ok(GenericResponse.builder()
                .status_code(HttpStatus.OK)
                .message("Success")
                .results(response)
                .build());
    }

    private UserResponse mapToUserResponse(User user, List<Address> address) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .title(user.getTitle())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .companyName(user.getCompanyName())
                .vat(user.getVat())
                .website(user.getWebsite())
                .telephone(user.getTelephone())
                .mobile(user.getMobile())
                .telefax(user.getTelefax())
                .email(user.getEmail())
                .primaryLanguage(user.getPrimaryLanguage())
                ///Needed to handle corner cases for multiple address
                .addressLine1(address.get(0).getAddressLine1())
                .addressLine2(address.get(0).getAddressLine2())
                .zipcode(address.get(0).getZipcode())
                .country(address.get(0).getCountry())
                .state(address.get(0).getState())
                .city(address.get(0).getCity())
                .build();
    }

}
