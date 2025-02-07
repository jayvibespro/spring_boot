package com.jaycode.demo.features.auth;

import com.jaycode.demo.utils.JwtUtil;
import com.jaycode.demo.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordService passwordService;

    @Autowired
    public AuthService(AuthRepository authRepository, PasswordService passwordService) {
        this.authRepository = authRepository;
        this.passwordService = passwordService;
    }

    public UserModel register(UserModel userModel){
        Optional<UserModel> checkUser = authRepository.findUserByUserName(userModel.getUserName());

        if(checkUser.isPresent()){
            throw  new ResourceNotFoundException("User name already taken");
        }

        userModel.setPassword(passwordService.encodePassword(userModel.getPassword()));
        UserModel registeredUser =  authRepository.save(userModel);

        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken(registeredUser.getUserName());

        registeredUser.setToken(token);
        registeredUser.setPassword(null);
        return  registeredUser;
    }

    public UserModel login(UserModel userModel) {
        Optional<UserModel> existingUser = authRepository.findUserByUserName(userModel.getUserName());

        if (existingUser.isEmpty()) {
            throw new ResourceNotFoundException("Invalid username or password");
        }

        if (!passwordService.matches(userModel.getPassword(),existingUser.get().getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        UserModel loggedInUser = existingUser.get();

        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken(loggedInUser.getUserName());

        loggedInUser.setToken(token);
        loggedInUser.setPassword(null);

        return loggedInUser;
    }

    public List<UserModel> getAllUsers() {
        return  authRepository.findAll();
    }
}
