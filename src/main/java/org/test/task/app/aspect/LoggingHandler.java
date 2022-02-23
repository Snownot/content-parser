package org.test.task.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class LoggingHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* org.test.task.app.controller.RestContentParserController.*(..))")
    public void controller() {
    }

    @Pointcut("execution(* org.test.task.app.service.ContentParserService.*(..))")
    public void service() {
    }

    @AfterReturning(pointcut = "controller() || service()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {

        log.info("Entering in Method :  " + joinPoint.getSignature().getName() +
        ", Class Name :  " + joinPoint.getSignature().getDeclaringTypeName() +
        ", Arguments :  " + Arrays.toString(joinPoint.getArgs()) +
        ", Target class : " + joinPoint.getTarget().getClass().getName());

        log.debug("Method Return value : " + result);

    }

    @AfterThrowing(pointcut = "controller() && service()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {

        log.error("An exception has been thrown in " + joinPoint.getSignature().getName() + "()");
        log.error("Cause : " + exception.getCause());

    }

    @Around("controller() || service()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        try {

            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            log.info("Around method " + className + "." + methodName + " ()" + " execution time : " + elapsedTime + " ms");
            return result;

        } catch (IllegalArgumentException exception) {

            log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in " + joinPoint.getSignature().getName() + "()");
            throw exception;

        }
    }
}
