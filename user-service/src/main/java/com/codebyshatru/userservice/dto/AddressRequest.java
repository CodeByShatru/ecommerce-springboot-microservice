package com.codebyshatru.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    private String addressLine1;
    private String addressLine2;
    private Integer zipcode;
    private String country;
    private String state;
    private String city;
    private BigInteger secondaryMobile;
}
