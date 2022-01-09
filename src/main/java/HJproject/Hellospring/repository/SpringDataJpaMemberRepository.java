package HJproject.Hellospring.repository;

import HJproject.Hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    // JpaRepository 인터페이스를 상속받아옴 => 이때 첫번째 파라미터는 Entity 명 , 두번째 파라미터는 PrimaryKey 타입
    // 추가로 MemberRepository 로 상속

    // JpaRepository 를 상속받은 인터페이스가 있다면 JpaRepository 가 자동으로 구현체를 생성해서 springBean 에 등로함


    // SpringDataJpa 가 메서드 이름을 통해 JPQL 을 자동으로 만들고 DB에 질의해준다.
    // 예를 들어 findById 의 경우 findBy 뒤에 나오는 이름(Id)을 검색 하고자하는 기준으로 잡고, 쿼리가 생성되고 질의된다.

    // 아래 내용을 JPQL 로 만들면 : select m from Member m where m.id = ?
    @Override
    Optional<Member> findById(String ID);

    // JPQL : select m.code , m.id from Member m = ?
    Optional<Member> findByCodeAndId(Long code, String ID);
}
