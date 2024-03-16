package com.mpt.MadhyaPradeshTourism.service;

import com.mpt.MadhyaPradeshTourism.request.UserCreateRequest;
import com.mpt.MadhyaPradeshTourism.request.UserUpdateRequest;
import com.mpt.MadhyaPradeshTourism.response.Response;
import com.mpt.MadhyaPradeshTourism.response.UserResponse;
import com.mpt.MadhyaPradeshTourism.util.ApiResponse;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {
    public Response<List<UserResponse>>getAllUser() ;

    public Response<UserResponse> getUserById(Long id ) ;

    public Response<UserResponse> createUser(UserCreateRequest userCreateRequest ) ;

    public Response<UserResponse>updateUser(Long userID, UserUpdateRequest userUpdateRequest);

    public Response<UserResponse> deleteUser(Long userID);
}
