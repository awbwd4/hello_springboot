package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    //jpa는 entitymanager라는 걸로 모든게 동작한다.
    //gradle과 application.properties에 jpa 설정이 되어있다면
    // 어플리케이션이 동작할때, 스프링 부트가 알아서 entity manager를 등록해준다.
    // 지금은 그걸 di하는것.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        //persist : 영구저장하다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    //pk가 아닌 칼럼 값들을 조회할때에는 JPQL을 작성해줘야 한다.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        //JPQL : 테이블 대상이 아닌 객체를 대상으로 쿼리를 날림, 이게 sql로 번역이 됨.
    }
}
