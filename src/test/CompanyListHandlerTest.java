package dev.oneeb.rssfeedactivity;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.xml.sax.SAXException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


public class CompanyListHandlerTest {

    private static CompanyListHandler handler;

    @BeforeClass
    public static void buildCompanyListHandler() {
        handler = new CompanyListHandler();
    }

    @Test
    public void testValidXMLFileString()
        throws IOException, SAXException, ParserConfigurationException {

        XMLFetch xmlFetch = new XMLFetch(XMLInputType.STRING, 
            new String(Files.readAllBytes(Paths.get("resources/validCompanyList.xml"))), 
            handler);
    }

    @Test(expected = SAXException.class)
    public void testInvalidXMLFileString()
        throws IOException, SAXException, ParserConfigurationException {

        XMLFetch xmlFetch = new XMLFetch(XMLInputType.STRING, 
            new String(Files.readAllBytes(Paths.get("resources/invalidCompanyList.xml"))), 
            handler);
    }
}