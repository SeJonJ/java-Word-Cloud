package HJproject.Hellospring.service;

import HJproject.Hellospring.domain.member.Member;
import HJproject.Hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MyWebTest {

    @Autowired memberService memberService ;
    @Autowired MemberRepository memberRepository;


    void member_Test(){
        Member member = new Member();

        member.setMNAME("test2");
        member.setMNAME("test2");
        member.setMID("test2");
        member.setMPASSWD("test2");

        Long saveCode = memberService.join(member);


        Member findCode = memberService.findOne(member.getMEMBERCODE()).get();

        System.out.println(findCode.getMEMBERCODE()+ " " +findCode.getMNAME() + " "+ findCode.getMID()+ " "+findCode.getMPASSWD());

    }
}
