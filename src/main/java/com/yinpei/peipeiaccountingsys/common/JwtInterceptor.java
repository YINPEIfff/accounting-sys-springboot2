package com.yinpei.peipeiaccountingsys.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.entity.User;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token");
        }

        if (handler instanceof HandlerMethod){
            AuthAccess methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class);
            if (methodAnnotation != null)
                return true;
        }

        if (StrUtil.isBlank(token)) {
            throw new ServiceException("401", "请登录");
        }
        String userid;
        try {
            userid = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw new ServiceException("401", "请登录");
        }
        User userById = userMapper.selectUserById(Integer.valueOf(userid));
        if (userById == null) {
            throw new ServiceException("401","请登录");
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userById.getPassword())).build();
        try{
            jwtVerifier.verify(token);
        }catch (JWTVerificationException jwtVerificationException){
            throw  new ServiceException("401","请登录");
        }
        return true;
    }
}
