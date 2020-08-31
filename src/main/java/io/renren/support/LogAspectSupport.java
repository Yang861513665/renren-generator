package io.renren.support;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;
import io.renren.annotation.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxj
 * @date 2020-08-31 09:13
 *
 * 请求日志记录切面
 */
@Aspect
@Component
@Priority(-1)
@Slf4j
public class LogAspectSupport {
    @Autowired
    HttpServletRequest request;

    @Around("@annotation(logger)")
    public Object Log(ProceedingJoinPoint joinPoint, Log logger) throws Throwable {

        Stopwatch stopwatch = Stopwatch.createStarted();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String methodName = methodSignature.getDeclaringTypeName() + "." + methodSignature.getName(); // 请求完整方法名

        StringBuffer requestURL = request.getRequestURL();

        Object[] requestParams = joinPoint.getArgs(); // 请求参数

        log.info("URL: {}, 请求方法: {}, 请求参数: {}", requestURL, methodName, JSON.toJSONString(requestParams));

        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            log.error("URL: {}, 请求方法: {}, 请求参数: {}", e);
            // 异常处理
            throw e;
        } finally {
            log.info("URL: {}, 请求方法: {}, 耗时: {}s", requestURL, methodName, stopwatch.elapsed(TimeUnit.SECONDS));
        }
    }
}
