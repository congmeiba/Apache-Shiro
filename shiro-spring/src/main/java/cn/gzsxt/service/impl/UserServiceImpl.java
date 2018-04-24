package cn.gzsxt.service.impl;

import cn.gzsxt.entity.User;
import cn.gzsxt.mapper.UserMapper;
import cn.gzsxt.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public User findByUser(User user) {
        return userMapper.findByUser(user);
    }
}
