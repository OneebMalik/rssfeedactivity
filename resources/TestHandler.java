package dev.oneeb.rssfeedactivity;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class TestHandler extends DefaultHandler {

    @Override
    public void startElement(String uri, String localName,
        String qName, Attributes attributes) throws SAXException {
            return;
    }

    @Override
    public void characters(char[] ch, int start, int length) 
        throws SAXException {
            return;
    }

    @Override
    public void endElement(String uri, String localName, String qName)
    throws SAXException {
        return;
    }
}