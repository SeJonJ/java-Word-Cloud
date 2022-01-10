package HJproject.Hellospring;

import HJproject.Hellospring.Aop.TimeTraceAop;
import HJproject.Hellospring.repository.*;
import HJproject.Hellospring.service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration // 스프링 빈에 등록하기 위한 설정파일이라는 Annotation
public class SpringConfig {

    private DataSource dataSource;

    @PersistenceContext // EntityManager 을 사용하기 위한 어노테이션
    EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean // Bean 에 등록되어야하는 객체라는 의미의 Annotation
    public memberService memberService() {
        return new memberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpamemberRepository(em);
    }

//    // 아래처럼 Bean 으로 등록하거나 @Component 어노테이션 사용
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

}
