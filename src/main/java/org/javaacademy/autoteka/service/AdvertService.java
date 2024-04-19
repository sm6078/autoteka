package org.javaacademy.autoteka.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javaacademy.autoteka.dto.AdvertDtoRq;
import org.javaacademy.autoteka.dto.AdvertDtoRs;
import org.javaacademy.autoteka.dto.AdvertFilterDtoRq;
import org.javaacademy.autoteka.entity.Advert;
import org.javaacademy.autoteka.repository.AdvertRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertService {
    private final AdvertRepository advertRepository;

    public AdvertDtoRs create(@NonNull AdvertDtoRq dto) {
        Advert ad = new Advert(dto.getBrand(), dto.getColor(), dto.getPrice(), dto.getModel(), dto.getPostingDate());
        advertRepository.add(ad);
        return convertToDtoRs(ad);
    }

    public List<AdvertDtoRs> getAll() {
        return advertRepository.findAll().stream()
                .map(this::convertToDtoRs).toList();
    }

    private AdvertDtoRs convertToDtoRs(Advert advert) {
        return new AdvertDtoRs(advert.getUuid(), advert.getBrand(), advert.getColor(),
                advert.getPrice(), advert.getModel(), advert.getPostingDate());

    }

    public AdvertDtoRs getById(@NonNull String id) {
        return advertRepository.findById(id)
                .map(this::convertToDtoRs)
                .orElseThrow();
    }

    public List<AdvertDtoRs> getByDate(@NonNull LocalDate date) {

        return advertRepository.findByDate(date)
                .stream()
                .map(this::convertToDtoRs)
                .toList();
    }

    public boolean deleteById(@NonNull String id) {
        return advertRepository.deleteById(id);
    }

    public boolean deleteAll() {
        return advertRepository.deleteAll();
    }

    public List<AdvertDtoRs> getByParameters(AdvertFilterDtoRq options) {
        List<Advert> list = new ArrayList<>();
        Set<Advert> result = new HashSet<>();
        if (options.getBrand() == null && options.getColor() == null && options.getPrice() == null && options.getModel() == null) {
            return getAll();
        } else {
            if (options.getBrand() != null) {
                List<Advert> temp = advertRepository.findByBrand(options.getBrand());
                list = temp;
            }
            if (options.getColor() != null) {
                List<Advert> temp = advertRepository.findByColor(options.getColor());
                result = list.stream().distinct().filter(temp::contains).collect(Collectors.toSet());
                list = result.stream().toList();

            }
            if (options.getPrice() != null) {
                List<Advert> temp = advertRepository.findByPrice(options.getPrice());
                result = list.stream().distinct().filter(temp::contains).collect(Collectors.toSet());
                list = result.stream().toList();


            }
            if (options.getModel() != null) {
                List<Advert> temp = advertRepository.findByModel(options.getModel());
                if (!result.isEmpty()) {
                    result = list.stream().distinct().filter(temp::contains).collect(Collectors.toSet());
                } else {
                    result.addAll(temp);
                }
            }
        }
        return dtoRsConvertToList(result);
    }

    private List<AdvertDtoRs> dtoRsConvertToList(Collection<Advert> adverts) {
        return adverts.stream().map(this::convertToDtoRs).toList();

    }
}
