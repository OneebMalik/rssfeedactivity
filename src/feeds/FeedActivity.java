package dev.oneeb.rssfeedactivity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

public class FeedActivity extends Feed {
    
    public FeedActivity() {
        super();
    }

    public FeedActivity(Feed feed) {
        this.title = feed.title;
		this.link = feed.link;
		this.description = feed.description;
		this.lastBuildDate = feed.lastBuildDate;
		this.pubDate = feed.pubDate;
    }

    public boolean feedWasActive(int days) {

        LocalDate activeDate = this.lastBuildDate != null ? this.lastBuildDate : this.pubDate;

        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(activeDate, currentDate);

        int daysSinceActive = period.getDays();

        return daysSinceActive <= days ? true : false;
    }

}