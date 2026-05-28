package com.library.serviceimpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.library.dto.LoginRequest;
import com.library.dto.RegisterRequest;
import com.library.entity.User;
import com.library.repository.UserRepository;
import com.library.service.UserService;
import com.library.util.JwtUtil;

@Service
public class UserServiceImpl
        implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(
            UserRepository userRepository){

        this.userRepository=
                userRepository;
    }


    @Override
    public String register(
            RegisterRequest request){

        Optional<User> user =
                userRepository
                .findByUsername(
                        request.getUsername());

        if(user.isPresent()){

            throw new RuntimeException(
                    "Username already exists");
        }

        User newUser =
                new User();

        newUser.setUsername(
                request.getUsername());

        newUser.setPassword(
                request.getPassword());

        newUser.setRole(
                request.getRole());

        userRepository.save(
                newUser);

        return "Registered Successfully";
    }


    @Override
    public String login(
            LoginRequest request){

        User user =
                userRepository
                .findByUsername(
                        request.getUsername())
                .orElseThrow(
                ()->new RuntimeException(
                "User not found"));

        if(!user.getPassword()
                .equals(
                request.getPassword())){

            throw new RuntimeException(
                    "Wrong Password");
        }

        return JwtUtil.generateToken(
                user.getUsername());
    }

}