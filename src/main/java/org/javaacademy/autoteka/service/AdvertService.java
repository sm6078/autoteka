package org.javaacademy.autoteka.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javaacademy.autoteka.dto.AdvertDtoRq;
import org.javaacademy.autoteka.dto.AdvertDtoRs;
import org.javaacademy.autoteka.dto.AdvertFilterDtoRq;
import org.javaacademy.autoteka.entity.Advert;
import org.javaacademy.autoteka.repository.AdvertRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertService {
    private final AdvertRepository advertRepository;

    public AdvertDtoRs create(@NonNull AdvertDtoRq dto) {
        Advert ad = new Advert(dto.getBrand(), dto.getColor(),
                dto.getPrice(), dto.getModel(), dto.getPostingDate());
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
        Set<Advert> set;
        if (options.getBrand() == null && options.getColor() == null
                && options.getPrice() == null
                && options.getModel() == null) {
            return getAll();
        } else {
            if (options.getBrand() != null) {
                list = advertRepository.findByBrand(options.getBrand());
            }
            if (options.getColor() != null) {
                List<Advert> listColor = advertRepository.findByColor(options.getColor());
                if (list.isEmpty()) {
                    list = listColor;
                } else {
                    set = list.stream().distinct()
                            .filter(listColor::contains)
                            .collect(Collectors.toSet());
                    list = set.stream().toList();
                }
            }
            if (options.getPrice() != null) {
                List<Advert> listPrice = advertRepository.findByPrice(options.getPrice());
                if (list.isEmpty()) {
                    list = listPrice;
                } else {
                    set = list.stream().distinct()
                            .filter(listPrice::contains)
                            .collect(Collectors.toSet());
                    list = set.stream().toList();
                }
            }
            if (options.getModel() != null) {
                List<Advert> listModel = advertRepository.findByModel(options.getModel());
                if (list.isEmpty()) {
                    list = listModel;
                } else {
                    set = list.stream().distinct()
                            .filter(listModel::contains)
                            .collect(Collectors.toSet());
                    list = set.stream().toList();
                }
            }
        }
        return dtoRsConvertToList(list);
    }

    private List<AdvertDtoRs> dtoRsConvertToList(Collection<Advert> adverts) {
        return adverts.stream().map(this::convertToDtoRs).toList();

    }
}
