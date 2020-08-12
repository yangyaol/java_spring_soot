package com.yang.javaspringsoot.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.common.vo.SearchVo;
import com.yang.javaspringsoot.modules.test.entity.City;

import java.util.List;

public interface CityService {

    List<City>  getCitiesByCountryId(int countryId);

    PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo);

    PageInfo<City> getCitiesBySearchVo(SearchVo searchVo);

    Result<City> insertCity(City city);

    Result<City> updateCity(City city);

    Result<Object> deleteCity(int cityId);
}
