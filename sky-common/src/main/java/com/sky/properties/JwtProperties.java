package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT令牌相关配置
 */
@Component
@ConfigurationProperties(prefix = "sky.jwt")//配置在application.yml中
@Data

public class JwtProperties {

    /**
     * 管理端员工
     */
    private String adminSecretKey;  // 密钥
    private long adminTtl;          // 有效期限（毫秒）
    private String adminTokenName;  // 令牌名称

    /**
     * 用户端微信用户
     */
    private String userSecretKey;   // 密钥
    private long userTtl;           // 有效期限（毫秒）
    private String userTokenName;   // 令牌名称


}
