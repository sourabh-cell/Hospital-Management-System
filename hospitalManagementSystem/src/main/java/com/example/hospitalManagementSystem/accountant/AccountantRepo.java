package com.example.hospitalManagementSystem.accountant;

import com.example.hospitalManagementSystem.accountant.entity.Accountant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountantRepo extends JpaRepository<Accountant,Long> {
}
