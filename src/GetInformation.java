import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetInformation {

	public static void main(String[] args) {
		String Url = "http://www.kweather.co.kr/forecast/forecast_lifestyle_detail.html?main_map=1&area1=area8&area2=42110000_101";
		Document doc;
		try {
			doc = Jsoup.connect(Url).get();
			Elements elem = doc.select("span[id=\"day0tempMax0\"]");
			System.out.println(elem.text());
		} catch (IOException e) {
			// Connection fail
			e.printStackTrace();
		}
	}
}
