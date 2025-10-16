package com.example.hospitalManagementSystem.noticeBoard.repo;

import com.example.hospitalManagementSystem.noticeBoard.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepo extends JpaRepository<Notice, Long> {
}
