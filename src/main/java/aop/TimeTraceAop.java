package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
//@Component    //: SpringConfig에서 직접 빈으로 등록해줘서 사용중
public class TimeTraceAop {

//    @Around("execution(* hello.hellospring..*(..))") // 패기키 하위에 전부 적용
    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    // SpringConfig에 빈으로 등록을 할 경우 : @Around의 범위가 hellospring 패키지 전체이므로
    // 이 TimeTraceAop를 구현하는 timeTraceAop메서드도 AOP처리를 하게된다.
    // 그렇게 되면 자기자신을 참조하는것이 되므로 순환참조 문제 발생
    // 따라서 그냥 @Component를 쓰든지
    //아님 @Around에 SpringConfig.java를 제외하도록 하면된다.
    public Object execute(ProceedingJoinPoint joinPoint) throws  Throwable {

        long start =  System.currentTimeMillis();
        System.out.println("START : "+joinPoint.toString());
        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : "+joinPoint.toString()+" "+timeMs+"ms");
        }
    }

}
