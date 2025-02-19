package com.zerobase.fastlms.admin.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String userId;
    private LocalDateTime loginDate;
    private String ipAddress;
    private String userAgent;

}
