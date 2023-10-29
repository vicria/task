package com.example.aspects;

import com.example.entity.Root;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Aspect
@Component
public class OwnAspect {



    private AtomicBoolean isPrivateMethodCalled = new AtomicBoolean(false);

    @Pointcut("execution(* com.example.repository.RootRepository.getById(..))")
    public void entityMethod5() {
    }

    @Pointcut("@annotation(com.example.aspects.Loggable)")
    public void privateMethod5() {
    }

//    @Around("privateMethod5()")
//    public void beforeDaoMethods7(fffff) {
//        log.error("Before DAO methods   !!!!!!");
//    }

    @Before("entityMethod5()")
    public void beforeDaoMethods5() {
        log.error("Before DAO methods  ??????? ");
        checkAndExecuteCommonLogic();
        isPrivateMethodCalled.set(false);
    }


    private void checkAndExecuteCommonLogic() {
        if (isPrivateMethodCalled.get()) {
            // Общая логика, которую нужно выполнить, когда оба аспекта сработали
            log.info("Before Ambos");
        }
    }

//    |||||||||||||||||||||||||||||||||||||||||||||||||||||

    @Pointcut("execution(* com.example.service.*.*(..)) ")
    public void entityMethod6() {
    }

    @Pointcut("@annotation(com.example.aspects.Loggable)")
    public void privateMethod6() {
    }


    @AfterReturning("entityMethod6() && privateMethod6()")
    public void beforeDaoMethods6() {
        log.info("Before DAO methods All Services");
    }

    //|||||||||||||||||||||||||||||||||||||||||||||||||||||

    @Pointcut("execution(* com.example.service.RootService.get(..)) ")
    public void entityMethod() {
    }

    @Pointcut("@annotation(com.example.aspects.Loggable)")
    public void privateMethod() {
    }


    @AfterReturning("entityMethod() && privateMethod()")
    public void beforeDaoMethods() {
        log.info("Before DAO methods AfterReturning");
    }

    //|||||||||||||||||||||||||||||||||||||||||||||||||||||

    @Pointcut("@annotation(com.example.aspects.Loggable) && target(com.example.service.RootService)")
    public void privateMethod2() {
    }

    @Before("privateMethod2()")
    public void beforeDaoMethods4() {
        log.info("Before DAO methods with Service");
    }

















    @Around("execution(* com.example.repository.RootRepository.getById(..))")
    public Object aroundDaoMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        Root result = (Root)joinPoint.proceed();
        //записываем вместо true и false чтобы сравнивать по id например
        String id = result.getId();
        return result;
    }

}
