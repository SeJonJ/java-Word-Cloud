package HJproject.Hellospring.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/") // 가장 기본 페이지 의미, 즉 루트 페이지 위치
    public String home(){
        return "newspringhome"; // springhome.html 을 찾아서 연다.
    }

    @GetMapping("/login") // 로그인 페이지 get 메소드로 받으면
    public String login(){
        return "login"; // login.html 을 찾아서 연다
    }

}
