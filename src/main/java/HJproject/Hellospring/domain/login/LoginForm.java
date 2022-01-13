package HJproject.Hellospring.domain.login;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginForm {

    // notNull 어노테이션은 해당 값이 꼭 있어야함함

   @NotNull
    private String loginid;

    @NotNull
    private String loginpw;
}
