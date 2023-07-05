package com.codebyshatru.userservice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class GenericResponse {
    private HttpStatus status_code;
    private String message;
    private Object results;
    private String error;
}
