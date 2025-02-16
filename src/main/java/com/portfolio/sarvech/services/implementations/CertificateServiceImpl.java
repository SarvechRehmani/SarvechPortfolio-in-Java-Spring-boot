package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.helper.AppConstants;
import com.portfolio.sarvech.models.Certificate;
import com.portfolio.sarvech.repositories.CertificateRepo;
import com.portfolio.sarvech.services.CertificateService;
import com.portfolio.sarvech.services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepo certificateRepo;
    private final ImageService imageService;
    private final AppConstants constants;

    public CertificateServiceImpl(CertificateRepo certificateRepo, ImageService imageService, AppConstants constants) {
        this.certificateRepo = certificateRepo;
        this.imageService = imageService;
        this.constants = constants;
    }

    @Override
    public Certificate saveCertificate (MultipartFile file) {
        String certificatePublicId = this.constants.CLOUDINARY_CERTIFICATE_FOLDER + UUID.randomUUID().toString();
        String certificateUrl = this.imageService.uploadImage(file, certificatePublicId, this.constants.CLOUDINARY_CERTIFICATE_FOLDER);
        Certificate certificate = new Certificate();
        certificate.setCertificateUrl(certificateUrl);
        certificate.setCloudinaryPublicId(certificatePublicId);
        return this.certificateRepo.save(certificate);
    }

    @Override
    public Certificate updateCertificate(Certificate certificate) {
        return this.certificateRepo.save(certificate);
    }

    @Override
    public void deleteCertificateById(long id) {
        this.certificateRepo.deleteById(id);
    }

    @Override
    public Certificate findCertificateById(long id) {
        return this.certificateRepo.findById(id).orElse(null);
    }

    @Override
    public List<Certificate> findAllCertificates() {
        return this.certificateRepo.findAll();
    }
}
