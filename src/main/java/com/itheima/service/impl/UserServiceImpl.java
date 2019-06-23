package com.itheima.service.impl;

import com.itheima.dao.TbUserMapper;
import com.itheima.domain.TbUser;
import com.itheima.domain.TbUserExample;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public List<TbUser> Login(TbUser user) {
        TbUserExample example =new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (user.getUsername().length()>0&&user.getPassword().length()>0){
            criteria.andUsernameEqualTo(user.getUsername());
            criteria.andPasswordEqualTo(user.getPassword());
        }
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
       return tbUsers;
    }
}
