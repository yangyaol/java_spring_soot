package com.yang.javaspringsoot.modules.test.service;

import com.yang.javaspringsoot.modules.test.entity.Country;

public interface CountryService {
    Country getCountryByCountryId(int countryId);

    Country getCountryByCountryName(String countryName);
}
