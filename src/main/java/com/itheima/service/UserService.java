package com.itheima.service;

import com.itheima.domain.TbUser;

import java.util.List;

public interface UserService {
    /**
     * 登录
     * @param user
     */
    public List<TbUser> Login(TbUser user);

}
