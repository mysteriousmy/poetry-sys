package com.poetry.common.util;

import com.poetry.common.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EmailUtil {
    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendVerifyCode(String email, String value){
        SimpleMailMessage message = new SimpleMailMessage();
        try{
            message.setFrom(from);
            message.setTo(email);
            message.setSubject("古诗词管理系统 - 验证码");
            message.setText("你的验证码为：" + value);
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("验证码发送失败！请稍后重试！");
        }
    }
}
