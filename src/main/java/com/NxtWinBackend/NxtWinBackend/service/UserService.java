package com.NxtWinBackend.NxtWinBackend.service;


import com.NxtWinBackend.NxtWinBackend.configuration.JwtTokenUtil;
import com.NxtWinBackend.NxtWinBackend.entity.Token;
import com.NxtWinBackend.NxtWinBackend.entity.User;
import com.NxtWinBackend.NxtWinBackend.repository.TokenRepository;
import com.NxtWinBackend.NxtWinBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JavaMailSender emailSender;

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use");
        }

        User savedUser = userRepository.save(user);
        sendRegistrationEmail(savedUser.getEmail());

        return savedUser;
    }

    public String loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtTokenUtil.generateToken(user.getEmail());

        // Save the token to the database using the Token entity
        Token userToken = new Token(token, user);
        tokenRepository.save(userToken);

        return token;
    }

    private void sendRegistrationEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registration Successful");
        message.setText("You have successfully registered!");
        emailSender.send(message);
    }


    public boolean logoutUser(Long userId, String token) {
        Optional<Token> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isPresent()) {
            Token userToken = tokenOpt.get();
            if (userToken.getUser().getId().equals(userId)) {
                tokenRepository.delete(userToken);
                return true;
            }
        }
        return false;
    }
}
