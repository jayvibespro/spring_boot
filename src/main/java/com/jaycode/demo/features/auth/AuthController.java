package com.jaycode.demo.features.auth;

import com.jaycode.demo.models.ResponseModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private  final AuthService authService;

    @Autowired
    AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("register")
    public ResponseModel<UserModel> register(@RequestBody UserModel userModel){
        UserModel registeredUser = authService.register(userModel);
        return new ResponseModel<>(201, true, "User registered successfully", registeredUser);
    }

    @PostMapping("/login")
    public  ResponseModel<UserModel> login(@RequestBody  @Valid UserModel userModel){
        UserModel loggedInUserModel = authService.login(userModel);
        return  new ResponseModel<>(200, true, "User logged in successfully", loggedInUserModel);
    }

    @GetMapping("/allUsers")
    public ResponseModel<List<UserModel>> getAllUsers(){
        List<UserModel> users = authService.getAllUsers();
        return new  ResponseModel<>(200, true, "Users fetched successfully", users);
    }
}
