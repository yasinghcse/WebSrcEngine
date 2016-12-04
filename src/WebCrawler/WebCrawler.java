package WebCrawler;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utilities.Util;


public class WebCrawler {
	
	// Creates a LIST (Queue) to maintain the list of links that will have to be visited
	private LinkedList<String> linksToVisit = new LinkedList<String>();
	
	// Creates a SET to maintain the list of links that were visited in order to guarantee no double visit
	private HashSet<String> visitedLinks = new HashSet<String>();

	// List of the Visited and Built Nodes
	private Collection<WebCrawlerNode> webCrawledNodes = new LinkedList<WebCrawlerNode>();
	
	private int MAX_URL_VISITS = 500;
	
	private int QtyQueuedLinks = 0;
	
	private boolean isPrintDebug = true;
	
	
	/**
	 * 	
	 * 
	 * @param maxURLVisits
	 */
	public WebCrawler(int maxURLVisits) {
		this.MAX_URL_VISITS = maxURLVisits;
	}

	
	/**
	 * 
	 * 
	 * @return
	 */
	public Collection<WebCrawlerNode> getWebCrawledNodes() {
		return webCrawledNodes;
	}

    
	/**
	 * 
	 * 
	 * @param urlToVisit
	 */
    public void buildWebCrawl(String url)  {
    	WebCrawlerNode crawlerNode = new WebCrawlerNode(url);
    	try {
	   		// Connects to the Provided URL to Visit
	    	Connection connectionToURL = Jsoup.connect(url);
	    	// Obtains the Document that in this case represents the HTML File
		    Document jSoupDoc = connectionToURL.get();
		    // Obtains all Tags of HREF type
		    Elements hreflinks = jSoupDoc.select("a[href]");
		    

	    	crawlerNode = new WebCrawlerNode(url);
	    	String fullHtmlTextFormat = jSoupDoc.text();
	    	StringTokenizer tokenizer = new StringTokenizer(fullHtmlTextFormat);
	    	
	    	// Tokenizing the TEXT from parsed from the HTML file 
			while (tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				// removing all Unwanted characters from String
				String cleanToken = token.replaceAll("[+.^:,?;!�()\n\r\"]","");
				crawlerNode.addTextContentToken(cleanToken);
			}

			// Saving all found links from HREF Tags in the HTML file
		    for (int i = 0; i < hreflinks.size(); i++) {
	    		Element link = hreflinks.get(i);
	    		crawlerNode.addNodeUrlLink(link.attr("abs:href"));
		    	if (QtyQueuedLinks <= this.MAX_URL_VISITS) {
			    	linksToVisit.add(link.attr("abs:href"));
			    	QtyQueuedLinks++;
			    	Util.printDebug(isPrintDebug, "####### ADDED link to visit: " + link.attr("abs:href") + "  Link index: " + QtyQueuedLinks);
		    	}
		    }
    	} catch (IOException ex) {
    		// A connection happened to the URL so it is considered to be a BROKEN URL
    		// In this case this NODE is marked as BAD it may not be considered while building the anything on this node.
    		crawlerNode.setBadURL(true);
    	}
    	
    	// Adding this NODE to lhe list of Crawled nodes
    	webCrawledNodes.add(crawlerNode);
    	
	    // Recursively visits the first Sub-URL that was Queued
	    if (!linksToVisit.isEmpty()) {
	    	String linkToVisit = null;
	    	do {
	    		linkToVisit = linksToVisit.removeFirst();
	    		if (visitedLinks.contains(linkToVisit)) {
	    			Util.printDebug(isPrintDebug, "!!!!!!!!!###### Already visited : " + linkToVisit);
	    		}
	    	} while (!linksToVisit.isEmpty() && visitedLinks.contains(linkToVisit));
	    	
	    	Util.printDebug(isPrintDebug, "Removing link from set: " + linkToVisit);
	    	Util.printDebug(isPrintDebug, "Number of links still to visit: " + linksToVisit.size());
	    	Util.printDebug(isPrintDebug, "Not visited yet : " + linkToVisit + " Will add to the visited list");
			visitedLinks.add(linkToVisit);
			Util.printDebug(isPrintDebug, ">>>>>>>>> Visiting recursevelly now: " + linkToVisit);
			buildWebCrawl(linkToVisit);
	    }
    }
    
}


//A crawler may only want to seek out HTML pages and avoid all other MIME types. In order to request only HTML resources, a crawler may make an HTTP HEAD request to determine a Web resource's MIME type before requesting the entire resource with a GET request. To avoid making numerous HEAD requests, a crawler may examine the URL and only request a resource if the URL ends with certain characters such as .html, .htm, .asp, .aspx, .php, .jsp, .jspx or a slash. This strategy may cause numerous HTML Web resources to be unintentionally skipped.