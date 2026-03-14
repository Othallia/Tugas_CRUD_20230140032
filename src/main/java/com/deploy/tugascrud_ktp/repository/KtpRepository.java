package com.deploy.tugascrud_ktp.repository;

import com.deploy.tugascrud_ktp.model.entity.Ktp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KtpRepository extends JpaRepository<Ktp, Integer> {
}