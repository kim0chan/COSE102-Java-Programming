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
	
	/*public String generateSentence() {
		int tmp = getTemperature();
		int fnd = getFinedust();
		String tmp_o = new String();
		String fnd_o = new String();
		
		
	}*/
}
