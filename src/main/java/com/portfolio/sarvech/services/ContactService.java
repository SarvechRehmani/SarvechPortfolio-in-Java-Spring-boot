package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.ContactForm;

public interface ContactService {
    boolean sendEmail(ContactForm contactForm);

}
