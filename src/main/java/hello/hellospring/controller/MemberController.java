package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

//    private final MemberService memberService = new MemberService();
//    MemberService객체가 쓰일때마다 새로 생성되는 방식
//    컨트롤러 뿐만 아니라 이후에 호출될 여기저기에서 멤버 서비스 객체가 쓰일텐데, 쓰일때마다 객체를 새로 생성하는건 너무 비효율적
//    따라서 스프링 컨테이너에 하나 등록해서 같이 사용함.


    private final MemberService memberService;

    // autowired : 스프링 컨테이너가 기동이 되면, 어노테이션으로 등록된 컨트롤러/서비스/리포지토리 등이 자동으로 생성이 된다
    // 이때, 내부의 autowired 어노테이션을 통해서 의존관계를 해소해준다.
    // 예를들면 이 컨트롤러는 생성될 시에 MemberService객체가 필요하다고 생성자에 선언이 되어있다.
    // @Controller, @Service, @Repository등으로 된 객체들은 자동으로 스프링 빈으로 등록이 되며
    // 각각의 객체들의 생성자에 @autowired가 있으면 이 객체들을 연결해준다.
    // 따라서 MemberService 객체 스프링이 관리하는 빈이므로

    // MemberController가 생성될 시에 스프링이 알아서 MemberService를 찾아주어서 컨트롤러에 주입해준다
    @Autowired // 그냥 자바 객체인 memberService가 아니라 스프링 빈으로 등록이 된 memberService를 주입시켜준다.
    public MemberController(MemberService memberService) {
        //Controller 생성자를 생성할때 memberservice객체도 같이 생성한다
        this.memberService = memberService;
        System.out.println("memberService : "+memberService.getClass());
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        System.out.printf("form.getName : %s", form.getName());
        member.setName(form.getName());

        memberService.join(member);
        System.out.println("============");
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
