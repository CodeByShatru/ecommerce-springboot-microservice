package com.codebyshatru.userservice.controller;

import com.codebyshatru.userservice.dto.AuthenticationRequest;
import com.codebyshatru.userservice.dto.GenericResponse;
import com.codebyshatru.userservice.dto.RegisterRequest;
import com.codebyshatru.userservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> register(@RequestBody RegisterRequest request) {
        if (authenticationService.userExistByUsername(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(GenericResponse.builder().status_code(HttpStatus.CONFLICT).error("User exists with username " + request.getUsername() + " in System").build());
        } else if (authenticationService.userExistByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(GenericResponse.builder().status_code(HttpStatus.CONFLICT).error("User exists with email " + request.getEmail() + " in System").build());
        } else if (authenticationService.userExistByMobile(request.getMobile())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(GenericResponse.builder().status_code(HttpStatus.CONFLICT).error("User exists with mobile number " + request.getMobile() + " in System").build());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(GenericResponse.builder().status_code(HttpStatus.CREATED).message("Successfully Registered").results(authenticationService.register(request)).build());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<GenericResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(GenericResponse.builder().status_code(HttpStatus.OK).message("LoggedIn Successfully").results(authenticationService.authenticate(request)).build());
    }

}
