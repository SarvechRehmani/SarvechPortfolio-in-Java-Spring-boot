package com.portfolio.sarvech.helper;

import com.portfolio.sarvech.models.ContactForm;
import org.springframework.stereotype.Component;

@Component
public class AppConstants {

    public final long DetailsID = 101;
    public final long mainSkillOne = 201;
    public final long mainSkillTwo = 202;

    public final int PROJECT_IMAGE_WIDTH = 500;
    public final int PROJECT_IMAGE_HEIGHT = 500;
    public final String PROJECT_IMAGE_CROP = "fill";
    public final String CLOUDINARY_PROJECT_IMAGE_FOLDER = "project_images/";
    public final String CLOUDINARY_SKILL_ICON_FOLDER = "skill_icons/";
    public final String CLOUDINARY_PROFILE_FOLDER = "profile/";

    public final int CERTIFICATE_WIDTH = 1755;
    public final int CERTIFICATE_HEIGHT = 1240;
    public final String CLOUDINARY_CERTIFICATE_FOLDER = "certificates/";

    public final String EMAIL_SEND_TO = "sarwech99@gmail.com";
    public final String EMAIL_SUBJECT = "Email From Portfolio Website";



    public static String createHTMLForEmail(ContactForm contactForm){
        return "<body style=\"margin: 0; padding: 0; background-color: #f9f9f9; font-family: Helvetica, Arial, sans-serif;\">\n" +
                "    <!-- Outer Table -->\n" +
                "    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" bgcolor=\"#f9f9f9\">\n" +
                "        <tr>\n" +
                "            <td align=\"center\" style=\"padding: 20px 0;\">\n" +
                "                <!-- Inner Container -->\n" +
                "                <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"max-width: 600px; background-color: #ffffff;\">\n" +
                "                    <!-- Header -->\n" +
                "                    <tr>\n" +
                "                        <td style=\"background: linear-gradient(to right, #4B0082, #6A0DAD); color: #ffffff; padding: 30px; text-align: center; font-size: 28px; font-weight: bold; line-height: 1.2;\">\n" +
                "                            New Message Alert!\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <!-- Introduction -->\n" +
                "                    <tr>\n" +
                "                        <td style=\"padding: 20px; text-align: center; color: #6A0DAD; font-size: 16px; line-height: 1.5;\">\n" +
                "                            <p style=\"margin: 0;\">Someone reached out via your website. Check out their details below!</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <!-- Content (Card Style) -->\n" +
                "                    <tr>\n" +
                "                        <td style=\"padding: 0 20px 20px 20px;\">\n" +
                "                            <table width=\"100%\" cellpadding=\"15\" cellspacing=\"0\" border=\"0\" style=\"background-color: #E6E6FA; border: 1px solid #d0d0d0; box-shadow: 0 2px 4px rgba(0,0,0,0.1);\">\n" +
                "                                <tr>\n" +
                "                                    <td style=\"font-weight: bold; color: #6A0DAD; width: 30%; vertical-align: top;\">\uD83D\uDC64 Name:</td>\n" +
                "                                    <td style=\"color: #333333; width: 70%; vertical-align: top;\">"+contactForm.getName()+"</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td style=\"font-weight: bold; color: #6A0DAD; width: 30%; vertical-align: top;\">\uD83C\uDFE2 Company:</td>\n" +
                "                                    <td style=\"color: #333333; width: 70%; vertical-align: top;\">"+contactForm.getCompany()+"</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td style=\"font-weight: bold; color: #6A0DAD; width: 30%; vertical-align: top;\">\uD83D\uDCE7 Email:</td>\n" +
                "                                    <td style=\"color: #333333; width: 70%; vertical-align: top;\">\n" +
                "                                        <a href=\"mailto:"+contactForm.getEmail()+"\" style=\"color: #6A0DAD; text-decoration: underline;\">"+contactForm.getEmail()+"</a>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td style=\"font-weight: bold; color: #6A0DAD; width: 30%; vertical-align: top;\">\uD83D\uDCDE Phone:</td>\n" +
                "                                    <td style=\"color: #333333; width: 70%; vertical-align: top;\">\n" +
                "                                        <a href=\"tel:"+contactForm.getPhone()+"\" style=\"color: #6A0DAD; text-decoration: underline;\">"+contactForm.getPhone()+"</a>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td style=\"font-weight: bold; color: #6A0DAD; width: 30%; vertical-align: top;\">\uD83D\uDCAC Message:</td>\n" +
                "                                    <td style=\"color: #333333; width: 70%; vertical-align: top; word-wrap: break-word;\">"+contactForm.getMessage()+"</td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <!-- CTA -->\n" +
                "                    <tr>\n" +
                "                        <td style=\"padding: 0 20px 20px 20px; text-align: center;\">\n" +
                "                            <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "                                <tr>\n" +
                "                                    <td style=\"background-color: #4B0082; padding: 15px 30px; border-radius: 8px; border: 2px solid #330066;\">\n" +
                "                                        <a href=\"mailto:"+contactForm.getEmail()+"\" style=\"color: #ffffff; text-decoration: none; font-size: 18px; font-weight: bold; display: block;\">Get Back to Them!</a>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <!-- Footer -->\n" +
                "                    <tr>\n" +
                "                        <td style=\"background-color: #4B0082; color: #ffffff; padding: 15px; text-align: center; font-size: 12px; line-height: 1.5;\">\n" +
                "                            <p style=\"margin: 0;\">© 2025 Sarvech Portfolio. All rights reserved.</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>";
    }


}
