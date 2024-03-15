package com.mpt.MadhyaPradeshTourism.request;

import com.mpt.MadhyaPradeshTourism.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequest {

    private String fullName;
    private String userName;
    private String email;
    private String mobileNumber;
    private boolean activeUser;
    private int age;
    private String password;
    private String aadharNo;
    private String address ;
}
