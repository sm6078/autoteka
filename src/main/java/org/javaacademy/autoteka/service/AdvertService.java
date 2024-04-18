package org.javaacademy.autoteka.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javaacademy.autoteka.dto.AdvertDtoRs;
import org.javaacademy.autoteka.entity.Advert;
import org.javaacademy.autoteka.repository.AdvertRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertService {
    private final AdvertRepository advertRepository;

    public AdvertDtoRs create(@NonNull AdvertDtoRs dto) {
        Advert ad = new Advert(dto.getBrand(), dto.getModel(), dto.getPrice(), dto.getColor(), dto.getPostingDate());
        return convertToDtoRs(advertRepository.add(ad));
    }

    public List<AdvertDtoRs> getAll() {
        return advertRepository.findAll().stream()
                .map(this::convertToDtoRs).toList();
    }

    private AdvertDtoRs convertToDtoRs(@NonNull Advert ad) {
        return new AdvertDtoRs(ad.getUuid(), ad.getBrand(), ad.getColor(),
                ad.getPrice(), ad.getModel(), ad.getPostingDate());

    }

    public AdvertDtoRs getById(@NonNull String id) {
        return advertRepository.findByKey(id)
                .map(this::convertToDtoRs)
                .orElseThrow();
    }

    public boolean deleteById(@NonNull String id) {
        return advertRepository.deleteById(id);
    }

    public boolean deleteAll() {
        return advertRepository.deleteAll();
    }
}
