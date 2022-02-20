package HJproject.Hellospring.Controller;

import HJproject.Hellospring.Session.SessionConst;
import HJproject.Hellospring.Session.SessionManager;
import HJproject.Hellospring.domain.login.LoginForm;
import HJproject.Hellospring.domain.member.Member;
import HJproject.Hellospring.repository.MemberRepository;
import HJproject.Hellospring.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor // 클래스의 final 필드에 대한 생성자를 자동으로 생성
public class new_LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager; // @Component 필요
    private final MemberRepository memberRepository;


    @GetMapping("/login")
    public String LoginForm(@ModelAttribute("LoginForm") LoginForm form) {
        return "members/login";
    }

//    @PostMapping("/login") // http 서블릿 세션 활용 로그인
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginWithHttpSession(@ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse res) throws IOException {
        JSONObject jso = new JSONObject();
        // 에러 관리 메서드(아직 사용 X)
//        if (bindingResult.hasErrors()) {
//            return "members/login";
//        }

        // 1. loginForm 을 통해 로그인 정보들을 가져옴
        Member LoginMember = loginService.login(form.getLoginid(), form.getLoginpw());

        Map<String, Object> result = new HashMap<String, Object>();

        if (LoginMember == null) { // login 메서드에서 던져주는 값이 null 이면 로그인 실패
//            bindingResult.reject("login Fail", "아이디 또는 비밀번호가 맞지 않습니다");
            System.out.println("계정 정보 확인 불가. 로그인 실패");

            res.setContentType("text/html;charset=utf-8");
            // printWiter 객체는 java 단에서 javascript 를 사용할 수 잇게해주는 객체이다
            // memberController 에서의 내용도 마찬가지로 이를 사용한 것.
            PrintWriter out = res.getWriter();

            result.put("state", "fail");
            // out.println 으로 사용하자
            out.println("<script>alert('로그인 실패!! 계정 정보를 확인해주세요');</script>");
            out.flush(); // printwriter 종료

            return "members/login";

        }else if(form.getLoginid() == null || form.getLoginpw() == null){
            System.out.println("아이디나 비밀번호 중 null 값 존재");

            res.setContentType("text/html;charset=utf-8");
            // printWiter 객체는 java 단에서 javascript 를 사용할 수 잇게해주는 객체이다
            // memberController 에서의 내용도 마찬가지로 이를 사용한 것.
            PrintWriter out = res.getWriter();

            result.put("state", "fail");
            // out.println 으로 사용하자
            out.println("<script>alert('아이디나 비밀번호가 비어있어요! 확인해주세요');</script>");
            out.flush(); // printwriter 종료

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
