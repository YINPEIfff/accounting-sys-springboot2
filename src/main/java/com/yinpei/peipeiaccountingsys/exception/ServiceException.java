package com.yinpei.peipeiaccountingsys.exception;

public class ServiceException extends RuntimeException {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ServiceException(String msg) {
        super(msg);
        this.code = "500";
    }

    public ServiceException(String code,String msg){
        super(msg);
        this.code = code;
    }
}
