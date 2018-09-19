package com.itheima.maven.dao;

import com.itheima.maven.User;

public interface UserMapper {

    public User selectUserById(Integer id);

    public void updateById(User user);

    public void insertUser(User user);
}

