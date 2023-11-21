package com.yinpei.peipeiaccountingsys.service;

import com.yinpei.peipeiaccountingsys.entity.Department;
import com.yinpei.peipeiaccountingsys.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDept(){
        return departmentMapper.selectAllDepartment();
    }
}
