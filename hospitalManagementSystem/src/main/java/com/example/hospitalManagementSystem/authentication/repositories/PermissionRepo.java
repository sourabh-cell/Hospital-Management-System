package com.example.hospitalManagementSystem.authentication.repositories;

import com.example.hospitalManagementSystem.authentication.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, Long> {

    @Query("""
        SELECT p
        FROM UserEntity u
        JOIN u.roles r
        JOIN r.permissions p
        WHERE u.userId = :userId
    """)
    List<Permission> findPermissionsByUserId(@Param("userId") Long userId);

}
