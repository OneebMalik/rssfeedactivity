package dev.oneeb.rssfeedactivity;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.*;
import java.io.*;

enum XMLInputType{ 
    URL, STRING;
} 
  

public class XMLFetch {

    private XMLReader reader;
    private SAXParserFactory saxParserFactory;

    // Constructor for getting XML data by url.
    public XMLFetch(XMLInputType inputType, String xmlSource, DefaultHandler handler) throws SAXException {
        try {
            saxParserFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxParserFactory.newSAXParser();
            this.reader = parser.getXMLReader();
        } catch(SAXException e) {
            e.printStackTrace();
        } catch(ParserConfigurationException e) {
            e.printStackTrace();
        }

        switch(inputType) {
            case URL:
                getXMLByUrl(xmlSource, handler);
                break;
            case STRING:
                getXMLByString(xmlSource, handler);
        }
    }

    public void getXMLByUrl(String url, DefaultHandler handler) throws SAXException {
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

    public void getXMLByString(String rawXML, DefaultHandler handler) throws SAXException {
        this.reader.setContentHandler(handler);
        try {
            this.reader.parse(new InputSource(new StringReader(rawXML)));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}