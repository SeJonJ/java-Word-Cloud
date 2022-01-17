package HJproject.Hellospring.Session;

import HJproject.Hellospring.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class SessionManagerTEST {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTEST(){
        /** 1. 여기는 서버 -> 클라이언트로 **/

        // HttpServletResponse 는 기본적으로 인터페이스! 이를 임의로 테스트 할 수 있또록 만든것이 MockHttpServletResponse
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 세션 생성 : 세션 생성 후 서버 -> 클라이언트로 가는 response 응답에 세션을 담은 상태
        Member member = new Member();
        sessionManager.createSession(member, response); // createSession(value, response)




        /** 2. 여기서부터는 클라이언트 -> 서버로 **/

        // 요청에 응답 쿠키 저장 확인 : 클라이언트에서 서버로 쿠키 전달 확인
        // response 응답에서 나온 쿠키를 가져와서 클라이언트 웹 브라우저에서 setCookie 쿠키를 만들어둠

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());
        System.out.println("mySessionID ="+ request.getCookies());


        // 세션 조회 : 클라이언트에서 쿠키로 정보 조회 request 시 사용되는 내용
        // 즉 value 에 해당하는 member 와 result 가 같은지 확인
        Object result = sessionManager.getSession(request);
        Assertions.assertThat(result).isEqualTo(member);
        System.out.println("result ="+ result);
        System.out.println("member ="+ member);

        // 세션 만료
        sessionManager.expireCookie(request);
        Object cookieExpired = sessionManager.getSession(request);
        Assertions.assertThat(cookieExpired).isNull();

    }
}
