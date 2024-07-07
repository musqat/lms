package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;

    @Override
    public BannerDto addBanner(BannerDto bannerDto) {
        Banner banner = bannerDto.toEntity();
        banner = bannerRepository.save(banner);
        return BannerDto.fromEntity(banner);
    }

    @Override
    public List<BannerDto> getAllBanners() {
        return bannerRepository.findAll()
                .stream()
                .map(BannerDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public BannerDto getBannerById(Long id) {
        return bannerRepository.findById(id)
                .map(BannerDto::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("Invalid banner Id:" + id));
    }

    @Override
    public void updateBanner(BannerDto bannerDto) {
        Banner banner = bannerDto.toEntity();
        bannerRepository.save(banner);
    }

    @Override
    public void deleteBanner(Long id) {
        bannerRepository.deleteById(id);
    }

    @Override
    public void deleteBanners(List<Long> ids) {
        ids.forEach(bannerRepository::deleteById);
    }
}
