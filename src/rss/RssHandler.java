package dev.oneeb.rssfeedactivity;
 
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
 
        if (value.length() == 0 || this.currentElement == null) {
            return;
        }
 
        if (this.currentElement == "title" && this.feed.getTitle() == "") {
            this.feed.setTitle(value);
        }

        if (this.currentElement == "link" && this.feed.getLink() == "") {
            this.feed.setLink(value);
        }

        if (this.currentElement == "description" && this.feed.getDescription() == "") {
            this.feed.setDescription(value);
        }

        // if (this.currentElement == "lastbuilddate" && this.feed.getLastBuildDate() == "") {
        //     this.feed.setLastBuildDate(value);
        // }

        // if (this.currentElement == "pubdate" && this.feed.getPubDate() == "") {
        //     this.feed.setPubDate(value);
        // }
    }

    @Override
    public void endDocument() {
        System.out.println("END: ");
        System.out.println(this.feed.toString());
    }

    public Feed getFeed() {
        return this.feed;
    }
}