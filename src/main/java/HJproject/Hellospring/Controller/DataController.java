package HJproject.Hellospring.Controller;

import HJproject.DataMining.NaverParsing;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class DataController {
    @GetMapping("/")
    public String home(){
        return "thymeleaf/dataplay/wordcloud";
    }


    @RequestMapping(value = "/wordCloud", method = RequestMethod.GET)
    public void wordCloud(HttpServletResponse res, HttpServletRequest req) throws IOException {

        // 웹에서 get으로 요청할때 보내온 파라미터 중 word 파라미터를 가져옴
       String word = req.getParameter("word");

        // NaverParsing 클래스의 parsingData 를 실행하고 겨과를 HashMap 로 저장함
        // 이때 파라미터로 웽에서 가져온 word 를 사용
        HashMap<String, Integer> crawlerData = new NaverParsing().parsingData(word);

        // 데이터 저장을 위한 json array
        JSONArray jsonArray = new JSONArray();

        for(String list : crawlerData.keySet()){
            JSONObject informationObject = new JSONObject();
            // JsonArray 에 저장하기 위해서 값을 하나씩 json 형태로 가져와서 x 에 key를 담고 , value  에는 value을 저장함
            // 이때 anychart 의 경우 x 와 value 를 사용하지만
            // JQcloud 의 경우 text 와 weight 를 사용한다.

            // 이후 다시 array 에 담기
            informationObject.put("x", list);
            informationObject.put("value", crawlerData.get(list));

            jsonArray.add(informationObject);
        }

        // 전달하는 값의 타입을 application/json;charset=utf-8 로 하고(한글을 정상적으로 출력하기 위함)
        // printwriter 과 prrint 를 사용하여 값을 response 로 값을 전달함
        // 이때 toJSONString 로 전당하는데 이는 추후 Jsonparsing 을 원활하게 하기 위해서
        // pw 로 값을 전달하면 값이 response body 에 들어가서 보내짐
        res.setContentType("application/json;charset=utf-8");
        PrintWriter pw = res.getWriter();
        pw.print(jsonArray.toJSONString());
        //System.out.println(jsonArray.toJSONString());

    }
}
