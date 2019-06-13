package dev.oneeb.rssfeedactivity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


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

    /**
     * Check whether company feed was active in the past passed in days.
     */
    public boolean feedWasActive(int days) {

        LocalDateTime activeDate = this.lastBuildDate != null ? this.lastBuildDate : this.pubDate;
        LocalDateTime currentDate = LocalDateTime.now();

        long daysSinceActive = ChronoUnit.DAYS.between(activeDate.toLocalDate(), currentDate.toLocalDate());

        return daysSinceActive <= days ? true : false;
    }

}