package com.example.twodatabasetest.config;


import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Transactional(value = "t2")
public @interface Transactional2 {

    @AliasFor(
            annotation = Transactional.class
    )
    String value() default "";

    @AliasFor(
            annotation = Transactional.class
    )
    Propagation propagation() default Propagation.REQUIRED;

    @AliasFor(
            annotation = Transactional.class
    )
    Class<? extends Throwable>[] rollbackFor() default {};

}
