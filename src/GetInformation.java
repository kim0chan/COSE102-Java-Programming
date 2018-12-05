import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetInformation {

	public static void main(String[] args) {
		String Url = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&query=%EA%B0%95%EC%9B%90%20%EC%9B%90%EC%A3%BC%EC%8B%9C%20%EB%AF%B8%EC%84%B8%EB%A8%BC%EC%A7%80";
		Document doc;
		try {
			doc = Jsoup.connect(Url).get();
			int finedust = Integer.parseInt(doc.select("em.main_figure").text());
			String temperature = doc.select("span.num").text();
			
			System.out.println(finedust);
			System.out.println(temperature);
			
		} catch (IOException e) {
			// Connection fail
			e.printStackTrace();
		}
	}
}
