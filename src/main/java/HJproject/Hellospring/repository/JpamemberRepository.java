package HJproject.Hellospring.repository;

import HJproject.Hellospring.domain.Member;
import org.hibernate.annotations.common.reflection.XMember;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Transactional
public class JpamemberRepository implements MemberRepository{

    private final EntityManager em;
    // JPA는 EntityManager 로 동작
    // Spring boot 가 여러 세팅해두었던 정보와 DB 연결 정보들을 활용해서 EntityManager 생성

    public JpamemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        System.out.println("성별 저장 : " + member.getSex());
        System.out.println("email 확인 : "+ member.getEmail());
        System.out.println("email address 확인 : "+ member.getEmaddress());

        em.persist(member); // persist 는 영속화 즉, member 를 저장한다는 의미 => SQL 따위 필요없다
        return member;
    }

    @Override
    public Optional<Member> findByCode(Long code) {
        Member member = em.find(Member.class, code);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 각 컬럼명으로 DB 정보를 검색할때 사용가능한 것이 JPQL
        // 일반적인 SQL 과 비슷한데 딱 하나 다른점이라고 하면 일반 SQL이 중간에 * 로 테이블을 선택하고 컬럼명을 넣는다면
        // 이때 쿼리에서의 MM은 일종의 별칭으로 어떻게 설정해줘도 상관은 없지만 꼭 필요하다.
        // 또한 파라미터를 사용법중 이름 기준 파라미터를 사용하는 경우 JPQL에서 앞에 : 를 붙여준다
        List<Member> result = em.createQuery("select MM from Member MM where MM.name=:name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }


    /* 5. 내 맘대로 구현하기 */
    @Override
    public Optional<Member> findById(String ID) {
        List<Member> result = em.createQuery("select m from Member m where m.id=:id", Member.class)
                .setParameter("id", ID)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByPasswd(String passwd) {
        List<Member> result = em.createQuery("select m from Member m where m.passwd=:passwd", Member.class)
                .setParameter("passwd", passwd)
                .getResultList();

        return result.stream().findAny();
    }
    /* 여기까지 */



    /* 6. 내 맘대로 구현하기 step 2 */

    @Override
    public  Optional<Member> findBySex(String sex){
        List<Member> result = em.createQuery("select m from Member m where m.sex:sex", Member.class)
                .setParameter("sex", sex)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m ", Member.class)
                .getResultList();
        return result;
    }
}
