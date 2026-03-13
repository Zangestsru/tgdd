package com.example.ngogiaphuong2280602523.service;

import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ngogiaphuong2280602523.model.User;
import com.example.ngogiaphuong2280602523.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // Hash password before saving
        String hashedPassword = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String hashedPassword = DigestUtils.sha256Hex(password);
            if (user.getPassword().equals(hashedPassword)) {
                return user; // Login successful
            }
        }
        return null; // Login failed
    }

    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
