package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.Message;
import com.portfolio.sarvech.helper.MessageType;
import com.portfolio.sarvech.models.Certificate;
import com.portfolio.sarvech.services.CertificateService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/certificate")
public class CertificateController {

    private static final Logger logger = LoggerFactory.getLogger(CertificateController.class);
    private final CertificateService certificateService;
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/add")
    public String addCertificate(){
        return "/admin/add-certificate";
    }
    @PostMapping("/add")
    public String saveCertificate(MultipartFile file, HttpSession session){
        if (file == null || file.isEmpty()) {
            logger.error("Certificate image is empty");
            session.setAttribute("fileMessage", new Message("Please select a certificate image", MessageType.ERROR));
            return "/admin/add-certificate";
        }
        this.certificateService.saveCertificate(file);
        logger.info("Certificate saved successfully");
        session.setAttribute("message", new Message("Certificate added successfully!", MessageType.SUCCESS));
        return "redirect:/admin/dashboard";
    }


    @GetMapping("/delete/{id}")
    public String deleteCertificate(@PathVariable long id, HttpSession session){
        Certificate certificate  = this.certificateService.findCertificateById(id);
        if(certificate == null){
            logger.error("Certificate not found with id: {}", id);
            session.setAttribute("message", new Message("Certificate not found with id: "+id, MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        this.certificateService.deleteCertificateById(id);
        logger.info("Certificate deleted successfully: {}", id);
        session.setAttribute("message", new Message("Certificate deleted successfully!", MessageType.SUCCESS));
        return "redirect:/admin/dashboard";
    }


}
