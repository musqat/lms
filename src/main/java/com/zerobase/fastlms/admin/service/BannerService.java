package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import java.util.List;

public interface BannerService {

    BannerDto addBanner(BannerDto bannerDto);
    List<BannerDto> getAllBanners();
    BannerDto getBannerById(Long id);
    void updateBanner(BannerDto bannerDto);
    void deleteBanner(Long id);
    void deleteBanners(List<Long> ids);
}
