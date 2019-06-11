package dev.oneeb.rssfeedactivity;

public class EntryPoint {

	public static void main(String[] args) {
		
		String url = "https://dianerehm.org/rss/npr/dr_podcast.xml";
		
		RssHandler handler = new RssHandler();
		XmlFetch siteData = new XmlFetch(url, handler);
	}
}
