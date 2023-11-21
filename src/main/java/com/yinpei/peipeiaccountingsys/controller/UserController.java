package com.yinpei.peipeiaccountingsys.controller;

import com.yinpei.peipeiaccountingsys.common.Result;
import com.yinpei.peipeiaccountingsys.entity.User;
import com.yinpei.peipeiaccountingsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody User user){
        try {
            userService.insertUser(user);
        }catch (Exception exception){
            if (exception instanceof SQLException){
                return Result.error("获取数据有误");
            }else{
                return Result.error();
            }

        }

        return Result.success();
    }

    @GetMapping("/selectAll")
    public Result selectAll(){
        return Result.success(userService.getAll());
    }

    @GetMapping("/nullUserId/{id}")
    public Result selectUserByNullId(@PathVariable Integer id){
        return Result.success(userService.getUserByNullId(id));
    }

    @PutMapping("/update")
    public Result updateUser(@RequestBody User user){
        userService.Update(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteNotice(@PathVariable Integer id) {
        userService.removeById(id);
        return Result.success();
    }

}
