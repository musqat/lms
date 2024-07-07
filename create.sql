CREATE TABLE member (
                        user_id VARCHAR(255) PRIMARY KEY,
                        user_name VARCHAR(255) NOT NULL,
                        phone VARCHAR(255),
                        password VARCHAR(255) NOT NULL,
                        reg_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        udt_dt TIMESTAMP,
                        email_auth_yn BOOLEAN DEFAULT true,
                        email_auth_dt TIMESTAMP,
                        email_auth_key VARCHAR(255),
                        reset_password_key VARCHAR(255),
                        reset_password_limit_dt TIMESTAMP,
                        admin_yn BOOLEAN DEFAULT FALSE,
                        user_status VARCHAR(255) DEFAULT 'ACTIVE',
                        zipcode VARCHAR(255),
                        addr VARCHAR(255),
                        addr_detail VARCHAR(255),
                        last_login_dt TIMESTAMP
);
CREATE TABLE login_history (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               user_id VARCHAR(255) NOT NULL,
                               login_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               ip_address VARCHAR(45) NOT NULL,
                               user_agent VARCHAR(255) NOT NULL,
                               FOREIGN KEY (user_id) REFERENCES member(user_id)
);
