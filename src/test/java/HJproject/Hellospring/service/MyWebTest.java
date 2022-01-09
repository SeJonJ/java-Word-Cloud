package HJproject.Hellospring.service;

import HJproject.Hellospring.domain.Member;
import HJproject.Hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MyWebTest {

    @Autowired memberService memberService ;
    @Autowired MemberRepository memberRepository;


    @Test
    void member_Test(){
        Member member = new Member();

        member.setName("test2");
        member.setName("test2");
        member.setId("test2");
        member.setPasswd("test2");

        Long saveCode = memberService.join(member);


        Member findCode = memberService.findOne(member.getCode()).get();

        System.out.println(findCode.getCode()+ " " +findCode.getName() + " "+ findCode.getId()+ " "+findCode.getPasswd());

    }
}
