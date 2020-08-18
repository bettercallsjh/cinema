package com.pmsj.cinema.system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmsj.cinema.common.entity.Brand;
import com.pmsj.cinema.common.mapper.BrandMapper;
import com.pmsj.cinema.system.exception.NullParametersException;
import com.pmsj.cinema.system.exception.PageInfoErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mhd
 * @className BrandService
 * @description TODO
 * @create 2020/7/1
 * @since 1.0.0
 */
@Service
public class BrandService {
    @Autowired(required = false)
    BrandMapper brandMapper;

    /**
     * 分页查询所有影院品牌
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Brand> getAllBrands(Integer pageNum, Integer pageSize, Brand searchParams){
        if (pageSize==null||pageSize<=0){
            throw new PageInfoErrorException("PageSize is illegal");
        }
        if(pageNum==null||pageNum<=0){
            pageNum=1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Brand> brands = brandMapper.selectAll(searchParams);
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        return pageInfo;
    }

    /**
     * 查询单挑数据
     * @return
     */
    public Brand getBrandById(Integer brandId){
        if(brandId==null){
                throw new NullParametersException("BrandId is null");
        }
        return brandMapper.selectByPrimaryKey(brandId);
    }

    /**
     * 添加品牌
     * @param brand
     * @return
     */
    public int addBrand(Brand brand){
        if (brand ==null||brand.getBrandName()==null){
            throw new NullParametersException("Brand is null");
        }else {
            return brandMapper.insert(brand);
        }
    }

    /**
     * 批量删除
     * @param list
     */
    public void deleteBatches(List<Brand> list){
        if (list==null){
            throw new NullParametersException("BrandList is null");
        }
        for (Brand brand:list){
            brandMapper.deleteByPrimaryKey(brand.getBrandId());
        }
    }

    /**
     * 更新数据
     * @param brand
     * @return
     */
    public Integer updateBrand(Brand brand){
        if (brand==null||brand.getBrandName()==null){
            throw new NullParametersException("Brand is null");
        }
        Brand oldBrand=brandMapper.selectByPrimaryKey(brand.getBrandId());

        if (!oldBrand.getBrandName().equals(brand.getBrandName())){
            oldBrand.setBrandName(brand.getBrandName());
        }
        return brandMapper.updateByPrimaryKey(oldBrand);
    }

    /**
     * 根据id删除数据
     * @param brandId
     * @return
     */
    public Integer deleteBrand(Integer brandId){
        if(brandId==null){
            throw new NullParametersException("BrandId is null");
        }
        return brandMapper.deleteByPrimaryKey(brandId);
    }


    public List<Brand> getAllBrandsJson() {
      return  brandMapper.selectAll(null);
    }

    public Brand getBrandByName(String brandName){
       return brandMapper.selectByName(brandName);
    }
}
