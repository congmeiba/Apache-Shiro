package cn.gzsxt.shirospringboot.mapper;

import cn.gzsxt.shirospringboot.entity.User;

import java.util.List;

public interface UserMapper {

    public User findByUser(String username);

    public List<String> findByPermission(Integer id);

    public List<String> findByPermissionAll();

}
