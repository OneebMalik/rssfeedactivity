package dev.oneeb.rssfeedactivity;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import java.util.Map;

public class EntryPoint {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";

	public static Map<String, List<String>> parsedCompanyList = null;

	public static void main(String[] args)
		throws InputMismatchException, IOException, ParserConfigurationException, SAXException {
		int daysAgo = -1;
		Scanner scanner = new Scanner(System.in);
		boolean validNumberOfDaysEntered = false;
		// For company list numbering in output.
		int listCount = 0;

		System.out.println("\n\nRSS Feed Activity v1.0.0 by Oneeb Malik");
		System.out.println("----------------------------------------");

		try {
			parsedCompanyList = getCompanyListFromFile();
		} catch(Exception e) {
			// All exception should have been handled before program execution reaches here.
			throw e;
		}

		System.out.print("\nEnter the number of days to check for company RSS feed activity: ");
		try {
			daysAgo = scanner.nextInt();
			validNumberOfDaysEntered = true;
		} catch(InputMismatchException e) {
			validNumberOfDaysEntered = false;
			scanner.next();
		}

		while(daysAgo < 0 || !validNumberOfDaysEntered) {
			if (!validNumberOfDaysEntered) {
				System.err.println(ANSI_RED + "\nError! Enter an integer." + ANSI_RESET);
			} else if (daysAgo < 0) {
				System.err.println(ANSI_RED + "\nError! Number of days is too low. Try again." + ANSI_RESET);
			}

			System.out.print("\nEnter the number of days to check for company RSS feed activity: ");
			
			try {
				daysAgo = scanner.nextInt();
				validNumberOfDaysEntered = true;
			} catch(InputMismatchException e) {
				validNumberOfDaysEntered = false;
				scanner.next();
			}
		}

		System.out.println("\n\nCompanies that have had no activity in the past " + daysAgo + " day(s)");
		System.out.println("---------------------------------------------------------");

		for (String companyName : parsedCompanyList.keySet()) {
			boolean companyWasActive = false;

			for (String companyURL : parsedCompanyList.get(companyName)) {
				try {
					// If even one feed was active for a company, then the company was active.
					companyWasActive = feedWasActive(companyURL, daysAgo) ? true : companyWasActive;
				} catch(Exception e) {
					throw e;
				} 
			}

			if (!companyWasActive) {
				listCount++;
				System.out.print("\n" + listCount + ") ");
				System.out.print(companyName + "\n");
			}
		}
	}

	/*
	 * XML must have well formed XML with 'list' as the root element
	 * and 'company' elements with their own sub-elements called 'url'
	 * to be parsed.
	 */
	public static Map<String, List<String>> getCompanyListFromFile() 
		throws IOException, ParserConfigurationException, SAXException {

		Scanner scanner;
		String fileText = "";
		boolean validFile = false;
		Map<String, List<String>> companyList = null;

		CompanyListHandler handler = new CompanyListHandler();
		XMLFetch xmlFetch;
		
		while (fileText == "" || !validFile) {
			System.out.print("\nEnter file name with company and URL data (or enter q to quit): ");
			scanner = new Scanner(System.in);
			String filename = scanner.nextLine();

			if (filename == "q") {
				System.exit(0);
			}

			try {
				fileText = new String(Files.readAllBytes(Paths.get(filename)));
				xmlFetch = new XMLFetch(XMLInputType.STRING, fileText, handler);
				validFile = true;
			} catch(IOException e) {
				System.err.println(ANSI_RED + "\nError! Invalid file name. Try again." + ANSI_RESET);
			} catch(SAXException e) {
				System.err.println(ANSI_RED + "\nError! Invalid company list format. Enter another valid file." + ANSI_RESET);
				validFile = false;
				continue;
			} catch(ParserConfigurationException e) {
				e.printStackTrace();
			}

			// Program will loop (or in rare events, crash) before it can get a parsed company list.
			companyList = handler.getParsedCompanyList();
		}

		return companyList;
	}

	/*
	 * Check if feed was active for a single company url.
	 */
	public static boolean feedWasActive(String url, int daysAgo)
		throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
		RssHandler handler = new RssHandler();
		FeedActivity feed;

		try {
			XMLFetch xmlFetch = new XMLFetch(XMLInputType.URL, url, handler);
			feed = new FeedActivity(handler.getFeed());

			return feed != null ? feed.feedWasActive(daysAgo) : false;

		} catch(MalformedURLException e) {
			// Do not add to list if there is a URL error.
			return true;
		} catch(Exception e) {
			throw e;
		}
	}
}
