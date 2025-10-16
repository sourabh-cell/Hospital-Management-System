package com.example.hospitalManagementSystem.noticeBoard.controller;

import com.example.hospitalManagementSystem.authentication.entity.Role;
import com.example.hospitalManagementSystem.authentication.service.RoleService;
import com.example.hospitalManagementSystem.noticeBoard.dto.NoticeDTO;
import com.example.hospitalManagementSystem.noticeBoard.entity.Attachment;
import com.example.hospitalManagementSystem.noticeBoard.entity.Notice;
import com.example.hospitalManagementSystem.noticeBoard.service.NoticeService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class NoticeController {

    private final RoleService roleService;

    private final NoticeService noticeService;

    public NoticeController(RoleService roleService, NoticeService noticeService) {
        this.roleService = roleService;
        this.noticeService = noticeService;
    }

    @GetMapping("notice/list")
    public String viewNotice(Model model)

    {

        model.addAttribute("notice",noticeService.getAllNotices());
        return "notice-list";
    }


    @GetMapping("/notice/add")
    public String showNoticeAdd(Model model) {
        model.addAttribute("notice", new NoticeDTO());
        model.addAttribute("roles",roleService.getAllRoles());
        return "notice-add-edit";
    }

    @PostMapping("/notice/add")
    @PreAuthorize("hasAuthority('NOTICE_ADD')")
    public String addNotice(@Valid @ModelAttribute NoticeDTO noticeDTO,
                            BindingResult  bindingResult,
                            RedirectAttributes  redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().toString());
            redirectAttributes.addFlashAttribute("error","invalid data or Empty Field");
            return "notice-add-edit";
        }

        noticeService.saveNotice(noticeDTO);
        redirectAttributes.addFlashAttribute("success","Notice Added Successfully");
        return "redirect:/notice/list";

    }


    @PostMapping("/notice/delete/{id}")
    public String deleteNotice(@PathVariable Long id) throws IOException {
        noticeService.deleteNoticeById(id);
        return "redirect:/notice/list";
    }

    @GetMapping("/notice/edit/{id}")
    public String showEdit(@PathVariable Long id, Model model) throws IOException {
        NoticeDTO  noticeDTO = noticeService.getNoticeById(id);

        //for showing only selected role
        Set<Role> targetAudience = new HashSet<>();
        for(Long roleId : noticeDTO.getTargetAudienceIds())
        {
            targetAudience.add(roleService.getRoleById(roleId));
        }

        model.addAttribute("notice",noticeDTO);
        model.addAttribute("targetAudience",targetAudience);
        model.addAttribute("roles",roleService.getAllRoles());


        return "notice-add-edit";
    }

    @PostMapping("/notice/edit/{id}")
    public String updateNotice(@PathVariable Long id, @ModelAttribute NoticeDTO noticeDTO,
                               BindingResult bindingResult,Model model,
                               RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors())
        {
            //for showing only selected role
            Set<Role> targetAudience = new HashSet<>();
            for(Long roleId : noticeDTO.getTargetAudienceIds())
            {
                targetAudience.add(roleService.getRoleById(roleId));
            }
            System.out.println(bindingResult.getAllErrors().toString());

            model.addAttribute("targetAudience",targetAudience);
            model.addAttribute("roles",roleService.getAllRoles());
            redirectAttributes.addFlashAttribute("error","invalid data or Empty Field");
            return "notice-add-edit";
        }
        noticeService.updateNotice(id,noticeDTO);
        redirectAttributes.addFlashAttribute("success","Notice Updated Successfully");
        return "redirect:/notice/list";
    }
}
