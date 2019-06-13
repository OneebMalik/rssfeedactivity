package dev.oneeb.rssfeedactivity;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;

public class FeedTest {

    @Test 
    public void testInitialClassPropertyValues() {
        Feed feed = new Feed();

        assertEquals(feed.title, "");
        assertEquals(feed.link, "");
        assertEquals(feed.description, "");

        assertNull(feed.lastBuildDate);
        assertNull(feed.pubDate);
    }

    @Test
    public void testStringClassMethods() {
        Feed feed = new Feed();
        
        feed.setTitle("Test Title");
        feed.setLink("Test Link");
        feed.setDescription("Test Description");
        

        assertEquals(feed.getTitle(), "Test Title");
        assertEquals(feed.getLink(), "Test Link");
        assertEquals(feed.getDescription(), "Test Description");
    }

    @Test
    public void testDateClassMethods() {
        Feed feed = new Feed();

        LocalDateTime now = LocalDateTime.now();

        feed.setLastBuildDate(now);
        feed.setPubDate(now);

        assertEquals(feed.getLastBuildDate(), now);
        assertEquals(feed.getPubDate(), now);

        LocalDateTime nowAgain = LocalDateTime.now();

        assertNotEquals(feed.getLastBuildDate(), nowAgain);
        assertNotEquals(feed.getPubDate(), nowAgain);
    }

    @Test
    public void testToString() {
        Feed feed = new Feed();
        
        feed.setTitle("Test Title");
        feed.setLink("Test Link");
        feed.setDescription("Test Description");

        LocalDateTime now = LocalDateTime.now();

        feed.setLastBuildDate(now);
        feed.setPubDate(now);

        String testString = "Feed: {\n\t title: Test Title"
        + "\n\t link: Test Link"
        + "\n\t description: Test Description" 
        + "\n\t lastBuildDate: " + now
        + "\n\t pubDate: " + now;

        assertEquals(feed.toString(), testString);
    }
}