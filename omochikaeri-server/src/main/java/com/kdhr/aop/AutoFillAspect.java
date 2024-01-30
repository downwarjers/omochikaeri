package com.kdhr.aop;

import com.kdhr.annotation.AutoFill;
import com.kdhr.constant.AutoFillConstant;
import com.kdhr.context.BaseContext;
import com.kdhr.entity.Category;
import com.kdhr.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;

@Component
@Aspect
@Slf4j
public class AutoFillAspect {
    @Pointcut("execution(* com.kdhr.mapper.*.*(..)) && @annotation(com.kdhr.annotation.AutoFill)")
    public void autoFillPointCut() {
    }

    @Before("autoFillPointCut()")
    public void autoFillDatetime(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //判斷否有參數
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0 || args[0] == null) {
            return;
        }

        //取得方法的標籤
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);

        //取得物件
        Object entity = args[0];
        LocalDateTime now = LocalDateTime.now();
        Long empId = BaseContext.getCurrentId();

        //假如為新增
        if (OperationType.INSERT.equals(annotation.value())) {
            Method setCreateTime = entity.getClass().getMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
            Method setCreateUser = entity.getClass().getMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
            setCreateTime.invoke(entity, now);
            setCreateUser.invoke(entity, empId);
        }
        //假如為新增或修改
        if (OperationType.INSERT.equals(annotation.value()) || OperationType.UPDATE.equals(annotation.value())) {
            Method setUpdateTime = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME,LocalDateTime.class);
            Method setUpdateUser = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
            setUpdateTime.invoke(entity, now);
            setUpdateUser.invoke(entity, empId);
        }
    }
}
