package com.yinpei.peipeiaccountingsys.controller;

import com.yinpei.peipeiaccountingsys.common.Result;
import com.yinpei.peipeiaccountingsys.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/all")
    public Result allDept(){
        return Result.success(departmentService.getAllDept());
    }
}
