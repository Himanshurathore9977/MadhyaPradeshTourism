package com.mpt.MadhyaPradeshTourism.response;

import com.mpt.MadhyaPradeshTourism.entity.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long  userID;
    private Role role;
    private String fullName;
    private String userName;
    private String email;
    private String mobileNumber;
    private Boolean activeUser;
    private String aadharNo;
    private String address;
}
