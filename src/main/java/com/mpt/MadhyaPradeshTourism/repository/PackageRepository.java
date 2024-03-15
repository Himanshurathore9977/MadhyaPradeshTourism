package com.mpt.MadhyaPradeshTourism.repository;

import com.mpt.MadhyaPradeshTourism.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long > {

}
