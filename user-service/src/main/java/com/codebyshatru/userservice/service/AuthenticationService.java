package com.codebyshatru.userservice.service;

import com.codebyshatru.userservice.config.JwtService;
import com.codebyshatru.userservice.dto.AddressRequest;
import com.codebyshatru.userservice.dto.AuthenticationRequest;
import com.codebyshatru.userservice.dto.RegisterRequest;
import com.codebyshatru.userservice.exception.ImachineMartApplicationException;
import com.codebyshatru.userservice.model.Role;
import com.codebyshatru.userservice.model.User;
import com.codebyshatru.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final AddressService addressService;
    public String register(RegisterRequest request) {

        var user = User.builder()
                .title(request.getTitle())
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .role(request.getRole() != null ? request.getRole() : Role.USER)
                .createdDt(LocalDate.now())
                .build();

        var addressRequest = AddressRequest.builder()
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .country(request.getCountry())
                .state(request.getState())
                .city(request.getCity())
                .zipcode(request.getZipcode())
                .build();
        try {
          var users =  repository.save(user);
            addressService.saveAddress(addressRequest,users);
        } catch (Exception exception) {
            throw new ImachineMartApplicationException(exception.getMessage());
        }
        return jwtService.generateToken(user);
    }

    public String authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = repository.findByUsername(request.getUsername()).orElseThrow();
        return jwtService.generateToken(user);
    }

    public boolean userExistByUsername(String username) {
        return repository.findByUsername(username).isPresent();
    }

    public boolean userExistByEmail(String email) {
        return repository.findByEmail(email).isPresent();
    }

    public boolean userExistByMobile(BigInteger mobile) {
        return repository.findByMobile(String.valueOf(mobile)).isPresent();
    }
}
