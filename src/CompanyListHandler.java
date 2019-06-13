package dev.oneeb.rssfeedactivity;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CompanyListHandler extends DefaultHandler {

    private Map<String, List<String>> parsedCompanyList;
    private List<String> companyUrls;

    private String currentCompany;
    private String currentElement;

    @Override
    public void startElement(String uri, String localName,
        String qName, Attributes attributes) throws SAXException {

        if (qName.equals("list")) {
            this.currentElement = "list";
            this.parsedCompanyList = new HashMap<String, List<String>>();
        }
        
        if (qName.equals("company")) {
            this.currentElement = "company";
            this.currentCompany = attributes.getValue(0);

            this.companyUrls = new ArrayList<String>();

            this.parsedCompanyList.put(this.currentCompany, this.companyUrls);
        }

        if (qName.equals("url")) {
            this.currentElement = "url";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) 
        throws SAXException {

        String value = new String(ch, start, length).trim();

        if (this.currentElement.equals("url")) {
            this.companyUrls.add(value);
        } 
    }

    @Override
    public void endElement(String uri, String localName, String qName)
    throws SAXException {

        this.currentElement = "";
    }

    public Map<String, List<String>> getParsedCompanyList() {
        return this.parsedCompanyList;
    }
}