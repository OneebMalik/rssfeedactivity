package dev.oneeb.rssfeedactivity;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.*;
import java.io.*;

public class XmlFetch {

    private XMLReader reader;
    private SAXParserFactory saxParserFactory;

    // Constructor for getting XML data by url.
    public XmlFetch(String url, DefaultHandler handler) throws SAXException {
        try {
            saxParserFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxParserFactory.newSAXParser();
            this.reader = parser.getXMLReader();
        } catch(SAXException e) {
            e.printStackTrace();
        } catch(ParserConfigurationException e) {
            e.printStackTrace();
        }
        
        getXmlByUrl(url, handler);
    }

    public void getXmlByUrl(String url, DefaultHandler handler) throws SAXException {
        try {
            this.reader.setContentHandler(handler);
            this.reader.parse(new InputSource(new URL(url).openStream()));
        } catch(SAXException e) {
            e.printStackTrace();
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}