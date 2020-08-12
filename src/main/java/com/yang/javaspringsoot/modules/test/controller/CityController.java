package com.yang.javaspringsoot.modules.test.controller;

import com.github.pagehelper.PageInfo;
import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.common.vo.SearchVo;
import com.yang.javaspringsoot.modules.test.entity.City;
import com.yang.javaspringsoot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     *   127.0.0.1/api/cities/522
     */
    @GetMapping("/cities/{countryId}")
    public List<City> getCitiesByCountryId(@PathVariable int countryId){

        return cityService.getCitiesByCountryId(countryId);
    }

    /**
     * 127.0.0.1/api/cities/522 --- post
     * {"currentPage":"1","pageSize":"5"}
     */
    @PostMapping(value = "/cities/{countryId}", consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(
            @PathVariable int countryId, @RequestBody SearchVo searchVo) {
        return cityService.getCitiesBySearchVo(countryId, searchVo);
    }

    /**
     * 127.0.0.1/api/cities --- post
     * {"currentPage":"1","pageSize":"5","keyWord":"Sh","orderBy":"city_name","sort":"desc"}
     */
    @PostMapping(value = "/cities", consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(@RequestBody SearchVo searchVo){
        return cityService.getCitiesBySearchVo(searchVo);
    }

    /**
     * 127.0.0.1/api/insertCity ---- post
     * {"cityName":"liupanshui","localCityName":"CoolCity","countryId":"522"}
     */
    @PostMapping(value = "/insertCity",consumes = "application/json")
    public Result<City> insertCity(@RequestBody City city){
        return cityService.insertCity(city);
    }

    /**
     * 127.0.0.1/api/updateCity  -----put
     * "cityId"="2258","cityName"="LiuPanshui"
     * @param city
     * @return
     */
    @PutMapping(value = "/updateCity", consumes ="application/x-www-form-urlencoded")
    public Result<City> updateCity(@ModelAttribute City city){
        return cityService.updateCity(city);
    }

    /**.........
     * 127.0.0.1/api/dedeleteCity/2257
     * @param cityId
     * @return
     */
    @DeleteMapping(value = "/deleteCity/{cityId}")
    public Result<Object> deleteCity(@PathVariable int cityId){
        return cityService.deleteCity(cityId);
    }
}
