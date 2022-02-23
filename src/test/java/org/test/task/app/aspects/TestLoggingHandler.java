package org.test.task.app.aspects;

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
public class TestLoggingHandler {

    private final static String METHOD = "Entering in Method : ";
    private final static String CLASS = ", Class Name :  ";
    private final static String ARGUMENT = ", Arguments :  ";
    private final static String TARGET = ", Target class : ";

    private final static String RETURN_VALUE = "Method Return value : ";

    private final static String ILLEGAL_EXCEPTION = "An exception is IllegalArgumentException : ";
    private final static String NP_EXCEPTION = "An exception is NullPointerException : ";

    private final static String EXCEPTION = "An exception has been thrown in : ";
    private final static String EXCEPTION_METHOD_NAME = ", Exception method : ";
    private final static String EXCEPTION_MESSAGE = ", Message : ";
    private final static String EXCEPTION_CAUSE = ", Cause : ";

    private final static String EXECUTION_TIME = " execution time : ";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(@org.springframework.boot.test.context.TestComponent *)")
    public void testComponents() {
    }


    @AfterReturning(pointcut = "testComponents()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {

        log.info(METHOD + joinPoint.getSignature().getName() +
                CLASS + joinPoint.getSignature().getDeclaringTypeName() +
                ARGUMENT + Arrays.toString(joinPoint.getArgs()) +
                TARGET + joinPoint.getTarget().getClass().getName()
        );

        if (result != null) {
            log.debug(RETURN_VALUE + result.getClass().getTypeName());
        }
    }

    @AfterThrowing(pointcut = "testComponents()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {

        log.error(EXCEPTION + joinPoint.getSignature().getDeclaringTypeName() +
                EXCEPTION_METHOD_NAME + joinPoint.getSignature().getName() +
                EXCEPTION_CAUSE + exception.getCause() +
                EXCEPTION_MESSAGE + exception.getMessage()
        );

        log.debug(Arrays.toString(exception.getStackTrace()));
    }

    @Around("testComponents()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        try {

            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            log.info(METHOD + joinPoint.getSignature().getName() +
                    CLASS + joinPoint.getSignature().getDeclaringTypeName() +
                    EXECUTION_TIME + elapsedTime + " ms"
            );

            return result;

        } catch (IllegalArgumentException e) {

            log.error(ILLEGAL_EXCEPTION + Arrays.toString(joinPoint.getArgs()) +
                    CLASS + joinPoint.getSignature().getDeclaringTypeName() +
                    EXCEPTION_METHOD_NAME + joinPoint.getSignature().getName()
            );

            throw e;

        } catch (NullPointerException e) {

            log.error(NP_EXCEPTION +
                    CLASS + joinPoint.getSignature().getDeclaringTypeName() +
                    EXCEPTION_METHOD_NAME + joinPoint.getSignature().getName()
            );

            throw e;
        }
    }
}