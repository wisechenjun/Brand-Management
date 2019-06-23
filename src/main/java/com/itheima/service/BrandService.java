package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.TbBrand;

import java.util.List;

public interface BrandService {
    /**
     * 查找所有品牌
     * @return
     */
    public List<TbBrand> findAll();

    /**
     * 根据id删除一条品牌信息
     * @param id
     */
    public void deleteById(Long id);

    /**
     * 根据id查找品牌信息进行回显
     * @param id
     * @return
     */
    public TbBrand findById(Long id);

    /**
     * 保存用户
     */
    public void save(TbBrand tbBrand);

    /**
     * 更新账户
     * @param tbBrand
     */
    public void update(TbBrand tbBrand);

    /**
     * 根据多条件传入查询
     * @param tbBrand
     * @return
     */
    public List<TbBrand> findByCond(TbBrand tbBrand);

    /**
     * 分页查询,条件查询
     * @param pageNum
     * @return
     */
    public PageInfo findByPage(Integer pageNum,TbBrand tbBrand);
}
