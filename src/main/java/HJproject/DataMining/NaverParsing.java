package HJproject.DataMining;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;


public class NaverParsing implements APIdata {


    public HashMap<String, Integer> parsingData(String word) {

        // System.getProperty 를 사용해서 파일이 실행되는 현재 위치 가져오기
        // iDE 환경에서는 워크스페이스 경로를 가져오고, jar 파일의 경우 jar 파일 실행 경로를 가져옴
        String path = System.getProperty("user.dir");
//            System.out.println(path);

        // Komoran 사용을 위한 초기화 && 선언
        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
        // user Dictionary 사용을 위한 위치 경로 정의
        komoran.setUserDic(path + "/userDictionary/koreanDic.user");

        // NaverCrawler 클래스의 crawlerData 메소드를 사용해서 크롤링한 STring 을 얻어오기
        String dataString = new NaverCrawler().Navercrawler(word);

        // 가져온 String을 komoran analyze 메소드에 넣기
        KomoranResult komoranResult = komoran.analyze(dataString);

        // 여기서 getMorphesByTags 사용하면 내가원하는 형태소만 뽑아낼 수 있음
        List<String> analyzeList = komoranResult.getMorphesByTags("NNP", "NNG", "NNB", "NP");

        // list 파일로 떨어진 analyzeList 를 HashMap 에 넣어서 중복된 데이터를 삭제하고
        // Conllections.frequency 를 사용해서 몇 번이나 중복되었는지 분석하여 저장한다.
        // 최종적으로 listHash 에는 단어=중복횟수 로 저장된다.
        HashMap<String, Integer> listHash = new HashMap<>();
        for (String l : analyzeList) {
            int num = Collections.frequency(analyzeList, l);
            listHash.put(l, num);
        }

        // 데이터 정렬을 위한 코드
//            List<Map.Entry<String, Integer>> list_entries = new ArrayList<Map.Entry<String, Integer>>(listHash.entrySet());
//            Collections.sort(list_entries, new Comparator<Map.Entry<String, Integer>>() {
//                @Override
//                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                    return o2.getValue().compareTo(o1.getValue());
//                }
//            });
//            for(Map.Entry<String, Integer> l : list_entries){
//                System.out.println(l);
//            }


        return listHash;

    }

//    public static void main(String[] args) {
//
//        String path = System.getProperty("user.dir");
////        System.out.println(path);
//
//        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
//        komoran.setUserDic(path+"/userDictionary/koreanDic.user");
//
//
//        HashMap<String, Integer> crawlerData = new NaverParsing().parsingData("엘든링");
//        JSONArray jsonArray = new JSONArray();
//
//        for(String list : crawlerData.keySet()){
//            System.out.println("key : "+list +"\t"+"value : "+crawlerData.get(list));
//            JSONObject informationObject = new JSONObject();
//            informationObject.put("text", list);
//            informationObject.put("weight", crawlerData.get(list));
//
//            jsonArray.add(informationObject);
//        }
//
//
//
//
//        System.out.println(jsonArray.toJSONString());
//
//    }

}
