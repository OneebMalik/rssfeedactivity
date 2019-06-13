package dev.oneeb.rssfeedactivity;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;

public class FeedActivityTest {

    @Test
    public void testInitialClassPropertyValues() {
        FeedActivity feed = new FeedActivity();

        assertEquals(feed.title, "");
        assertEquals(feed.link, "");
        assertEquals(feed.description, "");

        assertNull(feed.lastBuildDate);
        assertNull(feed.pubDate);
    }

    @Test
    public void testStringClassPropertyMethods() {
        FeedActivity feed = new FeedActivity();
        
        feed.setTitle("Test Title");
        feed.setLink("Test Link");
        feed.setDescription("Test Description");
        

        assertEquals(feed.getTitle(), "Test Title");
        assertEquals(feed.getLink(), "Test Link");
        assertEquals(feed.getDescription(), "Test Description");
    }

    @Test
    public void testDateClassPropertyMethods() {
        FeedActivity feed = new FeedActivity();

        LocalDateTime now = LocalDateTime.now();

        feed.setLastBuildDate(now);
        feed.setPubDate(now);

        assertEquals(feed.getLastBuildDate(), now);
        assertEquals(feed.getPubDate(), now);

        LocalDateTime nowAgain = LocalDateTime.now();

        assertNotEquals(feed.getLastBuildDate(), nowAgain);
        assertNotEquals(feed.getPubDate(), nowAgain);
    }
}