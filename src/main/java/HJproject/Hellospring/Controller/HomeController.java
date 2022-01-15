package HJproject.Hellospring.Controller;


import HJproject.Hellospring.domain.member.Member;
import HJproject.Hellospring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

//    @GetMapping("/") // 가장 기본 페이지 의미, 즉 루트 페이지 위치
//    public String home(){
//        return "newspringhome"; // springhome.html 을 찾아서 연다.
//    }

    @GetMapping("/")
    // @CookieValue 를 통해서 쿠키값을 가져올 수 있다.
    // required = true 의 경우 쿠키값이 없으면 접속 불가능, false 는 없어도 접근 가능
    public String LoginHome(@CookieValue(name = "memberCode", required = false) Long memberCode, Model model) {

        // 로그인 안했을 시
        if(memberCode == null){
            System.out.println("memberCode : "+memberCode);
            return "newspringhome";
        }

        /* 로그인 시도 시 */
        // loginMember 가 Optional 로 감싸져있기 때문에 .get() 으로 한번 가져와야 안에있는 값을 가져올 수 있음
        Optional<Member> loginMember = memberRepository.findByCode(memberCode);

        // 로그인 할 때 가져오는 Code 값이 null 인 경우
        if(loginMember.get().getCode() == null){
            System.out.println("getCode : "+ loginMember.get().getCode());
            return "newspringhome";

        }else if(loginMember.get().getCode() == 0){
            model.addAttribute("member", loginMember.get());
            System.out.println("관리자 로그인 성공");
            return "newspringhome_admin";

        }else {
            model.addAttribute("member", loginMember.get());
            System.out.println("로그인 성공");
            return "newspringhome_login";
        }
    }



    @PostMapping("/logout")
    // 쿠키를 삭제하려면 쉽게 그냥 쿠키 생명주기, 시간을 0으로 만들어 버리면 됨
    public String logout(HttpServletResponse httpServletResponse){
        Cookie cookie = new Cookie("memberCode", null);
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
        return "redirect:/";
    }
}
