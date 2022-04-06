package HJproject.Hellospring.Controller;


import HJproject.Hellospring.Session.SessionConst;
import HJproject.Hellospring.domain.member.Member;
import HJproject.Hellospring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.omg.SendingContext.RunTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;


@Controller
@RequiredArgsConstructor
public class new_HomeController {

    private final MemberRepository memberRepository;

//    @GetMapping("/") // 직접 만든 세션을 사용한 로그인
//    public String LoginHomeWithHttpSession(HttpServletRequest request, Model model) {
//        // 로그인 안했을 시
//        HttpSession session = request.getSession(false);
//
//        if(session == null){
//            return "newspringhome";
//        }
//
//        /* 로그인 시도 시 */
//        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//
//        if(loginMember.getMEMBERCODE() == null){
//
//            // 각각 로그인을 시도했으나 회원 코드가 없는 경우, 회원코드가 0 인 경우, 회원 코드가 0 이 아닌 경우
//            return "newspringhome";
//
//        }else if(loginMember.getMEMBERCODE() == 0){
//            session.getAttribute(session.getId());
//            model.addAttribute("member", loginMember);
//            System.out.println("관리자 로그인 성공");
//
//            /** 이런식으로 session에 담아져있는 값을 가져올 수 있음 */
//            System.out.println(session.getAttribute("memberCode"));
//
//            /** 이런식으로 세션 토큰 값을 가져올 수 있음 **/
//            System.out.println("JSESSIONID :"+request.getRequestedSessionId());
//
//            return "newspringhome_admin";
//
//        }else {
//            model.addAttribute("member", loginMember);
//            System.out.println("일반 회원 로그인 성공");
//            System.out.println("JSESSIONID :"+request.getRequestedSessionId());
//            return "newspringhome_login";
//        }
//
//
//    }

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

        }else if(loginMember.getMEMBERCODE() == 0){

            model.addAttribute("member", loginMember);
            System.out.println("관리자 로그인 성공");

            return "newspringhome_admin";

        }else {
            model.addAttribute("member", loginMember);
            System.out.println("일반 회원 로그인 성공");
            return "newspringhome_login";
        }

    }

    // 스프링에서 jar 파일 실행하기 => 응용하면 jar 뿐만 아니라 다른 파일도 실행 가능할듯?
    // Runtime 을 사용하며, cmd 커멘드를 사용하는듯하다
    @GetMapping("/gamestart")
    public String gameStart(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) throws IOException {
        try {
            // path 로 게임 경로 가져와서 runtime 로 시작
            Runtime rt = Runtime.getRuntime();
            File path = new File("");
            String gameLocat = "java -jar "+ path.getCanonicalPath()+"/src/main/resources/DynamicMusic/New_DynamicMusic-3.0-SNAPSHOT.jar";
            System.out.println(gameLocat);
            rt.exec(gameLocat);
            System.out.println("game start!");


            if(loginMember.getMEMBERCODE() == 0){

                model.addAttribute("member", loginMember);
//                System.out.println("관리자 로그인 성공");

                return "newspringhome_admin";

            }else {
                model.addAttribute("member", loginMember);
//                System.out.println("일반 회원 로그인 성공");
                return "newspringhome_login";
            }

        }catch (Exception e){
            e.printStackTrace();
            return "newspringhome";
        }
    }

}
