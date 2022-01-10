package HJproject.Hellospring.service;

import HJproject.Hellospring.domain.Member;
import HJproject.Hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class memberService {

    private final MemberRepository memberRepository;

    public memberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


//    회원 가입
    public Long join(Member member){

            // 회원 가입 시 동일한 ID 로는 가입 불가
            checkDuplicateID(member); // 중복 회원 검증

            memberRepository.save(member); // memberRepository 에 member 저장
            return member.getCode(); // 저장 후 저장된 회원의 Code 번호 반환
    }

    private void checkDuplicateID(Member member) { // 메서드 추출 단축키 컨트롤 + 쉬프트 + M

            memberRepository.findById(member.getId())
                    .ifPresent(m -> {
                        throw new IllegalStateException("이미 존재하는 ID 입니다");
                    });
    }

    /* 전체 회원 조회 */
    public List<Member> findMembers(){
            return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberCode){
        return memberRepository.findByCode(memberCode);
    }
}
