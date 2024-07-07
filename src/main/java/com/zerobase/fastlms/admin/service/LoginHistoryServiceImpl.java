package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.entity.LoginHistory;
import com.zerobase.fastlms.admin.repository.LoginHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;

    @Override
    public List<LoginHistoryDto> getLoginHistories(String userId) {
        List<LoginHistory> loginHistories = loginHistoryRepository.findByUserId(userId);
        return LoginHistoryDto.of(loginHistories);
    }

    @Override
    public LoginHistoryDto getLastLogin(String userId) {
        LoginHistory loginHistory = loginHistoryRepository.findTopByUserIdOrderByLoginDateDesc(userId);
        return LoginHistoryDto.of(loginHistory);
    }

    @Override
    public LocalDateTime getLastLoginDate(String userId) {
        LoginHistory loginHistory = loginHistoryRepository.findTopByUserIdOrderByLoginDateDesc(userId);
        return loginHistory != null ? loginHistory.getLoginDate() : null;
    }




}
