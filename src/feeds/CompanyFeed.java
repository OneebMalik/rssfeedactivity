package dev.oneeb.rssfeedactivity;

import java.util.ArrayList;
import java.util.List;

public class CompanyFeed {

    private String companyTitle;
    private List<String> feedUrls;

    public CompanyFeed() {
        this.companyTitle = "";
        this.feedUrls = new ArrayList<>();
    }

    public String getCompanyTitle() {
        return this.companyTitle;
    }

    public List<String> getFeedUrls() {
        return this.feedUrls;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public void setFeedUrls(List<String> feedUrls) {
        this.feedUrls = feedUrls;
    }

    public void addFeedUrl(String url) {
        this.feedUrls.add(url);
    }
}