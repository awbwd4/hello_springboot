package hello.hellospring;

import aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //Spring data JPA에 따라 인터페이스인 MemberRepository도 알아서 구현이 되고

    //Spring bean에 저장되어있다.

    @Bean
    public MemberService memberService(){

        return new MemberService(memberRepository);
    }

    // SpringDataJPA에 따라 MemberRepository가 SpringBean으로 등록이 되어 있으므로
    // 아래와 같은 bean으로 등록하기 위한 메서드가 필요없다.
//    @Bean
//    public MemberRepository memberRepository(){
//
////        return new MemoryMemberRepository();  // db를 연동하지 않은 자바만으로 구현한 리포지토리
////        return new JdbcMemberRepository(dataSource); //순수 jdbc
////        return new JdbcTemplateMemberRepository(dataSource); //JdbcTemplate
////        return new JpaMemberRepository(em);
//

//    }

    //AOP
    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }

}
