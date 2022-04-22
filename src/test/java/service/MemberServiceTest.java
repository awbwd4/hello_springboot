package service;


import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.MemoryMemberRepository;


class MemberServiceTest {

//    MemberService memberService = new MemberService(new MemoryMemberRepository());
    MemberService memberService;

    @BeforeEach //동작 하기 전에
    public void afterEach(){
        memberService = new MemberService(new MemoryMemberRepository());
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