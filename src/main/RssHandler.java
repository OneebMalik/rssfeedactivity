package dev.oneeb.rssfeedactivity;
 
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Stack;
 
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
public class RssHandler extends DefaultHandler {

    private Feed feed;
    private String currentElement;
    private String currentValue;
    private boolean isRssV2 = false;
 
    @Override
    public void startDocument() throws SAXException {
        feed = new Feed();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
        Attributes attributes) throws SAXException {

        qName = qName.toLowerCase();

        if (qName.equals("rss") && attributes.getValue("version").equals("2.0")) {
            isRssV2 = true;
        }

        if (qName.equals("title") || qName.equals("link") || qName.equals("description") 
            || qName.equals("lastbuilddate") || qName.equals("pubdate")) {
            
            this.currentElement = qName;
        }
    }
 
    @Override
    public void characters(char[] ch, int start, int length) 
        throws SAXException {

        String value = new String(ch, start, length).trim();
        DateTimeFormatter rssDateFormatZ = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
        DateTimeFormatter rssDateFormatz = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z");

        if (value.length() == 0 || this.currentElement == null) {
            return;
        }
 
        if (this.currentElement.equals("title") && this.feed.getTitle().equals("")) {
            this.feed.setTitle(value);
        }

        if (this.currentElement.equals("link") && this.feed.getLink().equals("")) {
            this.feed.setLink(value);
        }

        if (this.currentElement.equals("description") && this.feed.getDescription().equals("")) {
            this.feed.setDescription(value);
        }

        if (this.currentElement.equals("lastbuilddate") && this.feed.getLastBuildDate() == null) {

            LocalDateTime formattedDate = null;

            try{
                formattedDate = LocalDateTime.parse(value, rssDateFormatz);
            } catch(DateTimeParseException e) {
                try {
                    formattedDate = LocalDateTime.parse(value, rssDateFormatZ);
                } catch(DateTimeParseException ex) {
                    ex.printStackTrace();
                }
            }

            this.feed.setLastBuildDate(formattedDate);
        }

        if (this.currentElement.equals("pubdate") && this.feed.getPubDate() == null) {
            LocalDateTime formattedDate = null;

            try{
                formattedDate = LocalDateTime.parse(value, rssDateFormatz);
            } catch(DateTimeParseException e) {
                try {
                    formattedDate = LocalDateTime.parse(value, rssDateFormatZ);
                } catch(DateTimeParseException ex) {
                    ex.printStackTrace();
                }
            }
            this.feed.setPubDate(formattedDate);
        }
    }

    public Feed getFeed() {
        return isRssV2 ? this.feed : null;
    }
}