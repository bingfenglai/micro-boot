/*
 *    Copyright (c) 2018-2025, shenghua All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: shenghua
 */

package pers.lbf.microboot.common.log.aspect;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import pers.lbf.microboot.common.log.anotation.Log;

/**
 * 日志（操作日志、登录日志）切面
 *
 * @author 赖柄沣
 * @since 2021/10/7 13:09
 */

@Aspect
@RequiredArgsConstructor
@Slf4j
public class LogAspect {

    @Around("@annotation(log)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint point, Log log) {

        //1. 获取访问的类、方法
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        
        Object object;
        try {
            object = point.proceed();
        } catch (Exception e) {
            throw e;
        }

        return object;
    }


}
