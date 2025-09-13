package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){};

    /**
     * 前置通知，在通知中进行公共字段的赋值
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) throws NoSuchFieldException, IllegalAccessException {
        /////////////////////重要////////////////////////////////////
        //可先进行调试，是否能进入该方法 提前在mapper方法添加AutoFill注解
        log.info("开始进行公共字段自动填充...");


        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Object entity = joinPoint.getArgs()[0];  // 从 AOP 切面获取目标对象
        Class<?> clazz = entity.getClass();      // 获取对象的 Class 对象
        Field updateUser = clazz.getDeclaredField("updateUser");
        Field updateTime = clazz.getDeclaredField("updateTime");
        updateUser.setAccessible(true);
        updateTime.setAccessible(true);
        updateUser.set(entity, BaseContext.getCurrentId());
        updateTime.set(entity, LocalDateTime.now());
        if (signature.getMethod().getAnnotation(AutoFill.class).operationType().equals(OperationType.INSERT)) {
            Field insertUser = clazz.getDeclaredField("createUser");
            Field insertTime = clazz.getDeclaredField("createTime");
            insertUser.setAccessible(true);
            insertTime.setAccessible(true);
            insertUser.set(entity, BaseContext.getCurrentId());
            insertTime.set(entity, LocalDateTime.now());
        }
    }
}
