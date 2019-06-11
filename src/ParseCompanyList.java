package dev.oneeb.rssfeedactivity;

import java.util.ArrayList;

public class ParseCompanyList {

    private String jsonText;

    public ParseCompanyList(String jsonText) {
        this.jsonText = jsonText;
    }

    public ArrayList<CompanyFeed> parseJson() {
        System.out.println("PARSED: " + this.jsonText);

        return null;
    }
}