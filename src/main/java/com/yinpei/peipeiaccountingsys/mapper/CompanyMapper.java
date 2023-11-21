package com.yinpei.peipeiaccountingsys.mapper;

import com.yinpei.peipeiaccountingsys.entity.Company;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyMapper {

    @Select("select * from Company")
    @Results({
            @Result(
                    property = "companyResponsibleOfficer",
                    column = "company_responsible_officer_id",
                    javaType = String.class,
                    one = @One(select = "com.example.demo.mapper.UserMapper.selectUserNameById")
            )
    })
    List<Company> selectAllCompanies();

    @Select("select * from Company where id=#{id}")
    Company selectCompanyById(@Param("id") int id);

    @Delete("delete from Company where id=#{id}}")
    void deleteCompanyById(int id);
}
