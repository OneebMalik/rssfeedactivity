package dev.oneeb.rssfeedactivity;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CompanyListHandler extends DefaultHandler {

    private String xmlText;
    private List<Map<String, List<String>>> parsedCompanyList; 
    private Map<String, List<String>> companyItem;
 
    @Override
    public void startDocument() throws SAXException {
        parseCompanyList = new ArrayList<HashMap<String, ArrayList<String>>>();
    }

    public Map<String, List<String>> getParsedCompanyList() {
        return null;
    }
}