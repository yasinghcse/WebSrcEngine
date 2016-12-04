package WebCrawler;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;

import resources.InvertedIndex;
import utilities.Util;



public class WebCrawlerManager {
	
	private static final String FILE_PREFIX = "WebCrawlerNodes";
	private static final String FILE_TYPE = ".ser";

	/**
	 * 
	 * 
	 * @param groupName
	 * @param nodes
	 * @return
	 * @throws IOException
	 */
	public static boolean saveWebCrawlerNode (String groupName, Collection<WebCrawlerNode> nodes) throws IOException {
		boolean saved = false; 
		FileOutputStream fileOut = new FileOutputStream(FILE_PREFIX + groupName + FILE_TYPE);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(nodes);
		out.close();
		fileOut.close();
		saved = true;
		Util.printDebug(true, "Serialization Done at: " + fileOut.toString());
		return saved;
	}
   
   
   /**
    * 
    * 
    * 
    * 
    * @param groupName
    * @return
    */
   public static Collection<WebCrawlerNode> loadWebCrawlerNodes (String groupName) throws IOException, ClassNotFoundException {
	   Collection<WebCrawlerNode> nodes = null;
	   FileInputStream fileIn = new FileInputStream(FILE_PREFIX + groupName + FILE_TYPE);
	   ObjectInputStream in = new ObjectInputStream(fileIn);
	   nodes = (Collection<WebCrawlerNode>) in.readObject();
	   in.close();
	   fileIn.close();    	  
       return nodes;
    }   

   
	/**
	 * 
	 * 
	 * @param args
	 * @throws InvalidURLException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		/**
		 *  Create an object WebCrawler and build  a list of LInks size N times based on the Constructor object
		 *  These LInks will be visited, their HTML will be parsed to TEXT and saved as tokens.
		 *  And then will QUEUE all links of this page. The limit of pages QUEUED is N
		 *  No link is visited Twice, it is guaranteed by a HashSet of links
		 */
		WebCrawler webCrawler = new WebCrawler(10);
		// Starts Crawling links based on a URL, in this case the W3G
//		webCrawler.buildWebCrawl("https://www.w3.org/");
//		webCrawler.buildWebCrawl("http://luisrueda.cs.uwindsor.ca/");
		webCrawler.buildWebCrawl("https://en.wikipedia.org/wiki/Main_Page");
		// Final number of nodes, that means, number of links and its text parsed into Tokens
		System.out.println("List size : " + webCrawler.getWebCrawledNodes().size());
//		Collection<WebCrawlerNode> nodesMemory = webCrawler.getWebCrawledNode();
//		Iterator<WebCrawlerNode> it = nodesMemory.iterator();
//		// Navigating through all Nodes		
//		System.out.println("############## Nodes from memory #########################");
//		while(it.hasNext()) {
//			WebCrawlerNode node = it.next();
//			System.out.println("URL from visited NODE ===> " + node.getNodeBaseUrl());
//			System.out.println("TXT Tokens from visited NODE ===> " + node.getTextContentsTokens());
//		}

		// This saves (DUMPS) the Collections of Nodes into a Files so lated it may be directly loaded without WebCrawling again
		System.out.println(">>>>>>>>>> Will save Nodes to file!");
//		WebCrawlerManager.saveWebCrawlerNode("luisrueda1000", webCrawler.getWebCrawledNode());
		//WebCrawlerManager.saveWebCrawlerNode("-Wikipedia1000", webCrawler.getWebCrawledNodes());
		
		//*******************YADI***********
		InvertedIndex obj = new InvertedIndex();
		obj.updatedloadData(webCrawler.getWebCrawledNodes());
		//obj.getAllInvertedIndexList();
		System.out.println("Top 10 links");
		//System.out.println(obj.search("Geography"));
		for( String s:obj.findCorrection("Geography")){
			System.out.println(s);
		}
		//*******************YADI***********
		System.out.println("############## Nodes save to file! #########################");
		
		
		// This loads the Collections of Nodes from serialized Files directly into memmory
//		System.out.println("Now will load from file ...........");
//		Collection<WebCrawlerNode> nodesSaved = WebCrawlerManager.loadWebCrawlerNodes("luisrueda1000");
//		System.out.println("############## Nodes from file  #########################");
//		Iterator<WebCrawlerNode> it2 = nodesSaved.iterator();
//		while(it2.hasNext()) {
//			WebCrawlerNode node2 = it2.next();
//			System.out.println("URL from visited NODE ===> " + node2.getNodeBaseUrl());
//			System.out.println("TXT Tokens from visited NODE ===> " + node2.getTextContentsTokens());
//		}
   }
   
}

