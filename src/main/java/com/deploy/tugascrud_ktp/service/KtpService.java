package com.deploy.tugascrud_ktp.service;

import com.deploy.tugascrud_ktp.model.dto.KtpAddRequest;
import com.deploy.tugascrud_ktp.model.dto.KtpDto;
import java.util.List;

public interface KtpService {
    KtpDto addKtp(KtpAddRequest request);
    List<KtpDto> getAllKtp();
    KtpDto getKtpById(Integer id);
    KtpDto updateKtp(Integer id, KtpAddRequest request);
    void deleteKtp(Integer id);
}