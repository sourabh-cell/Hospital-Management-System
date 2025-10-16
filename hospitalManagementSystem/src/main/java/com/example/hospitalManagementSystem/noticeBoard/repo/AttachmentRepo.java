package com.example.hospitalManagementSystem.noticeBoard.repo;

import com.example.hospitalManagementSystem.noticeBoard.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepo extends JpaRepository<Attachment,Long>
{

}
