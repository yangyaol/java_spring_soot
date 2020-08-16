package com.yang.javaspringsoot.modules.test.service.impl;

import com.yang.javaspringsoot.modules.test.dao.CountryDao;
import com.yang.javaspringsoot.modules.test.entity.Country;
import com.yang.javaspringsoot.modules.test.service.CountryService;
import com.yang.javaspringsoot.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }

    @Override
    public Country mograteCountryByRedis(int countryId) {
        Country country = countryDao.getCountryByCountryId(countryId);

        String countryKey = String.format("country%d",countryId);
        redisUtils.set(countryKey,country);

        return (Country) redisUtils.get(countryKey);
    }


}
