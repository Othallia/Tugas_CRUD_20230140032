package com.deploy.tugascrud_ktp.service.impl;

import com.deploy.tugascrud_ktp.mapper.KtpMapper;
import com.deploy.tugascrud_ktp.model.dto.KtpAddRequest;
import com.deploy.tugascrud_ktp.model.dto.KtpDto;
import com.deploy.tugascrud_ktp.model.entity.Ktp;
import com.deploy.tugascrud_ktp.repository.KtpRepository;
import com.deploy.tugascrud_ktp.service.KtpService;
import com.deploy.tugascrud_ktp.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KtpServiceImpl implements KtpService {

    @Autowired
    private KtpRepository ktpRepository;

    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public KtpDto addKtp(KtpAddRequest request) {
        validationUtil.validate(request);

        Ktp ktp = Ktp.builder()
                .nomorKtp(request.getNomorKtp())
                .namaLengkap(request.getNamaLengkap())
                .alamat(request.getAlamat())
                .tanggalLahir(request.getTanggalLahir())
                .jenisKelamin(request.getJenisKelamin())
                .build();

        ktpRepository.save(ktp);
        return KtpMapper.MAPPER.toKtpDtoData(ktp);
    }

    @Override
    public List<KtpDto> getAllKtp() {
        List<Ktp> ktps = ktpRepository.findAll();
        List<KtpDto> ktpDtos = new ArrayList<>();
        for (Ktp ktp : ktps) {
            ktpDtos.add(KtpMapper.MAPPER.toKtpDtoData(ktp));
        }
        return ktpDtos;
    }

    @Override
    public KtpDto getKtpById(Integer id) {
        Ktp ktp = ktpRepository.findById(id).orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan"));
        return KtpMapper.MAPPER.toKtpDtoData(ktp);
    }

    @Override
    public KtpDto updateKtp(Integer id, KtpAddRequest request) {
        validationUtil.validate(request);
        Ktp existingKtp = ktpRepository.findById(id).orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan"));

        Ktp ktp = Ktp.builder()
                .id(existingKtp.getId())
                .nomorKtp(request.getNomorKtp())
                .namaLengkap(request.getNamaLengkap())
                .alamat(request.getAlamat())
                .tanggalLahir(request.getTanggalLahir())
                .jenisKelamin(request.getJenisKelamin())
                .build();

        ktpRepository.save(ktp);
        return KtpMapper.MAPPER.toKtpDtoData(ktp);
    }

    @Override
    public void deleteKtp(Integer id) {
        Ktp ktp = ktpRepository.findById(id).orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan"));
        ktpRepository.delete(ktp);
    }
}