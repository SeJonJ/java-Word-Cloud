package HJproject.Hellospring.Controller;

import HJproject.Hellospring.Session.SessionConst;
import HJproject.Hellospring.Session.SessionManager;
import HJproject.Hellospring.domain.login.LoginForm;
import HJproject.Hellospring.domain.member.Member;
import HJproject.Hellospring.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor // 클래스의 final 필드에 대한 생성자를 자동으로 생성
public class new_LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager; // @Component 필요

    @GetMapping("/login")
    public String LoginForm(@ModelAttribute("LoginForm")LoginForm form){
        return "members/login";
    }

    @PostMapping("/login") // http 서블릿 세션 활용 로그인
    public String loginWithHttpSession(@ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "members/login";
        }

        // 1. loginForm 을 통해 값들을 가져옴
        Member LoginMember = loginService.login(form.getLoginid(), form.getLoginpw());

        if (LoginMember == null) { // login 메서드에서 던져주는 값이 null 이면 로그인 실패
//            bindingResult.reject("login Fail", "아이디 또는 비밀번호가 맞지 않습니다");
            System.out.println("로그인 실패");
            return "members/login";
        }

        // 2. null 이외의 값 즉 member 객체라면 로그인 성공 처리

        // http 서블릿 세션을 활용하기 위해서는 httpservlerequest 가 필요함 : 세션이 있으면 세션 반환, 없으면 세션 생성
        // 이때 request.getSession() 에서 getSession() 의 파라미터안에는 true, false 두가지가 올 수 있음
        HttpSession session = request.getSession();

        // 세션에 회원 정보(LoginMember) 저장 후 홈으로 반환
        session.setAttribute(SessionConst.LOGIN_MEMBER, LoginMember);

        /** 이렇게 세션에 별도의 다른 값을 넣어 둘 수 있음 **/
        session.setAttribute("memberCode", LoginMember.getMEMBERCODE());
        return "redirect:/";
    }

    @PostMapping("/logout") // http 서블릿 세션 활용 로그아웃
    // 세션 종료를 위해서는 sessionManager 에 만들어두었던 expireCookie 를 사용하자
    public String loginWithHttpSession(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session != null){
            // invalidate 는 세션을 삭제하는 기능
            session.invalidate();
        }
        return "redirect:/";
    }

}
