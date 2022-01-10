package HJproject.Hellospring.service;

import HJproject.Hellospring.domain.Member;
import HJproject.Hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest // 실제 spring boot가 실행되며 동작하는 테스트
@Transactional // DB 트랜잭션 시 테스트 코드가 동작한 후 다시 Rollback 되도록 함
class memberServiceIntegrationTest {


    // 아래는 DI 의 또다른 방식인 Field 선언 방식
    // 필드에서 생성자 필요없이 Autowired 로 엮어서 DI 하는 방식
    @Autowired memberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void 회원가입() { // 테스트 코드에서는 한글로 메서드 잡아도 무방 -> 실제코드에 포함 X

        // given : 주어지는 데이터
        Member member = new Member();
        member.setName("통합테스트TEST");

        /*  내 맘대로 구현하기   */
        member.setId("통합테스트TEST");
        member.setPasswd("통합테스트TEST");
        member.setSex("man");
        member.setEmail("통합테스트TEST");
        member.setEmaddress("hjproject.iptime.org");

        LocalDate now = LocalDate.now();
        member.setRData(String.valueOf(now));

        // when : 검증하고자 하는 코드드
       Long saveCode = memberService.join(member);


        // then : 예상 결과(기댓값)
        Member findCode = memberService.findOne(member.getCode()).get();
        // findOne 메서드를 사용해 memberCode 가져오기

        assertThat(member.getId()).isEqualTo(findCode.getId());
        // 내가 작성한 값과 실제 저장된 값 비교 -> member.getId : findCode.getId

        System.out.println(findCode.getCode()+ " " +findCode.getName() + " "+ findCode.getId()+ " "+findCode.getPasswd() + " " + findCode.getSex());
        System.out.println(findCode.getEmail()+"@"+findCode.getEmaddress());
        System.out.println(findCode.getRData());

    }

    @Test
    public void 중복_아이디_체크(){

        //given
        Member member1 = new Member();
        member1.setName("spring");
        member1.setId("spring123");
        member1.setPasswd("4567");

        Member member2 = new Member();
        member2.setName("자바");
        member2.setId("spring123");
        member2.setPasswd("1234");



        /* 예외상황 확인 방법 2 */
        memberService.join(member1);

        // when : 예외가 발생하는 상황은 동일한 아이디로 회원가입을 요청하는 경우

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 ID 입니다");
    }




}