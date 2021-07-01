//package com.cuit.dataops.aop;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class ParamOutAspect {
//    //统计请求的处理时间
//    public static ThreadLocal<Long> startTime = new ThreadLocal<>();
//
//    /**
//     * 切入点
//     * 注解的参数就是对controller下的所有的类的所有方法进行切面
//     */
//    @Pointcut("execution(* com.cuit.dataops.controller.*.*(..))")
//    public void bigScreen() {
//    }
//
//    @Before("bigScreen()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        ParamOutAspect.startTime.set(System.currentTimeMillis());//设置请求开始的时间
//    }
//
//
//}
