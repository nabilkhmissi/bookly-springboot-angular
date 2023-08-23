package com.springboot.libraryappmongo.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {
    public static final String NEW_USER_ACCOUNT_VERIFICATION = "Account activation";
    public static final String UTF_8_ENCODING = "UTF-8";
    public static final String ACTIVATION_TEMPLATE = "verification_email";
    public static final String RECOVER_PASSWORD_TEMPLATE = "recover_password_email";
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;
    @Value("${spring.mail.verify.fromEmail}")
    private String fromEmail;
    @Value("${spring.mail.verify.host}")
    private String HOST;

    @Async
    public void sendSimpleMailMessage(String name, String to, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText("some text here");
            emailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Async
    public void sendEmailAction(String action, String name, String to, String token) {
        String template = null;
        String url = null;

        if (action.equals("activation")){
            template = ACTIVATION_TEMPLATE;
            url = buildActivationLink(HOST, token);
        }else{
            template = RECOVER_PASSWORD_TEMPLATE;
            url = buildActivationLink(HOST, token);
        }
            try {
                Context context = new Context();
                context.setVariables(Map.of("name", name,
                        "url", url));
                String text = templateEngine.process(template, context);

                MimeMessage message = getMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
                helper.setPriority(1);
                helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
                helper.setFrom(fromEmail);
                helper.setTo(to);
                helper.setText(text, true);
                emailSender.send(message);
            } catch (Exception exception) {
                throw new RuntimeException(exception.getMessage());
            }
    }

    private String buildActivationLink(String host, String token) {
        return String.format("%s/api/v1/auth/verify?token=%s", host, token);
    }
    private String buildRecoverLink(String host, String token) {
        return String.format("%s/api/v1/auth/password/recover?token=%s", host, token);
    }
    private MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }
}