package com.codeit.springwebbasic.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect // AOP 담당자(Aspect)
@Component
public class LoggingAspect {

    // 1. Pointcut (어디서?)
    /* execution([수식어] 리턴타입 [클래스경로.]메서드이름(파라미터 [예외])) - []안의 내용은 생략 가능한 문법
    @Pointcut("execution(* com.codeit.springwebbasic.book.controller.*.*(..))") // 접근 제어자 패키지경로 순서로 적는다

   모든 접근 제한자 허용, 모든 리턴타입 허용, MemberController 안에 있는 모든 메서드를 대상(매개값은 모든 파라미터)
   @Pointcut("execution(* com.codeit.springwebbasic.member.controller.MemberController.*(..))")

    // 모든 패키지 ..의 클래스, 메서드에 적용, ..:
    @Pointcut("execution(* com.codeit.springwebbasic..*.*(..")

   @Pointcut("execution(* com.codeit.springwebbasic.member.controller.MemberController.createMember(..))")
   springwebbasic하위 모든 package에서 controller 패키지의 모든 클래스, 모든 매서드 설정
      @Pointcut("execution(* com.codeit.springwebbasic.*.controller.*.*(..))")

    @Pointcut("execution(* com.codeit.springwebbasic.member.*(..))") // 접근 제어자 패키지경로 순서로 적는다

     */
    @Pointcut("execution(* com.codeit.springwebbasic.member.controller.MemberController.*(..))") // 접근 제어자 패키지경로 순서로 적는다
    private void allControllerMethods() {
        // 위에서 지정한 (어디에?) 라는 메서드 위치에 사전에 지정해야 할 여러 설정, 사전 작업 등을 명시합니다.
        // @Pointcut을 생략하고, @Around에 바로 execution을 작성해도 된다.
        System.out.println("allControllerMethods() 호출");

    }

    // 2. Advice (무엇을?): Pointcut에 지정된 곳 주변(Around)에서 이 코드를 실행.
//    @Around("allControllerMethods()")
//    public Object logControllerCheck(ProceedingJoinPoint joinPoint) throws Throwable {
//        // ProceedingJoinPoint: 이 AOP가 적용되는 지점(메서드)에 대한 정보를 담고 있는 객체
//
//        // 3. 공통 기능 (시작)
//        long start = System.currentTimeMillis();
//
//        // 4. 핵심 기능 실행 (원래의 메서드의 기능을 실행해라!)
//        Object result = joinPoint.proceed();
//
//        // 5. 공통 기능 (종료 및 로그)
//        long endTime = System.currentTimeMillis();
//        System.out.println("Controller Check Time: " + (endTime - start) + "ms");
//
//        return result; // 원래 메서드가 반환하는 값을 그대로 반환
//    }

    @Around("execution(* com.codeit.springwebbasic.*.controller.*Controller.*(..))")
    public Object logControllerCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        // ProceedingJoinPoint: 이 AOP가 적용되는 지점(메서드)에 대한 정보를 담고 있는 객체

        // 3. 공통 기능 (시작)
        long start = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName(); // 메서드 이름
        Object[] args = joinPoint.getArgs(); // 메서드에 전달된 매개값들
        Signature signature = joinPoint.getSignature();
        System.out.println("signature = " + signature);

//        signature.getDeclaringTypeName(); // 패키지 + 클래스이름
//        joinPoint.getTarget(); // 실제 대상 객체(Bean) 가져오기

        System.out.println("메서드 이름: " + methodName);
        System.out.println("매개값: " + Arrays.toString(args));

        // 4. 핵심 기능 실행 (원래의 메서드의 기능을 실행해라!)
        Object result = joinPoint.proceed();

        // 5. 공통 기능 (종료 및 로그)
        long endTime = System.currentTimeMillis();
        System.out.println("Controller Check Time: " + (endTime - start) + "ms");

        return result; // 원래 메서드가 반환하는 값을 그대로 반환
    }

    // @Before: 핵심 기능이 실행되기 직전까지만 딱 실행됨
    // proceed()를 따로 호출하지 않는다.

    // @AfterReturning: 메서드 정상 종료 이후 실행할 내용

    // 저위의 두개를 한꺼번에 아우를 수 있는 기능이 @Around
}
