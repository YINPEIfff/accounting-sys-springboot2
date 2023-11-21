package com.yinpei.peipeiaccountingsys.controller;

import com.yinpei.peipeiaccountingsys.common.Result;
import com.yinpei.peipeiaccountingsys.entity.Company;
import com.yinpei.peipeiaccountingsys.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/getAllCompanies")
    public Result getAllCompanies(){
        return Result.success(companyService.getAllCompanies());
    }

    @GetMapping("/getCompanyById/{id}")
    public Result getAllCompaniesById(@PathVariable int id){
        Company company = companyService.getCompanyById(id);
        return Result.success(company);
    }

    @DeleteMapping("/delete/{id}")
    public Result removeCompanyById(@PathVariable int id){
        companyService.removeCompanyById(id);
        return Result.success();
    }

}
