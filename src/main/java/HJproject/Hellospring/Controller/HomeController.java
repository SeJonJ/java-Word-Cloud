//package HJproject.Hellospring.Controller;
//
//
//import HJproject.Hellospring.Session.SessionManager;
//import HJproject.Hellospring.domain.member.Member;
//import HJproject.Hellospring.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.CookieValue;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Optional;
//
//
//@Controller
//@RequiredArgsConstructor
//public class HomeController {
//
//    private final MemberRepository memberRepository;
//    private final SessionManager sessionManager;
//
////    @GetMapping("/") // 가장 기본 페이지 의미, 즉 루트 페이지 위치
////    public String home(){
////        return "newspringhome"; // springhome.html 을 찾아서 연다.
////    }
//
////    @GetMapping("/") : 쿠키를 활용한 로그인 홈페이지 정의
//    // @CookieValue 를 통해서 쿠키값을 가져올 수 있다.
//    // required = true 의 경우 쿠키값이 없으면 접속 불가능, false 는 없어도 접근 가능
//    public String LoginHome(@CookieValue(name = "memberCode", required = false) Long memberCode, Model model) {
//
//        // 로그인 안했을 시
//        if(memberCode == null){
//            System.out.println("memberCode : "+memberCode);
//            return "newspringhome";
//        }
//
//        /* 로그인 시도 시 */
//        // loginMember 가 Optional 로 감싸져있기 때문에 .get() 으로 한번 가져와야 안에있는 값을 가져올 수 있음
//        Optional<Member> loginMember = memberRepository.findByCode(memberCode);
//
//        // 로그인 할 때 가져오는 Code 값이 null 인 경우
//        if(loginMember.get().getCode() == null){
//            System.out.println("getCode : "+ loginMember.get().getCode());
//            return "newspringhome";
//
//        }else if(loginMember.get().getCode() == 0){
//            model.addAttribute("member", loginMember.get());
//            System.out.println("관리자 로그인 성공");
//            return "newspringhome_admin";
//
//        }else {
//            model.addAttribute("member", loginMember.get());
//            System.out.println("로그인 성공");
//            return "newspringhome_login";
//        }
//    }
//
////    @GetMapping("/") // 직접 만든 세션을 사용한 로그인
//    public String LoginHomeWithSession(HttpServletRequest request, Model model) {
//
//        // 세션 관리자를 통한 회원 정보 확인 : 타입이 Member 임으로 라는 캐스팅 필요
//        Member member = (Member) sessionManager.getSession(request);
//
//        // 로그인 안했을 시
//        if(member == null){
//            System.out.println("memberCode : "+member);
//            return "newspringhome";
//        }
//
//        /* 로그인 시도 시 */
//        System.out.println("회원 코드 확인 : " + member.getCode());
//        Cookie cookie = sessionManager.findCookie(request, SessionManager.SESSION_COOKIE_NAME);
//
//        // 각각 로그인을 시도했으나 회원 코드가 없는 경우, 회원코드가 0 인 경우, 회원 코드가 0 이 아닌 경우
//        if(member.getCode() == null){
//            System.out.println("쿠키 : "+cookie.getValue());
//            return "newspringhome";
//
//        }else if(member.getCode() == 0){
//            model.addAttribute("member", member);
//            System.out.println("관리자 로그인 성공");
//            System.out.println("쿠키 : "+cookie.getValue());
//
//            return "newspringhome_admin";
//
//        }else {
//            model.addAttribute("member", member);
//            System.out.println("일반 회원 로그인 성공");
//            System.out.println("쿠키 : "+cookie.getValue());
//            return "newspringhome_login";
//        }
//
//
//    }
//
//}
