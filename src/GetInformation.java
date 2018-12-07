import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetInformation {
	int temperature;
	int finedust;
	
	private int getTemperature() { //접근 지정자
		String url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=안암동+날씨";
		Document doc;
		
		try {
			doc = Jsoup.connect(url).get();
			temperature = Integer.parseInt(doc.select("div.today_area._mainTabContent").select("span.todaytemp").text());
		} catch(IOException e) {
			//Connection fail
			e.printStackTrace();
		}
		return temperature;
	}
	
	private int getFinedust() { //접근 지정자
		String url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=안암동+미세먼지";
		Document doc;
		
		try {
			doc = Jsoup.connect(url).get();
			finedust = Integer.parseInt(doc.select("em.main_figure").text());
		} catch(IOException e) {
			//Connection fail
			e.printStackTrace();
		}
		return finedust;
	}
	
	public String generateSentence() {
		int tmp = getTemperature();
		int fnd = getFinedust();
		String tmp_o = new String();
		String fnd_o = new String();
		String sentence = new String();
		
		if(tmp < -10) tmp_o = "오늘은 매우 춥다. 괜히 밖에 나가지 말고 이불 속에 박혀있도록?";
		else if(-10 <= tmp && tmp < 5) tmp_o = "춥다. 감기 조심하고 코 닦을 휴지는 두둑히 챙겨 두자;";
		else if(5 <= tmp && tmp < 15) tmp_o = "코가 근질근질할 때엔 지르텍을 미리 사 먹어라";
		else if(15 <= tmp && tmp < 25) tmp_o = "딱 적당한 기온이다. 밖에 좀 나가도록";
		else if(25 <= tmp && tmp < 35) tmp_o = "더우니 물 많이 마시고 운동은 조심해서 할 수 있도록 한다";
		else tmp_o = "살았니?";
	
		return sentence;
	}
}
