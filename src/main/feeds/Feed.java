package dev.oneeb.rssfeedactivity;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Feed {

	// Required RSS channel elements (from RSS spec https://cyber.harvard.edu/rss/rss.html).
	protected String title;
	protected String link;
	protected String description;

	// Optional RSS channel elements (from RSS spec https://cyber.harvard.edu/rss/rss.html).
	protected LocalDateTime lastBuildDate;
	protected LocalDateTime pubDate;

	public Feed() {
		this.title = "";
		this.link = "";
		this.description = "";
		this.lastBuildDate = null;
		this.pubDate = null;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLastBuildDate(LocalDateTime lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public void setPubDate(LocalDateTime pubDate) {
		this.pubDate = pubDate;
	} 

	public String getTitle() {
		return this.title;
	}

	public String getLink() {
		return this.link;
	}

	public String getDescription() {
		return this.description;
	}

	public LocalDateTime getLastBuildDate() {
		return this.lastBuildDate;
	}

	public LocalDateTime getPubDate() {
		return this.pubDate;
	}

	@Override
	public String toString() {
		return "Feed: {\n\t title: " + this.title
			+ "\n\t link: " + this.link 
			+ "\n\t description: " + this.description 
			+ "\n\t lastBuildDate: " + this.lastBuildDate
			+ "\n\t pubDate: " + this.pubDate;
	}
}
