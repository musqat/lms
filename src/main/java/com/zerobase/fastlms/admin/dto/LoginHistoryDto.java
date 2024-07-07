package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.LoginHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class LoginHistoryDto {

    private Long id;
    private String userId;
    private LocalDateTime loginDate;
    private String ipAddress;
    private String userAgent;


    public static LoginHistoryDto of(LoginHistory loginHistory) {
        if (loginHistory == null) return null;
        return new LoginHistoryDto(
                loginHistory.getId(),
                loginHistory.getUserId(),
                loginHistory.getLoginDate(),
                loginHistory.getIpAddress(),
                loginHistory.getUserAgent()
        );
    }

    public static List<LoginHistoryDto> of(List<LoginHistory> loginHistories) {
        return loginHistories.stream()
                .map(LoginHistoryDto::of)
                .collect(Collectors.toList());
    }

}
