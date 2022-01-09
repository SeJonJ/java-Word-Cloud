package HJproject.Hellospring.repository;

import HJproject.Hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findByCode(Long code);
    Optional<Member> findByName(String name);
    Optional<Member> findById(String ID);
    Optional<Member> findByPasswd(String passwd);
    Optional<Member> findBySex(String sex);
    Optional<Member> findByEmail(String email);

    List<Member> findAll(); // 모든 회원 리스트 반환


}
