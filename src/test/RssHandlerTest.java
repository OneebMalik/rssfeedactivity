package dev.oneeb.rssfeedactivity;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.xml.sax.SAXException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


public class RssHandlerTest {

    private static RssHandler handler;

    @Test
    public void testValidRssSource()
        throws IOException, SAXException, ParserConfigurationException {

        handler = new RssHandler();
        FeedActivity feedActivity;

        XMLFetch xmlFetch = new XMLFetch(XMLInputType.URL, "https://atwar.blogs.nytimes.com/feed/", handler);

        feedActivity = new FeedActivity(handler.getFeed());

        assertFalse(feedActivity.feedWasActive(5));
        assertTrue(feedActivity.feedWasActive(612));
        assertTrue(feedActivity.feedWasActive(613));
    }

    @Test
    public void testInvalidRSSVersionFeed()
        throws IOException, SAXException, ParserConfigurationException {
        handler = new RssHandler();
        FeedActivity feedActivity;

        XMLFetch xmlFetch = new XMLFetch(XMLInputType.URL, "https://www.craigslist.org/about/best/all/index.rss", handler);

        assertNull(handler.getFeed());
    }
}