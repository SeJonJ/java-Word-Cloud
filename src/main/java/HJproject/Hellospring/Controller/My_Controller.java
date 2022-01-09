package HJproject.Hellospring.Controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class My_Controller {

    @GetMapping("hello")
    public String hello(Model model) { // MVC : model view controller 중 model
        model.addAttribute("data","hello!!"); // data 가 넘어오면 hello!! 출력
        return "hello";
        // viewResolver 가가화면을 찾아서 처리함
        // resources : 'templates/' + (ViewName)+'.html'
    }

    @GetMapping("home")
    public String home(Model model){
        model.addAttribute("home","my sweet spring home");
        return "home";
    }

    @GetMapping("hello-mvc")
    public String hellomvc(@RequestParam(value = "name") String name, Model model){
        // @RequestParam 뒤에 나오는 value 값은 http에서 request 파라미터를 의미한다.
        model.addAttribute("name", name);
        // 파라미터로 넘어온 name 을 model에 담아서 hello-template 의 name 으로 넘긴다.
        return "hello-template";
    }

    // 문자 전달
    @GetMapping("hello-api")
    @ResponseBody // http 의 Response의 Body 부분에 아래 데이터를 바로 넣겠다는 의미
    public String helloAPI(@RequestParam("name") String name){
        return "Spring 어려워진다! " + name;
    }

    // 데이터 전달 : 객체를 전달하는 경우
    @GetMapping("hello-json") // html 상에서 hello-json 의 get 으로 받으면
    @ResponseBody // Response Body 부분에 아래 내용을 넣는다.
    public hellojson hello_json(@RequestParam("name") String name, String id, String passwd){
        hellojson hellojson = new hellojson();

        hellojson.setName(name); // setName 매서드로 hellojson name 에 파라미터로 넘어온 name 을 넣는다.

        //응용해서 id 와 passwd 를 받아보자

        hellojson.setId(id);
        hellojson.setPasswd(passwd);


        return hellojson; // 문자가 아닌 객체를 넘김
    }

    static class hellojson{
        private String name;
        private String id;
        private String passwd;

        // 데이터 출력 시에는 getName
        public String getName() {
            return name;
        }

        // 데이터 넣을 때는 setName
        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }
    }



}
