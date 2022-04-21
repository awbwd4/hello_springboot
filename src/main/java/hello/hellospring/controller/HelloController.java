package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody //http 응답 body부의 내용을 내가 직접 넣어주겠다는 뜻
    public String helloString(@RequestParam("name") String name){
        return "hello "+name; //"hello spring"
    }
    //body부에 직접 값을 넣어주는 경우, view를 찾고말고 없이 이 값을 그대로 브라우저로 전달한다.
    //실제 소스코드를 보면 html 태그 같은거 없음.

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    //객체를 반환 + responsebody인 경우 spring 기본은 json반환이다.


    static class Hello{
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
