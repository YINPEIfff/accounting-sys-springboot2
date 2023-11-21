package com.yinpei.peipeiaccountingsys.service;

import cn.hutool.core.util.RandomUtil;
import com.yinpei.peipeiaccountingsys.entity.User;
import com.yinpei.peipeiaccountingsys.exception.ServiceException;
import com.yinpei.peipeiaccountingsys.mapper.UserMapper;
import com.yinpei.peipeiaccountingsys.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getById(Integer id){
        return userMapper.selectUserById(id);
    }

    public User getUserByNameAndPassword(String name, String password) {
        return userMapper.selectUserByNameAndPassword(name, password);
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public List<User> getAll() {
        return userMapper.selectAll();
    }

    public User login(User user) {
        User dbUser = userMapper.selectUserByUsername(user.getUsername());
        if (dbUser == null) {
            throw new ServiceException("用户名或密码错误");
        }
        if (!user.getPassword().equals(dbUser.getPassword())) {
            throw new ServiceException("用户名密码错误");
        }
        String token = TokenUtils.createToken(dbUser.getId().toString(),dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }


    public User register(User user) {
        User dbUser = userMapper.selectUserByUsername(user.getUsername());
        System.out.println("dbUser = " + dbUser);
        if (dbUser != null)
            throw new ServiceException("用户已经存在");
        user.setName(user.getUsername() + RandomUtil.randomNumbers(4));
        userMapper.insertUser(user);
        return user;
    }

    public void Update(User user){
        userMapper.update(user);
    }

    public void updateById(int id,User user){

    }

    public void removeById(Integer id) {
        userMapper.deleteUserById(id);
    }

    public List<String> getUserByNullId(int id){
        return userMapper.selectUserNameNotById(id);
    }
}
