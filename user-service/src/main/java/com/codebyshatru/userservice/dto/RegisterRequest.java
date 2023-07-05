package com.codebyshatru.userservice.dto;

import com.codebyshatru.userservice.model.Role;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Nonnull
    private String email;
    @Nonnull
    private String username;
    @Nonnull
    private String password;

    private String title;

    private String firstName;
    private String lastName;

    private String companyName;

    private String vat;

    private String website;

    private String telephone;

    private String telefax;

    private BigInteger mobile;

    private String primaryLanguage;
    private String addressLine1;
    private String addressLine2;
    private Integer zipcode;
    private String country;
    private String state;
    private String city;
    private BigInteger secondaryMobile;

    private Role role;

}
