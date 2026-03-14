package com.deploy.tugascrud_ktp.mapper;

import com.deploy.tugascrud_ktp.model.entity.Ktp;
import com.deploy.tugascrud_ktp.model.dto.KtpAddRequest;
import com.deploy.tugascrud_ktp.model.dto.KtpDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface KtpMapper {
    KtpMapper MAPPER = Mappers.getMapper(KtpMapper.class);

    KtpDto toKtpDtoData(Ktp ktp);
    
    Ktp toKtpEntity(KtpAddRequest request);
}