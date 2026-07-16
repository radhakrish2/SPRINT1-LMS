package com.learnhub.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learnhub.dto.RegisterRequest;
import com.learnhub.dto.UserDto;
import com.learnhub.entity.Roles;
import com.learnhub.entity.User;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.mapper.UserMapper;
import com.learnhub.repository.UserRepository;
import com.learnhub.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDto createUser(RegisterRequest regRequest) {


        if (userRepository.existsByEmail(regRequest.getEmail())) {

            throw new IllegalArgumentException(
                    "Email already exists : "
                    + regRequest.getEmail());
        }


        if (regRequest.getPhone() != null &&
                userRepository.existsByPhone(regRequest.getPhone())) {

            throw new IllegalArgumentException(
                    "Phone already exists : "
                    + regRequest.getPhone());
        }


        User user = userMapper.toEntity(regRequest);


        // Password Encryption
        user.setPassword(
                passwordEncoder.encode(
                		regRequest.getPassword()
                )
        );


        // Default Role
        if(user.getRole() == null) {

            user.setRole(Roles.STUDENT);
        }


        User savedUser =
                userRepository.save(user);


        return userMapper.toDto(savedUser);
    }



    @Override
    public UserDto updateUser(Long id,
                              RegisterRequest regRequest) {


        User user = userRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id : "
                                + id));


        user.setName(
        		regRequest.getName()
        );


        user.setPhone(
        		regRequest.getPhone()
        );


        if(regRequest.getPassword()!=null
                && !regRequest.getPassword().isEmpty()) {


            user.setPassword(
                    passwordEncoder.encode(
                    		regRequest.getPassword()
                    )
            );
        }


        User updatedUser =
                userRepository.save(user);


        return userMapper.toDto(updatedUser);
    }



    @Override
    public UserDto getUserById(Long id) {


        User user = userRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id : "
                                + id));


        return userMapper.toDto(user);
    }




    @Override
    public UserDto getUserByEmail(String email) {


        User user =
                userRepository.findByEmail(email)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with email : "
                                + email));


        return userMapper.toDto(user);
    }





    @Override
    public List<UserDto> getAllUsers() {


        return userMapper.toDtoList(
                userRepository.findAll()
        );
    }





    @Override
    public void deleteUser(Long id) {


        User user =
                userRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id : "
                                + id));


        userRepository.delete(user);

    }



    @Override
    public boolean existsByEmail(String email) {


        return userRepository.existsByEmail(email);
    }



}