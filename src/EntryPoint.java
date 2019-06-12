package dev.oneeb.rssfeedactivity;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;
import java.util.Scanner;
import java.util.Map;

public class EntryPoint {

	public static List<Map<String, List<String>>> parsedCompanyList;

	public static void main(String[] args) {
		String url = "https://www.npr.org/rss/podcast.php?id=510298";

		System.out.println("\n\nRSS Feed Activity v1.0.0 by Oneeb Malik");
		System.out.println("----------------------------------------");

		String fileText = "";

		try {
			fileText = getCompanyListFromFile();
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}

		// CompanyListHandler companyListHandler = new CompanyListHandler(fileText);
		// parsedCompanyList = companyListHandler.parseXML();

		try {
			getRssData(url);
		} catch(SAXException e) {
			e.printStackTrace();
		}
	}

	public static String getCompanyListFromFile() throws IOException {
		Scanner scanner;
		String fileText = "";
		
		while (fileText == "") {
			System.out.print("\nEnter file name with company and url data: ");
			// scanner = new Scanner(System.in);
			// String filename = scanner.nextLine();
			
			try {
				fileText = new String(Files.readAllBytes(Paths.get("oneeb.xml")));
			} catch(IOException e) {
				System.err.println("\nError! Invalid file. Try again.\n");
			}
		}

		return fileText;
	}

	public static void getRssData(String url) throws SAXException {
		RssHandler handler = new RssHandler();
		FeedActivity feed;

		try {
			XMLFetch xmlFetch = new XMLFetch(XMLInputType.URL, url, handler);
			feed = new FeedActivity(handler.getFeed());
			System.out.println("FEED WAS ACTIVE: " + feed.feedWasActive(3));
		} catch(SAXException e) {
			e.printStackTrace();
		}
	}
}
