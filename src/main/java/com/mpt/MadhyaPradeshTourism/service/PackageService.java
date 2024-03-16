package com.mpt.MadhyaPradeshTourism.service;

import com.mpt.MadhyaPradeshTourism.request.UserCreateRequest;
import com.mpt.MadhyaPradeshTourism.request.UserUpdateRequest;
import com.mpt.MadhyaPradeshTourism.response.PackageResponse;
import com.mpt.MadhyaPradeshTourism.response.Response;
import com.mpt.MadhyaPradeshTourism.response.UserResponse;

import java.util.List;

public interface PackageService {
    public Response<List<PackageResponse>> getAllPackage() ;

    public Response<PackageResponse> getPackageById(Long id ) ;

    public Response<PackageResponse> createPackage(UserCreateRequest userCreateRequest ) ;

    public Response<PackageResponse>updatePackage(Long userID, UserUpdateRequest userUpdateRequest);

    public Response<PackageResponse> deletePackage(Long userID);
}
