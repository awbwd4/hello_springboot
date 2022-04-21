package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import repository.MemoryMemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore(); //사용한 맵 객체 초기화
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member); //save하면 시퀀스가 생성됨, id랑 name 모두 선언

        Member result = repository.findById(member.getId()).get();
        //Optional 타입 객체에서 .get()을 쓰면 내부에 있는 객체를 꺼낼수있다.

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        repository.findByName("spring1").get();

    }


    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }


}
