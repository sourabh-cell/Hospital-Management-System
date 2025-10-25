package com.example.hospitalManagementSystem.AssetManagement.controller;

import com.example.hospitalManagementSystem.AssetManagement.dto.AssetRequestDTO;
import com.example.hospitalManagementSystem.AssetManagement.dto.AssetResponseDTO;
import com.example.hospitalManagementSystem.AssetManagement.service.AssetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AseetManagementMVCController {

    private final AssetService assetService;

    @GetMapping("/add-asset")
    public String showAddAssetPage(Model model) {
        model.addAttribute("asset", new AssetRequestDTO());
        return "add-asset";
    }

    //Handle form submit
    @PostMapping("/add-asset")
    public String addAssetPage(@ModelAttribute("asset") AssetRequestDTO asset,  RedirectAttributes redirectAttributes) {
        assetService.createAsset(asset);
        redirectAttributes.addFlashAttribute("successMessage", "Asset saved successfully!");
        return "redirect:/assets";
    }

        // view assets list
        @GetMapping("/view-assets")
        public String viewAssets(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
                Model model) {

            Pageable pageable = PageRequest.of(page, size);
            Page<AssetResponseDTO> assetsPage = assetService.getAllAssets(pageable);
            model.addAttribute("assetsPage", assetsPage);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", assetsPage.getTotalPages());
            return "list-assets";
        }

    @PostMapping("view-assets/delete/{id}")
    public String deleteAsset(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            assetService.deleteAsset(id);
            redirectAttributes.addFlashAttribute("successMessage", "Asset deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete asset.");
        }
            return "redirect:/add-asset";
    }

    // you can edit the exsting aset
    @GetMapping("view-assets/edit/{id}")
    public String editAssetForm(@PathVariable Long id, Model model) {
        AssetResponseDTO asset = assetService.getAssetById(id);
        model.addAttribute("asset", asset);
        return "redirect:/add-asset";
    }
}
