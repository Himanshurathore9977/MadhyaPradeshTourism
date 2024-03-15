package com.mpt.MadhyaPradeshTourism.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mpt.MadhyaPradeshTourism.entity.enums.LocationEnum;
import com.mpt.MadhyaPradeshTourism.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true,nullable = false)
    private String userName;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false , unique = true)
    private String mobileNumber;

    @Column(name = "role")
    @Enumerated
    private Role role;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(nullable = false)
    private int age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "activeUser")
    private boolean activeUser;

    @Column(name = "aadharNo")
    private String aadharNo;

    @Column(name = "address")
    private String address;

}
