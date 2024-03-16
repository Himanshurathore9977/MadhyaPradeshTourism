package com.mpt.MadhyaPradeshTourism.service.impl;

import com.mpt.MadhyaPradeshTourism.entity.Package;
import com.mpt.MadhyaPradeshTourism.exceptions.MPTApplicationExceptions;
import com.mpt.MadhyaPradeshTourism.repository.PackageRepository;
import com.mpt.MadhyaPradeshTourism.request.UserCreateRequest;
import com.mpt.MadhyaPradeshTourism.request.UserUpdateRequest;
import com.mpt.MadhyaPradeshTourism.response.PackageResponse;
import com.mpt.MadhyaPradeshTourism.response.Response;
import com.mpt.MadhyaPradeshTourism.response.UserResponse;
import com.mpt.MadhyaPradeshTourism.service.PackageService;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PackageServiceImpl implements PackageService {
    @Autowired
    PackageRepository packageRepository;

    ModelMapper modelMapper = new ModelMapper();


    @Override
    public Response<List<PackageResponse>> getAllPackage() {
        var packageList = packageRepository.findAll();
        var userResponse = packageList.stream().map(user -> modelMapper.map(packageList, PackageResponse.class)).collect(Collectors.toList());
        return Response.successfulResponse("Retrieved Successfully ", userResponse) ;
    }

    @Override
    public Response<PackageResponse> getPackageById(Long packageID) throws  MPTApplicationExceptions{
//        var package  = packageRepository.findById(userId).orElseThrow(() -> new MPTApplicationExceptions("User not found with id"));
//        var packageResponse = modelMapper.map(package, PackageResponse.class);
//        return Response.successfulResponse("Package fetched Successfully", packageResponse);
        Package package  = packageRepository.findById(packageID).orElseThrow(() -> new MPTApplicationExceptions("Package NOt found "));

    }

    @Override
    public Response<PackageResponse> createPackage(UserCreateRequest userCreateRequest) {
        return null;
    }

    @Override
    public Response<PackageResponse> updatePackage(Long userID, UserUpdateRequest userUpdateRequest) {
        return null;
    }

    @Override
    public Response<PackageResponse> deletePackage(Long userID) {
        return null;
    }
}
