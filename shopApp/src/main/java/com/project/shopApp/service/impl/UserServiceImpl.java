package com.project.shopApp.service.impl;

import com.project.shopApp.dtos.UserDto;
import com.project.shopApp.mapper.UserMapping;
import com.project.shopApp.models.Role;
import com.project.shopApp.models.Token;
import com.project.shopApp.models.User;
import com.project.shopApp.repository.RoleRepository;
import com.project.shopApp.repository.TokenRepository;
import com.project.shopApp.repository.UserRepository;
import com.project.shopApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Getter
@Setter
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(UserDto userDto) {
        // Check if phone number already exists
        if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber())) {
            throw new RuntimeException("Phone number already exists");
        }

        // Check if password and retype password match
        if (!userDto.getPassword().equals(userDto.getRetypePassword())) {
            throw new RuntimeException("Password and retype password do not match");
        }
        User user = UserMapping.INSTANCE.toModel(userDto);

//        if ((userDto.getFacebookAccountId() == 0 && userDto.getGoogleAccountId() == 0)) {
//            String password = userDto.getPassword();
//            String encodedPassword = passwordEncoder.encode(password);
//            user.setPassword(encodedPassword);
//        }
        // Find role
        Role role = roleRepository.findById(userDto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role);

        return userRepository.save(user);
    }

    @Override
    public String login(String phoneNumber, String password) {
        // Find user by phone number
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if user is active
        if (!user.isActive()) {
            throw new RuntimeException("User is not active");
        }

        // Verify password
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        // Generate token
        Token token = Token.builder()
                .token(UUID.randomUUID().toString())
                .tokenType("Bearer")
                .expirationDate(LocalDateTime.now().plusDays(7))
                .revoked(false)
                .expired(false)
                .user(user)
                .build();

        tokenRepository.save(token);

        return token.getToken();
    }
}
