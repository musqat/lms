package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BannerController {

    private final BannerService bannerService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/admin/banner/list")
    public String list(Model model) {
        List<BannerDto> banners = bannerService.getAllBanners();
        model.addAttribute("banners", banners);
        return "admin/banner/list";
    }

    @GetMapping("/admin/banner/add")
    public String add() {
        return "admin/banner/add";
    }

    @PostMapping("/admin/banner/add")
    public String add(BannerDto banner, MultipartFile file, Model model) {
        try {
            if (file != null && !file.isEmpty()) {
                Path directory = Paths.get(uploadDir);
                if (!Files.exists(directory)) {
                    Files.createDirectories(directory);
                }
                String filename = file.getOriginalFilename();
                Path filePath = directory.resolve(filename);
                file.transferTo(filePath.toFile());
                banner.setImagePath("/uploads/" + filename);

                // 디버깅을 위한 로그 추가
                System.out.println("파일 업로드 경로: " + filePath.toString());
                System.out.println("Banner imagePath: " + banner.getImagePath());
            } else {
                System.out.println("파일이 null이거나 비어있습니다.");
                model.addAttribute("error", "파일이 null이거나 비어있습니다.");
                return "admin/banner/add";
            }
            bannerService.addBanner(banner);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "파일 업로드 중 오류가 발생했습니다.");
            return "admin/banner/add";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "배너 추가 중 오류가 발생했습니다.");
            return "admin/banner/add";
        }
        return "redirect:/admin/banner/list";
    }

    @GetMapping("/admin/banner/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BannerDto banner = bannerService.getBannerById(id);
        model.addAttribute("banner", banner);
        return "admin/banner/edit";
    }

    @PostMapping("/admin/banner/edit")
    public String edit(BannerDto banner, MultipartFile file, Model model) {
        try {
            if (file != null && !file.isEmpty()) {
                Path directory = Paths.get(uploadDir);
                if (!Files.exists(directory)) {
                    Files.createDirectories(directory);
                }
                String filename = file.getOriginalFilename();
                Path filePath = directory.resolve(filename);
                file.transferTo(filePath.toFile());
                banner.setImagePath("/uploads/" + filename);

                // 디버깅을 위한 로그 추가
                System.out.println("파일 업로드 경로: " + filePath.toString());
                System.out.println("Banner imagePath: " + banner.getImagePath());
            }
            bannerService.updateBanner(banner);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "파일 업로드 중 오류가 발생했습니다.");
            return "admin/banner/edit";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "배너 수정 중 오류가 발생했습니다.");
            return "admin/banner/edit";
        }
        return "redirect:/admin/banner/list";
    }

    @PostMapping("/admin/banner/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        bannerService.deleteBanner(id);
        return "redirect:/admin/banner/list";
    }

    @PostMapping("/admin/banner/deleteSelected")
    public String deleteSelected(@RequestParam("bannerIds") List<Long> bannerIds) {
        bannerService.deleteBanners(bannerIds);
        return "redirect:/admin/banner/list";
    }

    @GetMapping("/")
    public String index(Model model) {
        List<BannerDto> banners = bannerService.getAllBanners();
        model.addAttribute("banners", banners);
        return "index";
    }
}
