package HJproject.Hellospring.Controller;


import HJproject.Hellospring.Session.SessionConst;
import HJproject.Hellospring.Session.SessionManager;
import HJproject.Hellospring.domain.member.Member;
import HJproject.Hellospring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class new_HomeController {

    private final MemberRepository memberRepository;

//    @GetMapping("/") // 직접 만든 세션을 사용한 로그인
    public String LoginHomeWithHttpSession(HttpServletRequest request, Model model) {
        // 로그인 안했을 시
        HttpSession session = request.getSession(false);

        if(session == null){
            return "newspringhome";
        }

        /* 로그인 시도 시 */
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(loginMember.getCode() == null){

            // 각각 로그인을 시도했으나 회원 코드가 없는 경우, 회원코드가 0 인 경우, 회원 코드가 0 이 아닌 경우
            return "newspringhome";

        }else if(loginMember.getCode() == 0){
            session.getAttribute(session.getId());
            model.addAttribute("member", loginMember);
            System.out.println("관리자 로그인 성공");

            /** 이런식으로 session에 담아져있는 값을 가져올 수 있음 */
            System.out.println(session.getAttribute("memberCode"));

            /** 이런식으로 세션 토큰 값을 가져올 수 있음 **/
            System.out.println("JSESSIONID :"+request.getRequestedSessionId());

            return "newspringhome_admin";

        }else {
            model.addAttribute("member", loginMember);
            System.out.println("일반 회원 로그인 성공");
            System.out.println("JSESSIONID :"+request.getRequestedSessionId());
            return "newspringhome_login";
        }


    }

    @GetMapping("/") // 스프링이 지원하는 세션 기능 : @SessionAttribute
    public String LoginHomeWithHttpSessionv2(
            // 기능은 이전과 동일. 즉 SessionConst.LOGIN_MEMBER 참고하여 세션 유무 확인 후 없으면 null 반환
            // 로그인 하면 member 에 담은 것 활용해서 로그인 세션 기능 제공
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {

        // 로그인 안했을 시
        // 스프링이 알아서 처리해줌 만세!

        /* 로그인 시도 시 */
        // 각각 로그인을 시도했으나 회원 코드가 없는 경우, 회원코드가 0 인 경우, 회원 코드가 0 이 아닌 경우
        if(loginMember == null){

            return "newspringhome";

        }else if(loginMember.getCode() == 0){

            model.addAttribute("member", loginMember);
            System.out.println("관리자 로그인 성공");

            return "newspringhome_admin";

        }else {
            model.addAttribute("member", loginMember);
            System.out.println("일반 회원 로그인 성공");
            return "newspringhome_login";
        }


    }

}
