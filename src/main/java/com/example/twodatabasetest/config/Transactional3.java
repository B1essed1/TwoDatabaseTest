//package com.example.twodatabasetest.config;
//
//
//import org.springframework.core.annotation.AliasFor;
//import org.springframework.transaction.annotation.Propagation;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@Target({ElementType.METHOD, ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@TransactionalCustom
//@Transactional2
//public @interface Transactional3 {
//
//    Propagation propagation() default Propagation.REQUIRED;
//
//
//    @AliasFor(
//            annotation = TransactionalCustom.class,
//            value = "propagation"
//    )
//    Propagation propagation1();
//
//
//
//
//
//}
