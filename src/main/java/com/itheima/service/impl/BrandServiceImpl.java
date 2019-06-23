package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.TbBrandMapper;
import com.itheima.domain.TbBrand;
import com.itheima.domain.TbBrandExample;
import com.itheima.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {
        List<TbBrand> brandList = brandMapper.selectByExample(null);
        return brandList;
    }

    @Override
    public void deleteById(Long id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TbBrand findById(Long id) {
        TbBrand tbBrand = brandMapper.selectByPrimaryKey(id);
        return tbBrand;
    }

    @Override
    public void save(TbBrand tbBrand) {
        brandMapper.insert(tbBrand);
    }

    @Override
    public void update(TbBrand tbBrand) {
        brandMapper.updateByPrimaryKey(tbBrand);
    }

    @Override
    public List<TbBrand> findByCond(TbBrand tbBrand) {
        TbBrandExample example =new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if (tbBrand.getName()!=null&&tbBrand.getName().length()>0){
            criteria.andNameLike("%"+tbBrand.getName()+"%");
        }
        if (tbBrand.getFirstChar()!=null&&tbBrand.getFirstChar().length()>0){
            criteria.andFirstCharLike("%"+tbBrand.getFirstChar()+"%");
        }
        List<TbBrand> brandList = brandMapper.selectByExample(example);
        return brandList;
    }

    @Override
    public PageInfo findByPage(Integer pageNum,TbBrand tbBrand) {

        Integer pageSize=5;
        PageHelper.startPage(pageNum,pageSize);
        List<TbBrand> tbrandList=new ArrayList<>();
        if (tbBrand==null){
            tbrandList = brandMapper.selectByExample(null);
        }else {
            TbBrandExample example = new TbBrandExample();
            TbBrandExample.Criteria criteria = example.createCriteria();
            if (tbBrand.getName()!=null&&tbBrand.getName().length()>0){
                criteria.andNameLike("%"+tbBrand.getName()+"%");
            }
            if (tbBrand.getFirstChar()!=null&&tbBrand.getFirstChar().length()>0){
                criteria.andFirstCharLike("%"+tbBrand.getFirstChar()+"%");
            }
            tbrandList = brandMapper.selectByExample(example);
        }

        PageInfo info=new PageInfo(tbrandList);


        //解决删除最后一条记录不自动换页问题
        if (info.getList().size()==0){
            if (info.getPageNum()==1){
                return info;
            }
            return this.findByPage(pageNum-1,tbBrand);
        }

        return info;
    }
}
