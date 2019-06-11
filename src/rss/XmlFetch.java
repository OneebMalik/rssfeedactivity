package dev.oneeb.rssfeedactivity;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.*;
import java.io.*;

public class XmlFetch {

    public XmlFetch(String url, DefaultHandler handler) {
        
        XMLReader reader = null;

        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(new URL(url).openStream()));
        } catch(Exception e) {
            System.err.println(e);
        }
    }

}