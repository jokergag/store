package com.cy.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author joker
 * @create 2022-05-04
 */
//@Component//将当前类的对象创建使用维护交由spring容器管理
//@Aspect//将当前类标记为切面类
public class TimeAspect {

    @Around("execution(* com.cy.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 记录起始时间
        long start = System.currentTimeMillis();
        // 执行连接点方法(目标方法)，即切面所在位置对应的方法。本项目中表示执行注册或执行登录等
        Object result = pjp.proceed();
        // 记录结束时间
        long end = System.currentTimeMillis();
        // 计算耗时
        System.err.println("耗时：" + (end - start) + "ms");
        // 返回连接点方法的返回值
        return result;
    }

}
