package com.yinpei.peipeiaccountingsys.common;

import com.sun.org.apache.bcel.internal.classfile.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {

    public final static String CODE_SUCCESS = "200";
    public final static String CODE_CLIENT_ERROR = "404";
    public final static String CODE_SERVER_ERROR = "500";

    private String code;
    private String msg;
    private Object data;

    public static Result success() {
        return new Result(CODE_SUCCESS, "request successful", null);
    }

    public static Result success(Object data) {
        return new Result(CODE_SUCCESS, "request successful", data);
    }

    public static Result error() {
        return new Result(CODE_CLIENT_ERROR, "bad request", null);
    }

    public static Result error(String msg) {
        return new Result(CODE_SERVER_ERROR, msg, null);
    }

    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }

}
