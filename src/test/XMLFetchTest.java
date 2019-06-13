package dev.oneeb.rssfeedactivity;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class XMLFetchTest {

    private static TestHandler testHandler;

    @BeforeClass
    public static void constructTestHandler() {
        testHandler = new TestHandler();
    }

    @Test(expected = MalformedURLException.class)
    public void testClassConstructorMalformedURLException()
        throws MalformedURLException, SAXException, ParserConfigurationException, IOException {
        
        XMLFetch xmlFetch = new XMLFetch(XMLInputType.URL, "haap://wrongurl.test", testHandler);
    }

    @Test(expected = SAXException.class)
    public void testClassConstructorInvalidXMLString()
        throws MalformedURLException, SAXException, ParserConfigurationException, IOException {
        
        XMLFetch xmlFetch = new XMLFetch(XMLInputType.STRING, "invalid xml", testHandler);
    }

    @Test(expected = SAXException.class)
    public void testClassContructorValidURLNoXMLContent()
        throws MalformedURLException, SAXException, ParserConfigurationException, IOException {

        XMLFetch xmlFetch = new XMLFetch(XMLInputType.URL, "https://google.com", testHandler);
    }
}