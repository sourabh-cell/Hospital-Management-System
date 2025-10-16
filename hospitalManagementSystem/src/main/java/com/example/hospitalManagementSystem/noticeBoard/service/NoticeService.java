package com.example.hospitalManagementSystem.noticeBoard.service;

import com.example.hospitalManagementSystem.noticeBoard.dto.NoticeDTO;
import com.example.hospitalManagementSystem.noticeBoard.entity.Notice;

import java.io.IOException;
import java.util.List;

public interface NoticeService {

    void saveNotice(NoticeDTO noticeDTO) throws IOException;

    List<NoticeDTO> getAllNotices();

    void deleteNoticeById(Long id) throws IOException;

    NoticeDTO getNoticeById(Long id) throws IOException;



    void updateNotice(Long id,NoticeDTO noticeDTO) throws IOException;
}
