package com.jaycode.demo.features.auth;

import com.jaycode.demo.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    private final AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public UserModel register(UserModel userModel){
        Optional<UserModel> checkUser = authRepository.findUserByUserName(userModel.getUserName());

        if(checkUser.isPresent()){
            throw  new ResourceNotFoundException("User name already taken");
        }

        userModel.setPassword(userModel.getPassword());
        UserModel registeredUser =  authRepository.save(userModel);


        registeredUser.setPassword(null);
        return  registeredUser;
    }

    public UserModel login(UserModel userModel) {
        Optional<UserModel> existingUser = authRepository.findUserByUserName(userModel.getUserName());

        if (existingUser.isEmpty()) {
            throw new ResourceNotFoundException("Invalid username or password");
        }

        if ((!Objects.equals(userModel.getPassword(), existingUser.get().getPassword()))) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        UserModel loggedInUser = existingUser.get();


        loggedInUser.setPassword(null);

        return loggedInUser;
    }

    public List<UserModel> getAllUsers() {
        return  authRepository.findAll();
    }
}
