package com.yang.javaspringsoot.modules.test.controller;

import com.yang.javaspringsoot.modules.test.entity.Country;
import com.yang.javaspringsoot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 *
 */
@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    private CountryService countryService;

    /*
    * 127.0.0.1/api/country/522
    *
    */
    @GetMapping("/country/{countryId}")
    public Country getCountryByCountryId(@PathVariable int countryId){

        return countryService.getCountryByCountryId(countryId);
    }

    /**
     * 127.0.0.1/api/country?countryName=China ---- get
     */
    @GetMapping("/country")
    public Country getCountryByCountryName(@RequestParam String countryName){

        return countryService.getCountryByCountryName(countryName);
    }

    /**
     * 127.0.0.1/api/redis/country/522
     * @param countryId
     * @return
     */
    @GetMapping("/redis/country/{countryId}")
    public Country mograteCountryByRedis(@PathVariable int countryId){
        return countryService.mograteCountryByRedis(countryId);
    }

}
