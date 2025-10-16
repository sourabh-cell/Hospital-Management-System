package com.example.hospitalManagementSystem.noticeBoard.controller;

import com.example.hospitalManagementSystem.noticeBoard.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @GetMapping("get/attachment/download/{id}")
    public ResponseEntity<byte[]> downloadAttachment(@PathVariable Long id) throws Exception {
        return attachmentService.getAttachmentDownloadResponse(id);
    }

    @GetMapping("get/attachment/view/{id}")
    public ResponseEntity<byte[]> viewAttachment(@PathVariable Long id) throws Exception {
        return attachmentService.getAttachmentViewResponse(id);
    }
}
