package cn.gzsxt.shirospringboot.service;

import cn.gzsxt.shirospringboot.entity.User;

import java.util.List;

public interface UserService {

    public User findByUser(String username);

    public List<String> findByPermission(Integer id);

    public List<String> findByPermissionAll();


}
