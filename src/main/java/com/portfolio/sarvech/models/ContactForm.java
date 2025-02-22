package com.portfolio.sarvech.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactForm {
    private String name;
    private String company;
    private String email;
    private String phone;
    private String message;
}
