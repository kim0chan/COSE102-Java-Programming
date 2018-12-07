package jansori;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetInfo {
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
		
		//tmp 설정
		if(tmp < -10) tmp_o = "오늘은 매우 춥습니다. 괜히 밖에 나가지 말고 이불 속에 박혀있도록 하세요.";
		else if(-10 <= tmp && tmp < 5) tmp_o = "춥습니다. 감기 조심하시고 코 닦을 휴지는 두둑히 챙겨 두시기 바랍니다;;";
		else if(5 <= tmp && tmp < 15) tmp_o = "코가 근질근질할 때엔 지르텍을 미리 사 자십시오";
		else if(15 <= tmp && tmp < 25) tmp_o = "딱 적당한 기온입니다. 이 기온을 누리세요.";
		else if(25 <= tmp && tmp < 35) tmp_o = "더우니 물을 많이 드시고 운동은 조심해서 할 수 있도록 하십시오";
		else tmp_o = "살았니?";
		
		//fnd 설정
		if(fnd < 30) fnd_o = "미세먼지 상태는 \"좋음\", 맘 놓고 숨을 쉬어도 될 것 같습니다.";
		else if(30 <= fnd && fnd < 80) fnd_o = "미세먼지 상태는 \"보통\"이며, 살만은 하겠네요";
		else if(80 <= fnd && fnd < 150) fnd_o = "미세먼지 상태는 \"나쁨\"입니다. 마스크를 챙기십시오.";
		else fnd_o = "숨을 쉬지 마십시오";
	
		sentence = tmp_o + "\n" + fnd_o;
		return sentence;
	}
}
