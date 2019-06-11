package dev.oneeb.rssfeedactivity;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;

public class EntryPoint {

	public static List<CompanyFeed> parsedCompanyList;

	public static void main(String[] args) {
		String url = "https://www.npr.org/rss/podcast.php?id=510298";
		String filename = "oneeb.jsons";

		try {
			getCompanyListFromFile(filename);
		} catch(IOException e) {
			System.err.println("Error! " + e);
			return;
		}

		try {
			getRssData(url);
		} catch(SAXException e) {
			e.printStackTrace();
		}
	}

	public static void getCompanyListFromFile(String filename) throws IOException {
		String fileText = "";
		
		try {
			fileText = new String(Files.readAllBytes(Paths.get(filename)));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		ParseFeedList list = new ParseFeedList(fileText);
		parsedCompanyList = list.parseJson();
	}

	public static void getRssData(String url) throws SAXException {
		RssHandler handler = new RssHandler();
		FeedActivity feed;

		try {
			XmlFetch xmlFetch = new XmlFetch(url, handler);
			feed = new FeedActivity(handler.getFeed());
			System.out.println("RETURNED: " + feed.feedWasActive(3));
		} catch(SAXException e) {
			e.printStackTrace();
		}
	}
}
