package HJproject.Hellospring.repository;

import HJproject.Hellospring.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findBycode(Long MEMBERCODE);
    Optional<Member> findByName(String MNAME);
    Optional<Member> findById(String MID);
    Optional<Member> findByPasswd(String MPASSWD);
    Optional<Member> findBySex(String MGENDER);
    Optional<Member> findByEmail(String MEMAIL);
    Optional<Member> findByEmaddress(String MEMADDRESS);
    Optional<Member> findByRData(String RData);

    List<Member> findAll(); // 모든 회원 리스트 반환


}
