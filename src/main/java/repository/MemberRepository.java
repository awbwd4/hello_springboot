package repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //Optional : 자바 8에서 도입된 wrapper class, 만약 null값을 반환해야할 경우
    // null을 그대로 반환하는 것이 아니라 이 Optional 타입으로 반환하는 wrapper class이다.
    List<Member> findAll();

}
