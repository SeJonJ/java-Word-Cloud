package HJproject.DataMining;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class NaverCrawler implements APIdata{ // 베이스 URL
    final String baseUrl = "https://openapi.naver.com/v1/search/blog.json?query=";


    public String Navercrawler(String word){
        String crawerString = null;

        try {


            // 매개변수 : 검색단어, 인코딩
            String url = URLEncoder.encode(word, "UTF-8");
            // crawler 의 search 메소드 사용
            // 이때 naverID 와 naverSecret 은 APIdata 안에 있는 내용 사용
            String response = search(naverID, naverSecret, url);

            // 필드값은 title 가 desc 2개!
            // 크롤링을 하게되면 field 가 여러개가 나오는데 이 중에서 title 와 desc 만 가져온다는 의미
            String[] fields = {"title","description"};

            // 결과를 Map 형태로 저장장
           Map<String, Object> result = getResult(response, fields);

            // 검색 결과가 1개 이상인 경우 result 값을 출력
            if (result.size() > 0) {
                System.out.println("total -> " + result.get("total"));
            }

//            System.out.println("result : "+result);

            // 검색 결과를 다시 List 형태로 저장
            List<Map<String, Object>> items = (List<Map<String, Object>>) result.get("result");

            //
            for (Map<String, Object> item : items) {
                crawerString += item.get("title");
                crawerString += item.get("description");
            }


            return crawerString;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // 여기는 네이버 검색 API
    public String search(String clientId, String secret, String _url) {
        HttpURLConnection con = null;
        String result = "";
        try {
            URL url = new URL(baseUrl + _url +"&display=50");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", secret);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) result = readBody(con.getInputStream());
            else result = readBody(con.getErrorStream());
        } catch (Exception e) {
            System.out.println("연결 오류 : " + e);
        } finally {
            con.disconnect();
        }
        return result;
    }


    public String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);
        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }


    public Map<String, Object> getResult(String response, String[] fields) {
        Map<String, Object> rtnObj = new HashMap<>();
        try {
            JSONParser parser = new JSONParser();
            JSONObject result = (JSONObject) parser.parse(response);
            rtnObj.put("total", (long) result.get("total"));
            JSONArray items = (JSONArray) result.get("items");
            List<Map<String, Object>> itemList = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                JSONObject item = (JSONObject) items.get(i);
                Map<String, Object> itemMap = new HashMap<>();
                for (String field : fields) {
                    itemMap.put(field, item.get(field));
                }
                itemList.add(itemMap);
            }
            rtnObj.put("result", itemList);
        } catch (Exception e) {
            System.out.println("getResult error -> " + "파싱 실패, " + e.getMessage());
        }
        return rtnObj;
    }

}
