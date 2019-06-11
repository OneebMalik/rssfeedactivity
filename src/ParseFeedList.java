package dev.oneeb.rssfeedactivity;

import java.util.ArrayList;

public class ParseFeedList {

    private String jsonText;

    public ParseFeedList(String jsonText) {
        this.jsonText = jsonText;
    }

    public ArrayList<CompanyFeed> parseJson() {
        System.out.println("PARSED: " + this.jsonText);

        return null;
    }
}