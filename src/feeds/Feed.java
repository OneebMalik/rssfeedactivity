package dev.oneeb.rssfeedactivity;

import java.util.List;
import java.util.ArrayList;

public class Feed {

	// Required RSS channel elements.
	private String title;
	private String link;
	private String description;

	private String lastBuildDate;

	public Feed() {
		this.title = "";
		this.link = "";
		this.description = "";
		this.lastBuildDate = "";
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

	public String getTitle() {
		return this.title;
	}

	public String getLink() {
		return this.link;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return "Feed: {\n\t title: " + this.title +
			"\n\t link: " + this.link + "\n\t description: " 
			+ this.description + "\n}";
	}
}
