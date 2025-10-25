package com.example.hospitalManagementSystem.AssetManagement.mapper;

import com.example.hospitalManagementSystem.AssetManagement.dto.AssetRequestDTO;
import com.example.hospitalManagementSystem.AssetManagement.dto.AssetResponseDTO;
import com.example.hospitalManagementSystem.AssetManagement.entity.Asset;
import org.springframework.stereotype.Component;

@Component
public class AssetMapper {

    public Asset toEntity(AssetRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return Asset.builder()
                .assetId(dto.getAssetId())
                .serialNumber(dto.getSerialNumber())
                .model(dto.getModel())
                .vendor(dto.getVendor())
                .purchaseDate(dto.getPurchaseDate())
                .warrantyDate(dto.getWarrantyDate())
                .departmentBranch(dto.getDepartmentBranch())
                .amcCmcDetails(dto.getAmcCmcDetails())
                .remarksNotes(dto.getRemarksNotes())
                .build();
    }

    public AssetResponseDTO toDTO(Asset entity) {
        if (entity == null) {
            return null;
        }

        return AssetResponseDTO.builder()
                .id(entity.getId())
                .assetId(entity.getAssetId())
                .serialNumber(entity.getSerialNumber())
                .model(entity.getModel())
                .vendor(entity.getVendor())
                .purchaseDate(entity.getPurchaseDate())
                .warrantyDate(entity.getWarrantyDate())
                .departmentBranch(entity.getDepartmentBranch())
                .amcCmcDetails(entity.getAmcCmcDetails())
                .remarksNotes(entity.getRemarksNotes())
                .status(entity.getStatus().name())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public Asset updateEntityFromDTO(AssetRequestDTO dto, Asset entity) {
        if (dto == null || entity == null) {
            return entity;
        }

        entity.setAssetId(dto.getAssetId());
        entity.setSerialNumber(dto.getSerialNumber());
        entity.setModel(dto.getModel());
        entity.setVendor(dto.getVendor());
        entity.setPurchaseDate(dto.getPurchaseDate());
        entity.setWarrantyDate(dto.getWarrantyDate());
        entity.setDepartmentBranch(dto.getDepartmentBranch());
        entity.setAmcCmcDetails(dto.getAmcCmcDetails());
        entity.setRemarksNotes(dto.getRemarksNotes());
        return entity;
    }
}
