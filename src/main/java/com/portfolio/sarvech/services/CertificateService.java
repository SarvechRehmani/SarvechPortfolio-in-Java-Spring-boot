package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.Certificate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CertificateService {

    Certificate saveCertificate(MultipartFile file);
    Certificate updateCertificate(Certificate certificate);
    void deleteCertificateById(long id);
    Certificate findCertificateById(long id);
    List<Certificate> findAllCertificates();
}
