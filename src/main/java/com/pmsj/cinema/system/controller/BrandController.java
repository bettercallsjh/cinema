package com.pmsj.cinema.system.controller;

import com.github.pagehelper.PageInfo;
import com.pmsj.cinema.common.entity.Brand;
import com.pmsj.cinema.system.exception.NullParametersException;
import com.pmsj.cinema.system.exception.PageInfoErrorException;
import com.pmsj.cinema.system.service.BrandService;
import com.pmsj.cinema.system.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author mhd
 * @className BrandController
 * @description TODO
 * @create 2020/7/1
 * @since 1.0.0
 */
@Controller
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    @RequestMapping("/getAll")
    @ResponseBody
    public ReturnUtil getAllBrands(Integer page, Integer limit, Brand brand) {
        try {
            PageInfo<Brand> pageInfo = brandService.getAllBrands(page, limit, brand);
            ReturnUtil returnUtil = new ReturnUtil(0, "success", pageInfo.getTotal(), pageInfo.getList());
            return returnUtil;
        } catch (PageInfoErrorException e) {
            e.printStackTrace();
            return new ReturnUtil().failure();
        }
    }


    @RequestMapping("/getAllBrandsJson")
    @ResponseBody
    public List<Brand> getAllBrandsJson() {
        return brandService.getAllBrandsJson();

    }


    @RequestMapping("/add")
    @ResponseBody
    public String addBrand(@RequestBody Brand brand) {
        try {
            if (brandService.getBrandByName(brand.getBrandName())!=null){
                return "品牌重复";
            }else{
                brandService.addBrand(brand);
                return "SUCCESS";
            }


        } catch (NullParametersException e) {
            e.printStackTrace();
            return "ERROR";
        }

    }

    @RequestMapping("/deleteBatches")
    @ResponseBody
    public String deleteBatches(@RequestBody List<Brand> list) {
        try {
            brandService.deleteBatches(list);
            return "SUCCESS";
        } catch (NullParametersException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    /**
     * 编辑初始化
     *
     * @param brandId
     * @param maps
     * @return
     */
    @RequestMapping("/editInit")
    public String editInit(@RequestParam Integer brandId, Map maps) {
        try {
            Brand brand = brandService.getBrandById(brandId);
            maps.put("brand", brand);
        } catch (NullParametersException e) {
            e.printStackTrace();
            maps.put("message", "ERROR");
        }
        return "system/page/table/editBrand";


    }

    /**
     * 编辑品牌
     *
     * @param brand
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(@RequestBody Brand brand) {
        try {
            brandService.updateBrand(brand);
            return "SUCCESS";
        } catch (NullParametersException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @RequestMapping("/del")
    @ResponseBody
    public String del(@RequestBody Integer brandId) {
        try {
            brandService.deleteBrand(brandId);
            return "SUCCESS";
        } catch (NullParametersException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
