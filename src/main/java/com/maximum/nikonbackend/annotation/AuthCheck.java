package com.maximum.nikonbackend.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {
    /**
     * any role
     * @return
     */
    String[] anyRole() default "";

    /**
     * must be a role
     * @return
     */
    String mustRole() default "";
}
