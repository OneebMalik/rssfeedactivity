package dev.oneeb.rssfeedactivity;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

public class EntryPoint {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";

	public static Map<String, List<String>> parsedCompanyList = null;

	public static void main(String[] args) {
		int daysAgo = -1;
		Scanner scanner = new Scanner(System.in);
		boolean validDays = false;

		System.out.println("\n\nRSS Feed Activity v1.0.0 by Oneeb Malik");
		System.out.println("----------------------------------------");

		try {
			parsedCompanyList = getCompanyListFromFile();
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}

		System.out.print("\nEnter the number of days to check for company RSS feed activity: ");
		try {
			daysAgo = scanner.nextInt();
			validDays = true;
		} catch(InputMismatchException e) {
			validDays = false;
			scanner.next();
		}

		while(daysAgo < 0 || !validDays) {
			if (!validDays) {
				System.err.println(ANSI_RED + "\nError! Enter an integer." + ANSI_RESET);
			} else if (daysAgo < 0) {
				System.err.println(ANSI_RED + "\nError! Number of days is too low. Try again." + ANSI_RESET);
			}

			System.out.print("\nEnter the number of days to check for company RSS feed activity: ");
			
			try {
				daysAgo = scanner.nextInt();
				validDays = true;
			} catch(InputMismatchException e) {
				validDays = false;
				scanner.next();
			}
		}

		System.out.println("\n\nCompanies that have had no activity in the past " + daysAgo + " day(s)");
		System.out.println("-----------------------------------------------------");

		int count = 0;

		for (String companyName : parsedCompanyList.keySet()) {
			boolean wasActive = false;

			for (String companyUrl : parsedCompanyList.get(companyName)) {
				try {
					wasActive = feedWasActive(companyUrl, daysAgo) ? true : wasActive;
				} catch(SAXException e) {
					e.printStackTrace();
				} 
			}

			if (!wasActive) {
				count++;
				System.out.print("\n" + count + ") ");
				System.out.print(companyName + "\n");
			}
		}
	}

	public static Map<String, List<String>> getCompanyListFromFile() 
		throws IOException {

		Scanner scanner;
		String fileText = "";

		CompanyListHandler handler = new CompanyListHandler();
		XMLFetch xmlFetch;
		
		while (fileText == "") {
			System.out.print("\nEnter file name with company and url data: ");
			scanner = new Scanner(System.in);
			String filename = scanner.nextLine();
			try {
				fileText = new String(Files.readAllBytes(Paths.get(filename)));
				xmlFetch = new XMLFetch(XMLInputType.STRING, fileText, handler);
			} catch(IOException e) {
				System.err.println(ANSI_RED + "\nError! Invalid file name. Try again." + ANSI_RESET);
			} catch(SAXException e) {
				e.printStackTrace();
			}
		}

		return handler.getParsedCompanyList();
	}

	public static boolean feedWasActive(String url, int daysAgo) throws SAXException {
		RssHandler handler = new RssHandler();
		FeedActivity feed;

		try {
			XMLFetch xmlFetch = new XMLFetch(XMLInputType.URL, url, handler);
			feed = new FeedActivity(handler.getFeed());
			return feed.feedWasActive(daysAgo);
		} catch(SAXException e) {
			e.printStackTrace();
		}

		return false;
	}
}
