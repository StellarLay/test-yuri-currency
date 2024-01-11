package com.currency.Currency.controllers;

import com.currency.Currency.dto.CurrencyData;
import com.currency.Currency.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CurrencyController {

    private final CurrencyService currencyService;
    private List<CurrencyData> currencyDataList;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("currencyDataList", currencyDataList);
        return "index";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        currencyDataList = currencyService.processCsv(file, model);
        return "redirect:/";
    }
}