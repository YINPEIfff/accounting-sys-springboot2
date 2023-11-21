package com.yinpei.peipeiaccountingsys.common;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAccess {
}
