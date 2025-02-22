package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.helper.AppConstants;
import com.portfolio.sarvech.models.ContactForm;
import com.portfolio.sarvech.services.ContactService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Value("${email.from}")
    private String from;

    private final AppConstants constants;

    private final JavaMailSender javaMailSender;

    public ContactServiceImpl(AppConstants constants, JavaMailSender javaMailSender) {
        this.constants = constants;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public boolean sendEmail(ContactForm contactForm) {
        String htmlEmail = AppConstants.createHTMLForEmail(contactForm);
        MimeMessage simpleMailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage,true,"UTF-8");
            helper.setTo(constants.EMAIL_SEND_TO);
            helper.setSubject(constants.EMAIL_SUBJECT);
            helper.setFrom(from);
            helper.setText(htmlEmail, true);
            javaMailSender.send(simpleMailMessage);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
}
