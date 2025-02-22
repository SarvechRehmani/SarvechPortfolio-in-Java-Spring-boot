package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.models.ContactForm;
import com.portfolio.sarvech.services.ContactService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/send")
    public String sendMail(@ModelAttribute ContactForm contactForm){
        System.out.println(contactForm);
        boolean flag = contactService.sendEmail(contactForm);
        if(flag){
            return "success";
        }
        return "error";
    }
}
