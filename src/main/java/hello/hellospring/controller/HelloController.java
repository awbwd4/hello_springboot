package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data1", "hello!!");
        model.addAttribute("data2", "helloddddd!!");
        System.out.println("hello!!");
        return "hello";
        // 컨트롤러에서 리턴값으로 문자를 반환하면 view resolver가 templates 디렉토리 안의
        // hello.html을 알아서 찾아서 렌더링해준다.
    }


    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(required = true, value = "name") String name, Model model){
        model.addAttribute("name", name);
        System.out.println("hello-mvc");
        return "hello-template";
    }


}
