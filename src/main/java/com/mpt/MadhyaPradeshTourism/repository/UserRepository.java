package com.mpt.MadhyaPradeshTourism.repository;

import com.mpt.MadhyaPradeshTourism.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long > {
    Optional<User> findByUserName(String userName);

    Optional<User> findByMobileNumber(String mobileNumber);

    Optional<User> findByEmail(String email);

    Boolean existsByUserName(String userName);

    Boolean existsByMobileNumber(String mobileNumber);

    Boolean existsByEmail(String email);

//    Boolean existsByIdentityNumber(Long identityNumber);


    Optional<User> findByUserNameOrEmail(String username, String email);

//    Boolean existsByEmail(String email);

//    Boolean existsByUsername(String username);
}
