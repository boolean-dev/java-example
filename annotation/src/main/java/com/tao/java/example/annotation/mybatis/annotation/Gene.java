package com.tao.java.example.annotation.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Gene {
    String value();
}
