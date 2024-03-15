package com.mpt.MadhyaPradeshTourism.service.impl;

import com.mpt.MadhyaPradeshTourism.entity.User;
import com.mpt.MadhyaPradeshTourism.exceptions.MPTApplicationExceptions;
import com.mpt.MadhyaPradeshTourism.repository.UserRepository;
import com.mpt.MadhyaPradeshTourism.request.UserCreateRequest;
import com.mpt.MadhyaPradeshTourism.request.UserUpdateRequest;
import com.mpt.MadhyaPradeshTourism.response.Response;
import com.mpt.MadhyaPradeshTourism.response.UserResponse;
import com.mpt.MadhyaPradeshTourism.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    UserRepository userRepository ;
    public Response<UserResponse> createUser(UserCreateRequest userCreateRequest) throws MPTApplicationExceptions {

        if (userRepository.existsByEmail(userCreateRequest.getEmail()))
            throw new MPTApplicationExceptions("A user with the same email already exists");

        if (userRepository.existsByUserName(userCreateRequest.getUserName()))
            throw new MPTApplicationExceptions("A user with the same username already exists");

        if (userRepository.existsByMobileNumber(userCreateRequest.getMobileNumber()))
            throw new MPTApplicationExceptions("A user with the same Mobile Number already exists");

        userCreateRequest.setActiveUser(true);
        var user = modelMapper.map(userCreateRequest, User.class);
        userRepository.save(user);
        var userResponse = modelMapper.map(user, UserResponse.class);
        return Response.successfulResponse("User Created Successfully", userResponse);
    }
    @Override
    public Response<List<UserResponse>> getAllUser() {
        var userList = userRepository.findAll();
        var userResponse = userList.stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
        return Response.successfulResponse("Retrieved Successfully ", userResponse) ;
    }

    @Override
    public Response<UserResponse> getUserById(Long userId) throws MPTApplicationExceptions  {
        var user = userRepository.findById(userId).orElseThrow(() -> new MPTApplicationExceptions("User not found with id"));
        var userResponse = modelMapper.map(user, UserResponse.class);
        return Response.successfulResponse("User fetched Successfully", userResponse);
    }

    @Override
    public Response<UserResponse> updateUser(Long userID, UserUpdateRequest userUpdateRequest) throws MPTApplicationExceptions {

            User existingUser = userRepository.findById(userID).orElseThrow(() -> new MPTApplicationExceptions("User not found with id  "));
            if(!userUpdateRequest.getEmail().equals(existingUser.getEmail())  && userRepository.existsByEmail(userUpdateRequest.getEmail()))
                throw new MPTApplicationExceptions("A user with the same email already exists");

            if(!userUpdateRequest.getUserName().equals(existingUser.getUserName()) && userRepository.existsByUserName(userUpdateRequest.getUserName()))
                throw new MPTApplicationExceptions("A user with the same username already exists");

            if (!userUpdateRequest.getMobileNumber().equals(existingUser.getMobileNumber()) && userRepository.existsByMobileNumber(userUpdateRequest.getMobileNumber()))
                throw new MPTApplicationExceptions("A user with the same Mobile Number already exists");

            existingUser.setEmail(userUpdateRequest.getEmail());
            existingUser.setUserName(userUpdateRequest.getUserName());
            existingUser.setFullName(userUpdateRequest.getFullName());
            existingUser.setPassword((userUpdateRequest.getPassword()));

            existingUser.setAge(userUpdateRequest.getAge());
            existingUser.setAddress(userUpdateRequest.getAddress());
            existingUser.setMobileNumber(userUpdateRequest.getMobileNumber());
            existingUser.setAadharNo(userUpdateRequest.getAadharNo());

            User updatedUser = userRepository.save(existingUser);
            UserResponse userResponse = modelMapper.map(updatedUser, UserResponse.class);
            return Response.successfulResponse("User Updated Successfully", userResponse);
        }
}
