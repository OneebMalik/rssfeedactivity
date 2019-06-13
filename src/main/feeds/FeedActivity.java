package dev.oneeb.rssfeedactivity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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

        LocalDateTime activeDate = this.lastBuildDate != null ? this.lastBuildDate : this.pubDate;

        LocalDateTime currentDate = LocalDateTime.now();
        Period period = Period.between(activeDate.toLocalDate(), currentDate.toLocalDate());

        int daysSinceActive = period.getDays();

        return daysSinceActive <= days ? true : false;
    }

}