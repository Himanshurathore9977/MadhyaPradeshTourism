package com.mpt.MadhyaPradeshTourism.controller;

import com.mpt.MadhyaPradeshTourism.exceptions.MPTApplicationExceptions;
import com.mpt.MadhyaPradeshTourism.request.UserCreateRequest;
import com.mpt.MadhyaPradeshTourism.request.UserUpdateRequest;
import com.mpt.MadhyaPradeshTourism.response.Response;
import com.mpt.MadhyaPradeshTourism.response.UserResponse;
import com.mpt.MadhyaPradeshTourism.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<Response<UserResponse>> getUser(@PathVariable long userId) throws MPTApplicationExceptions {
        var response = userService .getUserById(userId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<UserResponse>>> getUser(){
        var response =  userService.getAllUser() ;
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<UserResponse>> updateUser(@PathVariable long id, @RequestBody UserUpdateRequest userUpdateRequest){
        var response = userService.updateUser(id , userUpdateRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<UserResponse>> createUser(@RequestBody UserCreateRequest userCreateRequest ){
        var response = userService.createUser(userCreateRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @DeleteMapping
    public ResponseEntity<Response<UserResponse>> deleteUser(@PathVariable Long userID){
        var response = userService.deleteUser(userID) ;
        return ResponseEntity.status(response.getStatusCode()).body(response) ;
    }
}
