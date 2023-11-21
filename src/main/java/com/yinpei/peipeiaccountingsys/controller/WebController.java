package com.yinpei.peipeiaccountingsys.controller;

import cn.hutool.core.util.StrUtil;
import com.yinpei.peipeiaccountingsys.common.AuthAccess;
import com.yinpei.peipeiaccountingsys.common.Result;
import com.yinpei.peipeiaccountingsys.entity.User;
import com.yinpei.peipeiaccountingsys.mapper.DepartmentMapper;
import com.yinpei.peipeiaccountingsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    UserService userService;

    @Autowired
    DepartmentMapper departmentMapper;

    @AuthAccess
    @GetMapping("/")
    public Result demo() {
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword()))
            return Result.error("用户名或密码不能为空!");

        try {
            user = userService.login(user);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error("500", e.getMessage());
        }
    }

    @AuthAccess
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword()))
            return Result.error("用户名或密码不能为空!");
        if (user.getUsername().length() > 10 || user.getPassword().length() > 20)
            return Result.error("用户名或密码过长");
        try {
            user = userService.register(user);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error("500", e.getMessage());
        }
    }

}
