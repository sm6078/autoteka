package org.javaacademy.autoteka.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.autoteka.service.AdvertService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/advert")
public class AdvertController {
    private final AdvertService advertService;
}
