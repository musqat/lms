package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerDto {

    private Long id;
    private String name;
    private String imagePath;
    private String link;
    private String target;
    private int sortOrder;
    private boolean published;
    private LocalDateTime regDt;

    public static BannerDto fromEntity(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .name(banner.getName())
                .imagePath(banner.getImagePath())
                .link(banner.getLink())
                .target(banner.getTarget())
                .sortOrder(banner.getSortOrder())
                .published(banner.isPublished())
                .regDt(banner.getRegDt())
                .build();
    }

    public Banner toEntity() {
        return Banner.builder()
                .id(id)
                .name(name)
                .imagePath(imagePath)
                .link(link)
                .target(target)
                .sortOrder(sortOrder)
                .published(published)
                .regDt(regDt != null ? regDt : LocalDateTime.now())
                .build();
    }
}
