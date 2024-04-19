package org.javaacademy.autoteka.controller;

import java.time.LocalDate;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javaacademy.autoteka.dto.AdvertDtoRq;
import org.javaacademy.autoteka.dto.AdvertDtoRs;
import org.javaacademy.autoteka.dto.AdvertFilterDtoRq;
import org.javaacademy.autoteka.dto.MessageDtoRs;
import org.javaacademy.autoteka.service.AdvertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/advert")
public class AdvertController {
    private final AdvertService advertService;

    /**
     * getAll()
     */
    @GetMapping
    public List<AdvertDtoRs> getAllAdvert() {
        return advertService.getAll();
    }

    /**
     * get by id
     */
    @GetMapping("/{id}")
    public AdvertDtoRs getAdvertById(@PathVariable @NonNull String id) {
        return advertService.getById(id);
    }

    /**
     * get all advert by date
     */
    @GetMapping("filter")
    public List<AdvertDtoRs> getAdvertByDate(@RequestParam(required = true)
                                             @NonNull LocalDate date) {
        return advertService.getByDate(date);
    }

    @GetMapping("filter/option")
    public List<AdvertDtoRs> getAdvertByParameters(@RequestBody AdvertFilterDtoRq filterDtoRq) {
        return advertService.getByParameters(filterDtoRq);
    }

    /**
     * create new advert
     */
    @PostMapping
    public ResponseEntity<AdvertDtoRs> createAdvert(@RequestBody @NonNull AdvertDtoRq dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(advertService.create(dto));
    }

    /**
     * delete by id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAdvert(@PathVariable String id) {

        return advertService.deleteById(id)
                ? ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new MessageDtoRs("Объявление успешно удалено"))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageDtoRs("При удалении возникла ошибка. Повторите поптытку."));
    }
}
