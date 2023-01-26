package com.example.ecommerce.service;

import com.example.ecommerce.dto.user.SignupDto;
import com.example.ecommerce.dto.user.SignupResponseDto;
import com.example.ecommerce.exceptions.CustomException;
import com.example.ecommerce.model.AuthenticationToken;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationService authenticationService;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public SignupResponseDto signUp(SignupDto signupDto) throws CustomException {
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            throw new CustomException("User already exists");
        }
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }

        User user = new User(
                signupDto.getFirstName(),
                signupDto.getLastName(),
                signupDto.getEmail(),
                encryptedPassword
                );
        try {
            userRepository.save(user);
            final AuthenticationToken authenticationToken =  new AuthenticationToken(user);
            authenticationService.saveConfirmationToken(authenticationToken);
            return new SignupResponseDto("success", "user created successfully");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toUpperCase();
    }
}
