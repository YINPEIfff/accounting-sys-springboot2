package com.yinpei.peipeiaccountingsys.mapper;

import com.yinpei.peipeiaccountingsys.entity.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    @Select("select * from department")
    @Results({
            @Result(property = "departmentId", column = "department_id"),
            @Result(property = "departmentName", column = "department_name"),
            @Result(property = "departmentManagerId", column = "department_manager_id"),
            @Result(
                    property = "departmentManager",
                    column = "department_manager_id",
                    javaType = String.class,
                    one = @One(select = "com.example.demo.mapper.UserMapper.selectUserNameById")
            ),
            @Result(
                    property = "departmentEmps",
                    column = "department_id",
                    javaType = List.class,
                    many = @Many(select = "com.example.demo.mapper.UserMapper.selectUserByDepartmentId")
            )
    })
    List<Department> selectAllDepartment();

}
