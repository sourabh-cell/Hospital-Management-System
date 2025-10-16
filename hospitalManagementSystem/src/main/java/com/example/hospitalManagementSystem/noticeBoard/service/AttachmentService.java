package com.example.hospitalManagementSystem.noticeBoard.service;

import com.example.hospitalManagementSystem.noticeBoard.entity.Attachment;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AttachmentService {

    ResponseEntity<byte[]> getAttachmentDownloadResponse(Long id) throws Exception;

    ResponseEntity<byte[]> getAttachmentViewResponse(Long id) throws Exception;
}
