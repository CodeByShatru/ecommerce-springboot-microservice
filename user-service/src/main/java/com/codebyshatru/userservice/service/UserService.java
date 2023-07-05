package com.codebyshatru.userservice.service;

import com.codebyshatru.userservice.model.User;
import com.codebyshatru.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User getByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }
}
