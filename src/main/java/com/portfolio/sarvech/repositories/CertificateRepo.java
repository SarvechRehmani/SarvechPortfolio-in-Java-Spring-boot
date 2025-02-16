package com.portfolio.sarvech.repositories;

import com.portfolio.sarvech.models.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepo extends JpaRepository<Certificate, Long> {
}
