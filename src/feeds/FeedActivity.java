package dev.oneeb.rssfeedactivity;

import java.text.SimpleDateFormat;
import java.time.*;

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
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(this.lastBuildDate.toLocalDate(), currentDate);

        int daysSinceActive = period.getDays();

        return daysSinceActive <= days ? true : false;
    }

}