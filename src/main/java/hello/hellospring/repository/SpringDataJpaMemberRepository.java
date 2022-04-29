package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //Spring data JPA repository가 JPA repository를 받고 있으면
    //Spring data JPA가 이 인터페이스의 구현체를 자동으로 만들어서
    //spring bean으로 등록을 해준다.

    @Override
    Optional<Member> findByName(String name);

    // findByName으로 선언이 되어있으면
    // SpringDataJPA가 자동으로 "Name"을 인식해서
    // JPQL로 "select m from Member m where m.name = ?"을 생성한다.
    // findByNameAndId 라면
    // "select m from Member m where m.name = ? and m.id = ?"

}
