package HJproject.Hellospring.Session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
// 세션을 관리해주는 기능
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "mySessionId";

    // 동시성 문제가 있는 경우 ConcurrentHashMap 를 사용해야 함.
    // 동시성 문제란 한마디로 '동시에' 스레드가 많이 발생하는 경우가 있는 것을 의미함 => 로그인은 동시에 많이 발생할 수 있음
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /*
    *  세션 생성
    *  1. SessionId 생성 (임의의 추정 불가능한 랜덤 값)
    *  2. 세션 저장소에 SessionId 와 보관할 값 저장
    *  3. SessionId 로 응답 쿠키를 생성해서 클라이언트에 전달
    * */

    public void createSession(Object value, HttpServletResponse response){

        // SessionId 생성, 값을 세션에 저장
        String SessionId = UUID.randomUUID().toString(); // UUID 를 활용한 SessionId 생성
        sessionStore.put(SessionId, value); // 세션 저장소에 SessionId 와 보관할 값 저장

        // 쿠키 생성 : 쿠키 이름은 SESSION_COOKIE_NAME , 값은 SessionId
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, SessionId);
        response.addCookie(mySessionCookie);
    }


/*    세션 조회 방법 1 : getSession 메서드 안에 쿠키를 찾는 내용 포함

    public Object getSession(HttpServletRequest request){
        Cookie[] cookies = request.getCookies(); // 쿠키는 배열로 반환된다.

        if(cookies == null){
            return null;
        }


        // 배열이기 때문에 안에 있는 값을 for 문으로 찾아옴
        // for 문이 돌면서 getName 햇을 때 SESSION_COOKIE_NAME 와 동일한 값이 있다면
        // 쿠키 저장소에서 해당 쿠키의 value 를 가져옴
       for (Cookie cookie : cookies){
            if(cookie.getName().equals(SESSION_COOKIE_NAME)){
                return sessionStore.get(cookie.getValue());
            }
        }

        return null;
    }*/

    /* 세션 조회 방법 2 : findCookie 를 따로 생성해서 해당 메서드를 사용해 쿠키값을 찾아옴 */

    public Object getSession(HttpServletRequest request){
        // SessionCookie 에 findCookie 를 메서드를 사용해서 찾아온 SESSION_COOKIE_NAME 를 저장함
        Cookie Sessioncookie = findCookie(request, SESSION_COOKIE_NAME);
        if(Sessioncookie == null){
            return null;
        }
        return sessionStore.get(Sessioncookie.getValue());
    }

    public Cookie findCookie(HttpServletRequest request, String cookieName){
        // 여기서 CookieName 는 SESSION_COOKIE_NAME 의미

        Cookie[] cookies = request.getCookies();

        if(cookies == null){
            return null;
        }
        return Arrays.stream(cookies) // arrays 를 스트림을 바꿔줌
                .filter(cookie -> cookie.getName().equals(cookieName))
                /*
                findfirst 와 findAny 둘중 하나를 쓸 수 있는데
                1. findAny : 순서 상관X! 빨리 나오면 꺼내옴
                2. findfrist : 순서 중요! 순서에 따라서 돌다가 맞으면 꺼내옴
                 */
                .findAny()
                .orElse(null);
    }

    /*
    * 3. 세션 만료 : Session 만료는 그냥 지워버리면 된다
    */
    public void expireCookie(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);

        // findCookie 로 세션을 가져와서 해당 값이 null 이 아니면 세션 스토어에 저장, 매핑된 값을 삭제!
        if(sessionCookie != null){
            sessionStore.remove(sessionCookie.getValue());
        }
    }
}
