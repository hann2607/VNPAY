
package com.vnpay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "vnp")
@Data
public class AppConfig {
    private String payUrl;
    private String tmnCode;
    private String hashSecret;
    private String version;
    private String commandPay;
    private String commandToken;
}
