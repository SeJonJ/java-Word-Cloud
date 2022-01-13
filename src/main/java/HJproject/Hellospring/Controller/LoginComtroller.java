package HJproject.Hellospring.Controller;

import HJproject.Hellospring.domain.member.Member;
import HJproject.Hellospring.domain.login.LoginForm;
import HJproject.Hellospring.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginComtroller {


    private final LoginService loginService;

    @GetMapping("/login")
    public String LoginForm(@ModelAttribute("LoginForm")LoginForm form){
        return "members/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/login";

        }

        Member LoginMember = loginService.login(form.getLoginid(), form.getLoginpw());

        if (LoginMember == null) { // login 메서드에서 던져주는 값이 null 이면 로그인 실패
            bindingResult.reject("login Fail", "아이디 또는 비밀번호가 맞지 않습니다");
            System.out.println("로그인 실패");
            return "members/login";
        }

        // null 이외의 값 즉 member 객체라면 로그인 성공 처리
        System.out.println("로그인 성공");
        return "redirect:/";
    }

}
