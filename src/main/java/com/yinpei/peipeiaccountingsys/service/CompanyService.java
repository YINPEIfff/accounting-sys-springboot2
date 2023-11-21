package com.yinpei.peipeiaccountingsys.service;

import com.yinpei.peipeiaccountingsys.entity.Company;
import com.yinpei.peipeiaccountingsys.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyMapper companyMapper;

    public List<Company> getAllCompanies(){
        return companyMapper.selectAllCompanies();
    }

    public Company getCompanyById(int id){
        return companyMapper.selectCompanyById(id);
    }

    public void removeCompanyById(int id){
        companyMapper.deleteCompanyById(id);
    }
}
