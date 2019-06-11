package dev.oneeb.rssfeedactivity;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Feed {

	// Required RSS channel elements (from RSS spec https://cyber.harvard.edu/rss/rss.html).
	private String title;
	private String link;
	private String description;

	// Optional RSS channel elements (from RSS spec https://cyber.harvard.edu/rss/rss.html).
	private Date lastBuildDate;
	private Date pubDate;

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

	public void setLastBuildDate(Date lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public void setPubDate(Date pubDate) {
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

	public Date getLastBuildDate() {
		return this.lastBuildDate;
	}

	public Date getPubDate() {
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
