package dev.oneeb.rssfeedactivity;
import org.xml.sax.SAXException;

public class EntryPoint {

	public static void main(String[] args) {
		String url = "https://dianerehm.org/rss/npr/dr_podcast.xml";

		try {
			getRssData(url);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void getRssData(String url) throws SAXException {
		RssHandler handler = new RssHandler();

		try {
			XmlFetch siteData = new XmlFetch(url, handler);
		} catch(SAXException e) {
			e.printStackTrace();
		}
	}
}
