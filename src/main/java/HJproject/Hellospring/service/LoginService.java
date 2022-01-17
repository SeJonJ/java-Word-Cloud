package HJproject.Hellospring.service;

import HJproject.Hellospring.domain.member.Member;
import HJproject.Hellospring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
// 이 어노테이션이 붙어있으면 초기화되지 않은 final 필드나 @nonNull 이 붙은 필드에 대해서
// 생성자를 생성해줌 => 즉 알아서 DI를 해줌
public class LoginService {

    private final MemberRepository memberRepository;


    public Member login(String loginid, String loginpw) {
        // login Controller 을 통해서 넘어오는 loginid loginpw

        System.out.println("내가 넣은 패스워드 :" + loginpw);
        System.out.println("내가 넣은 아이디 :" + loginid);

        // 여기서 findbyid 메서드를 사용하여 Optional 로 감싸진 member 객체에 해당 아이디 기준의 DB 정보를 저장함 => code, passwd , sex , email 등등
        Optional<Member> findMemberOptional = memberRepository.findById(loginid);
        Member member = findMemberOptional.get(); // Optional 로 감싸졌을때는 get 으로 꺼내올 수 있음


        if (member.getPasswd().equals(loginpw)) { // 꺼내온 passwd 가 내가 입력한 패스워드 loginpw 와 같다면
//            System.out.println("내가 가져온 패스워드 :" + member.getPasswd());
//            System.out.println("내가 넣은 패스워드 :" + userpw);
//            System.out.println(member.getCode());
//            System.out.println(member.getRData());

            return member; // member 객체 리턴


        }else{

            return null; // 아니면 null 리턴

        }
    }

}
