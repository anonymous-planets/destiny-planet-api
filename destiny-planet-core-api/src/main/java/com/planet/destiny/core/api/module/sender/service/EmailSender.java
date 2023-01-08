package com.planet.destiny.core.api.module.sender.service;


import com.planet.destiny.core.api.constant.SenderType;
import com.planet.destiny.core.api.module.sender.item.EmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Slf4j
@Component(SenderType.TypeConstants.EMAIL_CODE)
public class EmailSender implements SenderType.Sender<EmailDto> {

    private final JavaMailSender javaMailSender;

    private MimeMessage message;

    private MimeMessageHelper messageHelper;

    private SpringTemplateEngine templateEngine;

    @Value("${spring.mail.default-sender}")
    private String fromAddress;

    @Value("${spring.mail.default-username}")
    private String fromName;


    public EmailSender(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) throws MessagingException {
        this.javaMailSender = javaMailSender;
        this.message = javaMailSender.createMimeMessage();
        this.messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        this.templateEngine = templateEngine;
    }



    @Override
    public void send(EmailDto emailDto, SenderCallback callback) {
        boolean result = true;
        try{
            // 보내는 사람 설정
            this.setFromInfo(fromAddress, fromName);

            // 받는 사람 설정
            this.setToInfo(emailDto.getToInfos());

            // 제목
            this.setSubject(emailDto.getSubject());

            // 내용
            if(emailDto.getIsUseHtml()) {
                this.setTemplateContent(emailDto.getTemplateFileName(), emailDto.getParams());
            } else {
                this.setContent(emailDto.getContent());
            }

            // 첨부 파일
            if(emailDto.getAttachFiles() != null) {
                for(EmailDto.AttachFile attachFile : emailDto.getAttachFiles()) {
                    this.setAttach(attachFile);
                }
            }

            // 메일 전송
            javaMailSender.send(message);
        } catch(MessagingException e ) {
            result = false;
        } catch(UnsupportedEncodingException e) {
            result = false;
        } catch(IOException e) {
            result = false;
        } finally {
            // 메일 전송 성공 여부
            callback.execute(emailDto.getIdx(), result);
        }
    }


    // 보내는 사람 이메일, 이름 설정
    private void setFromInfo(String fromAddress, String fromName) throws MessagingException, UnsupportedEncodingException {
         messageHelper.setFrom(new InternetAddress(fromAddress, fromName));
    }


    // 받는 사람 이메일, 이름 설정
    private void setToInfo(EmailDto.PersonInfo toInfo) throws MessagingException, UnsupportedEncodingException {
        messageHelper.setTo(new InternetAddress(toInfo.getAddress(), toInfo.getName()));
    }

    // 받는 사람 이메일, 이름 설정
    private void setToInfo(List<EmailDto.PersonInfo> toInfo) throws MessagingException, UnsupportedEncodingException {
        InternetAddress[] toInfos = new InternetAddress[toInfo.size()];
        for(int i=0; i< toInfo.size(); i++) {
            toInfos[i] = new InternetAddress(toInfo.get(i).getAddress(), toInfo.get(i).getName());
        }
        messageHelper.setTo(toInfos);
    }

    // 제목 설정
    private void setSubject(String subject) throws MessagingException{
        messageHelper.setSubject(subject);
    }

    // 내용 설정(Plain Text)
    private void setContent(String content) throws MessagingException {
        messageHelper.setText(content);
    }

    // 내용 설정(Template)
    private void setTemplateContent(String templateFileName, Map<String, Object> params) throws MessagingException {
        Context context = new Context();

        if(params != null) {
            params.forEach((key, value) -> {
                context.setVariable(key, value);
            });
        }

        String html = templateEngine.process(templateFileName, context);
        messageHelper.setText(html, true);
    }

    // 첨부 파일
    private void setAttach(EmailDto.AttachFile attachFile) throws MessagingException, IOException {
        File file = new ClassPathResource(attachFile.getFilePath() + attachFile.getFileName() + "." + attachFile.getFileExtension()).getFile();
        FileSystemResource fsr = new FileSystemResource(file);
        messageHelper.addAttachment(attachFile.getOriginalFileName() + "." + attachFile.getFileExtension(), fsr);
    }
}
