package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.LoginHistoryDto;

import java.time.LocalDateTime;
import java.util.List;

public interface LoginHistoryService {


    List<LoginHistoryDto> getLoginHistories(String userId);

    LoginHistoryDto getLastLogin(String userId);

    /**
     * 마지막로그인 기록
     */

    LocalDateTime getLastLoginDate(String userId);
}
