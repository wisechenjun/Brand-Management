package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.TbBrand;
import com.itheima.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @RequestMapping("/findAll")
    public String findAll(Model model) {

        List<TbBrand> brandList = brandService.findAll();
        model.addAttribute("brandList", brandList);
        return "brand";
    }

    @RequestMapping("/deleteById/{id}")
    public String deleteById(@PathVariable Long id,TbBrand tbBrand, HttpServletRequest request) {
        brandService.deleteById(id);
//        List<TbBrand> brandList = brandService.findAll();
//        model.addAttribute("brandList",brandList);
        findByPage(null,tbBrand, request);
        return "brand";
    }

    @RequestMapping("/findById/{id}")
    public String findById(@PathVariable Long id,TbBrand tbBrand, HttpServletRequest request) {
        TbBrand tbBrand1 = brandService.findById(id);
        request.setAttribute("brand", tbBrand1);

        findByPage(null,tbBrand, request);

        return "brand";
    }

    @RequestMapping("/save")
    public String save(TbBrand tbBrand, HttpServletRequest request) {
        tbBrand.setFirstChar(tbBrand.getFirstChar().toUpperCase());
        brandService.save(tbBrand);
        findByPage(null,null, request);
        return "brand";
    }

    @RequestMapping("/update")
    public String update(TbBrand tbBrand, String name1,String firstChar1,HttpServletRequest request) {
        brandService.update(tbBrand);
        tbBrand.setName(name1);
        tbBrand.setFirstChar(firstChar1);
        findByPage(null,tbBrand, request);
        return "brand";
    }

    @RequestMapping("/findByCond")
    public String findByCond(TbBrand tbBrand, Model model) {
        List<TbBrand> brandList = brandService.findByCond(tbBrand);
        model.addAttribute("brandList", brandList);
        return "brand";
    }

    @RequestMapping({"/findByPage/{pageNum}","/findByPage"})
    public String findByPage(@PathVariable(required = false) Integer pageNum, TbBrand tbBrand,HttpServletRequest request) {
        if (pageNum == null) {
            if (request.getSession().getAttribute("pageNum") == null) {
                pageNum = 1;
            } else {
                pageNum = (Integer) request.getSession().getAttribute("pageNum");
            }

        }


        //存多条件回显，并判断如果两者为null则设置brand为null
        if (tbBrand!=null){
            if (tbBrand.getName()!=null&&tbBrand.getName().length()>0){
                request.setAttribute("name",tbBrand.getName());
            }
            if (tbBrand.getFirstChar()!=null&&tbBrand.getFirstChar().length()>0){
                request.setAttribute("firstChar",tbBrand.getFirstChar());
            }
            if (tbBrand.getName()==null&&tbBrand.getFirstChar()==null){
                tbBrand=null;
            }
        }

        //开始分页多条件查询
        PageInfo info = brandService.findByPage(pageNum,tbBrand);
        request.setAttribute("info", info);
        request.getSession().setAttribute("pageNum", info.getPageNum());

        //对当前页进行判断，如果当前页为1则前一页为1，如果当前页为最后一页则下一页为最后一页
        int before = pageNum - 1;
        int next = pageNum + 1;
        if (pageNum == 1) {
            before = 1;
        }
        if (pageNum == info.getPages()) {
            next = pageNum;
        }

        //对分页栏进行10条限定，当前页前5后4

        int begin = 0; //定义遍历起始页
        int end = 0; //定义遍历最后页

        if (info.getPages() <= 10) {
            begin = 1;
            end = info.getPages();
        }

        if (info.getPages() > 10) {
            //普通情况下
            begin = pageNum - 5;
            end = pageNum + 4;

            //前五页
            if (pageNum - 5 <= 0) {
                begin = 1;
                end = 10;
            }
            //后四页
            if (pageNum + 4 >= info.getPages()) {
                end = info.getPages();
                begin = end - 9;
            }

        }

        request.setAttribute("before", before);
        request.setAttribute("next", next);
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);




        return "brand";
    }
}
