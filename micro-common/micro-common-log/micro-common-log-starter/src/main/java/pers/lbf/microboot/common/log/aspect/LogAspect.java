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
