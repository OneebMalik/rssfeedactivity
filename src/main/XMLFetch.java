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

    /*
     * Get valid XML data to pass to a handler.
     * Route to the appropriate function based on type of XML input source.
     */
    public XMLFetch(XMLInputType inputType, String xmlSource, DefaultHandler handler) 
        throws SAXException, ParserConfigurationException, MalformedURLException,
            IOException {

        try {
            saxParserFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxParserFactory.newSAXParser();
            this.reader = parser.getXMLReader();
        } catch(Exception e) {
            throw e;
        }

        try {
            switch(inputType) {
                case URL:
                    getXMLByURL(xmlSource, handler);
                    break;
                case STRING:
                    getXMLByString(xmlSource, handler);
            }
        } catch(Exception e) {
            throw e;
        }
    }

    public void getXMLByURL(String URL, DefaultHandler handler) 
        throws SAXException, MalformedURLException, IOException {

        try {
            this.reader.setContentHandler(handler);
            this.reader.parse(new InputSource(new URL(URL).openStream()));
        } catch(Exception e) {
            throw e;
        }
    }

    public void getXMLByString(String rawXML, DefaultHandler handler)
        throws SAXException, IOException {

        this.reader.setContentHandler(handler);
        
        try {
            this.reader.parse(new InputSource(new StringReader(rawXML)));
        } catch(IOException e) {
            throw e;
        } catch(SAXException e) {
            throw e;
        }
    }
}