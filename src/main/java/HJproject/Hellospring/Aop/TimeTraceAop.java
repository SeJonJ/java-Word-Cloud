package HJproject.Hellospring.Aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // AOP 사용하기 위한 어노테이션
@Component
// Configration 에 Bean 으로 등록하거나 @Component 어노테이션 사용
// 현재는 @Component 사용
public class TimeTraceAop {

    //@Around("execution(* HJproject.Hellospring..*(..))") // AOP를 적용할 클래스들을 정하는(타게팅하는) 어노테이션
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{

        long start = System.currentTimeMillis();

        System.out.println("START : "+ joinPoint.toString());

        try{

            return joinPoint.proceed();

        } finally {

            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString() + " " +timeMs + "ms");

        }

    }

}

