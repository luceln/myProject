package com.library.entity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("appConfig")
public class AppConfig {
    /**
     * 超级管理员
     */
    @Value("${admin.emails:}")
    private String adminEmails;

    @Value("${send.user.name:}")
    private String sendUserName;

    /**
     * 注册发送邮件标题
     */
    @Value("${register.email.title:}")
    private String registerEmailTitle;

    /**
     * 注册发送邮件内容
     */
    @Value("${register.email.content:}")
    private String registerEmailContent;

    public String getSendUserName() {
        return sendUserName;
    }

    public String getAdminEmails() {
        return adminEmails;
    }

    public String getRegisterEmailTitle() {
        return registerEmailTitle;
    }

    public String getRegisterEmailContent() {
        return registerEmailContent;
    }
}
