package service;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach //동작 하기 전에
    public void afterEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        //MemberService 입장에서는 MemoryMemberRepository() 객체를 외부에서 넣어준것
        // Dependency Injection!
    }


    @Test
    void 회원가입(){
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());


    }

    @Test
    public void 중복회원예외처리(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        IllegalStateException e = org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try{
//            memberService.join(member2);
//            fail();//위에서 예외가 안터지고 이 줄까지 내려올경우 테스트 실패임.
//        } catch (IllegalStateException e) {
//            //예외처리 정상
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
//        }



        //then



    }


    @Test
    void findMembers(){

    }

    @Test
    void findOne(){

    }

}