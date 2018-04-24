package cn.gzsxt.shirospringboot.service.impl;

import cn.gzsxt.shirospringboot.entity.User;
import cn.gzsxt.shirospringboot.mapper.UserMapper;
import cn.gzsxt.shirospringboot.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    UserMapper userMapper;

    @Override
    public User findByUser(String username) {
        return userMapper.findByUser(username);
    }

    @Override
    public List<String> findByPermission(Integer id) {
        return userMapper.findByPermission(id);
    }

    @Override
    public List<String> findByPermissionAll() {
        return userMapper.findByPermissionAll();
    }
}
