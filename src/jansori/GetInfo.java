package jansori;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetInfo {
	int temperature;
	int finedust;
	int rainrate[] = new int[2];
	
	private int getTemperature() { //기온
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
	
	private int getFineDust() { //미세먼지
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
	
	private int[] getRainRate() { //강수확률
		String url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=안암동+날씨";
		Document doc;
		
		try {
			doc = Jsoup.connect(url).get();
			rainrate[0] = Integer.parseInt(doc.select("li.date_info.today").select("span.point_time.morning").select("span.num").text());
			rainrate[1] = Integer.parseInt(doc.select("li.date_info.today").select("span.point_time.afternoon").select("span.num").text());
		} catch(IOException e) {
			//Connection fail
			e.printStackTrace();
		}
		return rainrate;
	}
	
	public String generateSentence() {
		int tmp = getTemperature();
		int fnd = getFineDust();
		int[] rr = getRainRate();
		String tmp_o = new String();
		String fnd_o = new String();
		String rr_o = new String();
		String sentence = new String();
		
		//tmp 설정
		if(tmp < -10) tmp_o = "오늘은 매우 춥습니다. 뇌졸중 위험이 있으니 괜히 밖에 나가지 말고 제발 이불 속에 박혀있도록 하세요.";
		else if(-10 <= tmp && tmp < 5) tmp_o = "춥습니다. 감기 조심하시고 코 닦을 휴지는 두둑히 챙겨 두시기 바랍니다.";
		else if(5 <= tmp && tmp < 15) tmp_o = "쌀쌀하네요. 코가 근질근질할 때엔 지르텍을 미리 사 자십시오";
		else if(15 <= tmp && tmp < 25) tmp_o = "딱 적당한 기온입니다. 누려보세요.";
		else if(25 <= tmp && tmp < 35) tmp_o = "더우니 물을 많이 드시고 운동은 조심해서 할 수 있도록 하십시오";
		else tmp_o = "살았니?";
		
		//fnd 설정
		if(fnd < 30) fnd_o = "미세먼지 상태는 \"좋음\", 맘 놓고 숨을 쉬어도 될 것 같습니다.";
		else if(30 <= fnd && fnd < 80) fnd_o = "미세먼지 상태는 \"보통\"이며, 살만은 하겠네요";
		else if(80 <= fnd && fnd < 150) fnd_o = "미세먼지 상태는 \"나쁨\"입니다. 마스크를 챙기십시오.";
		else fnd_o = "숨을 쉬지 마십시오";
		
		//rr 설정
		int rrrr = rr[0] + rr[1];
		
		if(rrrr == 0) rr_o = "날씨는 좋습니다!";
		else if(0 < rrrr && rrrr <= 40) rr_o = "구름이 조금 있겠지만 비가 올 확률은 작습니다.";
		else if(40 < rrrr && rrrr <= 100) rr_o = "비가 올 확률이 꽤 됩니다. 혹시 모르니 우산을 챙겨보세요.";
		else if(100 < rrrr && rrrr <= 160) rr_o = "먹구름이 잔뜩 꼈네요. 우산을 챙겨보세요.";
		else rr_o = "오늘은 비가 오네요. 우산 챙기세요.";
	
		sentence = tmp_o + "\n" + rr_o + "\n" + fnd_o;
		return sentence;
	}
}
