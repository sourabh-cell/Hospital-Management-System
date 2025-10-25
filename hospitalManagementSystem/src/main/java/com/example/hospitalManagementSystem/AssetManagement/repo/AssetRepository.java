package com.example.hospitalManagementSystem.AssetManagement.repo;

import com.example.hospitalManagementSystem.AssetManagement.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long>, JpaSpecificationExecutor<Asset> {

    Optional<Asset> findByAssetId(String assetId);
    Optional<Asset> findBySerialNumber(String serialNumber);
    boolean existsByAssetId(String assetId);
    boolean existsBySerialNumber(String serialNumber);
}
