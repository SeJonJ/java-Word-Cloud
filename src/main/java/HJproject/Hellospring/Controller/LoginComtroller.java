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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
    /* HttpServletResponse : 아래의 쿠키값을 생성 후 클라이언트에게 보낼때 response 에 넣어서 보내야함 */
    public String login(@ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) {
            return "members/login";

        }

        // loginForm 을 통해 값들을 가져옴
        Member LoginMember = loginService.login(form.getLoginid(), form.getLoginpw());

        if (LoginMember == null) { // login 메서드에서 던져주는 값이 null 이면 로그인 실패
//            bindingResult.reject("login Fail", "아이디 또는 비밀번호가 맞지 않습니다");
            System.out.println("로그인 실패");
            return "members/login";
        }

        // null 이외의 값 즉 member 객체라면 로그인 성공 처리

        // 쿠키에 시간 정보를 주지 않았기 때문에 세션 쿠키로 인식됨 -> 브라우저 종료시 모두 종료
        Cookie CookieCode = new Cookie("memberCode", String.valueOf(LoginMember.getCode()));
        httpServletResponse.addCookie(CookieCode);
        System.out.println("쿠키 정보 전달 완료 : "+ CookieCode);
        return "redirect:/";
    }

}
