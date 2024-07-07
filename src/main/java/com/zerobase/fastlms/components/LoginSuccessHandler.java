package com.zerobase.fastlms.components;

import com.zerobase.fastlms.admin.entity.LoginHistory;
import com.zerobase.fastlms.admin.repository.LoginHistoryRepository;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@Slf4j
@Component("customLoginSuccessHandler") // 빈 이름 변경
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        String ipAddress = RequestUtils.getClientIP(request);
        String userAgent = RequestUtils.getUserAgent(request);

        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setUserId(username);
        loginHistory.setLoginDate(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
        loginHistory.setIpAddress(ipAddress);
        loginHistory.setUserAgent(userAgent);

        loginHistoryRepository.save(loginHistory);


        super.onAuthenticationSuccess(request, response, authentication);
    }
}
