package com.example.bsm.mail;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import java.util.Map;


@Slf4j
@Service
@AllArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;


    public void sendmail(String To,String subject, String Text) throws MessagingException {
        log.info("sending donation request to {}",To);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo(To);
        helper.setSubject(subject);
        helper.setText(Text,true);
        javaMailSender.send(message);

    }
    public String genratecontent(String templateName, Map<String, Object> variables){
        Context context = new Context();
        context.setVariables(variables);
        return templateEngine.process(templateName,context);
    }
}
