package com.practice.spring.controller;

import com.practice.spring.service.IBanjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BanjiController {
    @Autowired
    private IBanjiService banjiService;

    public void selectAll() {
        System.out.println("BnajiController.selectAll");
        banjiService.selectAll();
    }
}
