package dev.oneeb.rssfeedactivity;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;
import java.util.Scanner;

public class EntryPoint {

	public static List<CompanyFeed> parsedCompanyList;

	public static void main(String[] args) {
		String url = "https://www.npr.org/rss/podcast.php?id=510298";

		System.out.println("RSS Feed Activity v1.0.0 by Oneeb Malik");
		System.out.println("--------------------------------");

		try {
			getCompanyListFromFile();
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}

		try {
			getRssData(url);
		} catch(SAXException e) {
			e.printStackTrace();
		}
	}

	public static void getCompanyListFromFile() throws IOException {

		Scanner scanner;
		

		String fileText = "";
		
		while (fileText == "") {
			System.out.print("\nEnter file name with company and url data: ");
			scanner = new Scanner(System.in);
			String filename = scanner.nextLine();
			
			try {
				fileText = new String(Files.readAllBytes(Paths.get(filename)));
			} catch(IOException e) {
				System.err.println("\nError! Invalid file. Try again.\n");
			}
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
