package dev.oneeb.rssfeedactivity;
 
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Stack;
 
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
public class RssHandler extends DefaultHandler {

    private Feed feed;
    private String currentElement;
    private String currentValue;
 
    @Override
    public void startDocument() throws SAXException {
        feed = new Feed();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
        Attributes attributes) throws SAXException {

        qName = qName.toLowerCase();

        if (qName.equals("title") || qName.equals("link") || qName.equals("description") 
            || qName.equals("lastbuilddate") || qName.equals("pubdate")) {
            
            this.currentElement = qName;
        }
    }
 
    @Override
    public void characters(char[] ch, int start, int length) 
        throws SAXException {

        String value = new String(ch, start, length).trim();
        DateTimeFormatter rssDateFormat = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z");
 
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
            OffsetDateTime formattedDate = OffsetDateTime.parse(value, rssDateFormat);
            this.feed.setLastBuildDate(formattedDate);
        }

        if (this.currentElement.equals("pubdate") && this.feed.getPubDate() == null) {
            OffsetDateTime formattedDate = OffsetDateTime.parse(value, rssDateFormat);
            this.feed.setPubDate(formattedDate);
        }
    }

    public Feed getFeed() {
        return this.feed;
    }
}