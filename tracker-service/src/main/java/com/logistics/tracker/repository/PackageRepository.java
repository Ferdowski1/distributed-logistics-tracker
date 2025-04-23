package com.logistics.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistics.tracker.model.PackageEntity;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, String> {
    // We can add custom queries later if needed
}
