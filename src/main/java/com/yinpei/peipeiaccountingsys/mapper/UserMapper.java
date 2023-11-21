package com.yinpei.peipeiaccountingsys.mapper;


import com.yinpei.peipeiaccountingsys.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from User where name=#{name} and password=#{password}")
    User selectUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Select("select * from User")
    List<User> selectAll();

    @Select("select * from USER where username=#{username} order by `id` desc")
    User selectUserByUsername(@Param("username") String username);

    @Select("select * from USER where id=#{id}")
    User selectUserById(@Param("id") int id);

    @Select("select * from USER where department_id=#{departmentId}")
    List<User> selectUserByDepartmentId(@Param("departmentId") int departmentId);

    @Select("select name from USER where id=#{id}")
    List<String> selectUserNameById(@Param("id") int id);

    @Select("SELECT name from USER  where department_id<>#{id}")
    List<String> selectUserNameNotById(@Param("id") int id);

    @Update("update user set " +
            "username = #{username}," +
            "password = #{password}," +
            "name = #{name}," +
            "phone= #{phone}," +
            "email= #{email}," +
            "address= #{address}," +
            "avatar= #{avatar}," +
            "birthday= #{birthday}, " +
            "token= #{token}, " +
            "role= #{role} " +
            "where id = #{id}")
    void update(User user);

    @Insert("insert into User (username,password,name,phone,email,address,avatar,birthday,token,role) " +
            "values (#{username},#{password},#{name},#{phone},#{email},#{address},#{avatar},#{birthday},#{token},${role}});")
    void insertUser(User user);


    @Delete("delete from User where id=#{id}")
    void deleteUserById(@Param("id") Integer id);

}
