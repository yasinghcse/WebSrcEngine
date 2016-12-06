package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import WebCrawler.WebCrawlerManager;
import WebCrawler.WebCrawlerNode;
import resources.InvertedIndex;

/**
 * Servlet implementation class FirstEntry
 */
@WebServlet("/WebSrcController")
public class WebSrcController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String CRAWLER_NODES_FILE = "LuisRueda2000URL";
	
	// Inverted Index for Web Search
	InvertedIndex invertedIndexEngine;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WebSrcController() {
		super();
	}

	
    /**
     *  simply creates or loads some data that will be used throughout the life of the servlet
     * 
     */
    public void init() throws ServletException {
    	
		Collection<WebCrawlerNode> nodesSaved = null;
		try {
			System.out.println("### DEBUG - Servlet Initialization ###");
			System.out.println("### DEBUG - Will try to load WebCrawler Serialized file.");
			nodesSaved = (Collection<WebCrawlerNode>)WebCrawlerManager.loadSerializedObject(CRAWLER_NODES_FILE, "LinkedList");
			System.out.println("### DEBUG - WebCrawler Serialized file loaded Successfully");			
			System.out.println("### DEBUG - Will instantiate INVERTED INDEX Search Structure");
			invertedIndexEngine = new InvertedIndex();
			System.out.println("### DEBUG - INVERTED INDEX Search Structure Instantiated");
			invertedIndexEngine.updatedloadData(nodesSaved);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
    }	
	
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		// TO check if InvertedIndex was instantiated 
		if (invertedIndexEngine != null) {
			System.out.println("### DEBUG => Inverted Index loaded and instantianted");
		} else {
			System.out.println("### DEBUG => Inverted Index IS NULL");
		}

		if (request.getParameter("act") != null) {
			String actValue = request.getParameter("act");
			System.out.println("### DEBUG => ACT = " + actValue);
			if (actValue.equals("prefix")) {
				if (request.getParameter("prefix") != null) {
					String prefixValue = request.getParameter("prefix");
					System.out.println("### DEBUG Prefix = " + prefixValue.length());
					if (prefixValue.length() != 0) {
						JSONArray obj = new JSONArray();
						if (invertedIndexEngine.guessWord(prefixValue) != null) {
							for (String s : invertedIndexEngine.guessWord(prefixValue)) {
								obj.add(s);
							}
							System.out.println("Returnung " + invertedIndexEngine.guessWord(prefixValue));
						}
						response.getWriter().print(obj);
					}
				}
			} else if (actValue.equals("getTopUrl")) {
				System.out.println("### DEBUG => ACT = " + actValue);
				String prefixValue = request.getParameter("prefix");
				System.out.println("### DEBUG prefix = " + prefixValue);
				if (prefixValue != null) {
					System.out.println("### DEBUG prefix = " + prefixValue.length());
					if (prefixValue.length() != 0) {
						ArrayList<String> e = new ArrayList<String>();
						JSONArray obj = new JSONArray();
						if (invertedIndexEngine.getTopUrls(prefixValue) != null) {
							int i = 0;
							for (String s : invertedIndexEngine.getTopUrls(prefixValue)) {
								if (s != null) {
									System.out.println("### DEBUG => getTopUrl = " + i + " => " + s);
									i++;
									obj.add(s);
								}
							}
						}
						response.getWriter().print(obj);
					}
				}
			} else if (actValue.equals("getCorWord")) {
				System.out.println("### DEBUG => ACT = " + actValue);
				String prefixValue = request.getParameter("prefix");
				System.out.println("### DEBUG prefix = " + prefixValue.length());
				if (prefixValue != null) {
					if (prefixValue.length() != 0) {
						ArrayList<String> e = new ArrayList<String>();
						JSONArray obj = new JSONArray();
						if (invertedIndexEngine.findCorrection(prefixValue) != null) {
							for (String s : invertedIndexEngine.findCorrection(prefixValue)) {
								if (s != null) {
									obj.add(s);
								}
							}
						}
						response.getWriter().print(obj);
					}
				}
			}
		}
		response.getWriter().print("");
	}	
	

//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doGetOriginal(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.setContentType("text/html");
//		
//		if (invertedIndexEngine != null) {
//			System.out.println("Inverted Index loaded and instantianted");
//		} else {
//			System.out.println("Inverted Index NULL!!");
//		}
//
//		if (request.getParameter("act") != null) {
//			System.out.println("### DEBUG => ACT = " + request.getParameter("act"));
//			System.out.println("act =" + request.getParameter("act"));
//			if (request.getParameter("act").equals("prefix")) {
//				if (request.getParameter("prefix") != null) {
//					System.out.println("prefix = " + request.getParameter("prefix").length());
//					if (request.getParameter("prefix").length() != 0) {
//						InvertedIndex t = new InvertedIndex();
//
//						ArrayList<String> e = new ArrayList<String>();
//						String url1 = "www.test.com";
//						String url2 = "www.test2.com";
//						String url3 = "www.test3.com";
//						String url4 = "www.test4.com";
//						String url5 = "www.test5.com";
//						String url6 = "www.test6.com";
//						e.add("hello");
//						e.add("hello1");
//						e.add("hello2");
//						e.add("hello3");
//						e.add("hello4");
//						e.add("hello5");
//						e.add("hello6");
//						e.add("hello7");
//						e.add("hello8");
//						e.add("hello9");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hen");
//						e.add("hens");
//						e.add("hell");
//						t.loadData(e, url1);
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						t.loadData(e, url2);
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						t.loadData(e, url3);
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						t.loadData(e, url4);
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello11");
//						t.loadData(e, url5);
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						e.add("hello10");
//						t.loadData(e, url6);
//						JSONArray obj = new JSONArray();
//						if (t.guessWord(request.getParameter("prefix")) != null) {
//							for (String s : t.guessWord(request.getParameter("prefix"))) {
//								obj.add(s);
//							}
//							System.out.println("Returnung " + t.guessWord(request.getParameter("prefix")));
//						}
//						response.getWriter().print(obj);
//					}
//				}
//			} else if (request.getParameter("act").equals("getTopUrl")) {
//				System.out.println("prefix = " + request.getParameter("prefix").length());
//				if (request.getParameter("prefix").length() != 0) {
//					InvertedIndex t = new InvertedIndex();
//
//					ArrayList<String> e = new ArrayList<String>();
//					String url1 = "www.test.com";
//					String url2 = "www.test2.com";
//					String url3 = "www.test3.com";
//					String url4 = "www.test4.com";
//					String url5 = "www.test5.com";
//					String url6 = "www.test6.com";
//					e.add("hello");
//					e.add("hello1");
//					e.add("hello2");
//					e.add("hello3");
//					e.add("hello4");
//					e.add("hello5");
//					e.add("hello6");
//					e.add("hello7");
//					e.add("hello8");
//					e.add("hello9");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//
//					t.loadData(e, url1);
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					t.loadData(e, url2);
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					t.loadData(e, url3);
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					t.loadData(e, url4);
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello11");
//					t.loadData(e, url5);
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hen");
//					e.add("hens");
//					e.add("hell");
//					t.loadData(e, url6);
//					JSONArray obj = new JSONArray();
//					if (t.getTopUrls(request.getParameter("prefix")) != null) {
//						for (String s : t.getTopUrls(request.getParameter("prefix"))) {
//							if (s != null) {
//								obj.add(s);
//							}
//						}
//					}
//					response.getWriter().print(obj);
//				}
//			}
//			else if (request.getParameter("act").equals("getCorWord")) {
//				System.out.println("prefix = " + request.getParameter("prefix").length());
//				if (request.getParameter("prefix").length() != 0) {
//					InvertedIndex t = new InvertedIndex();
//
//					ArrayList<String> e = new ArrayList<String>();
//					String url1 = "www.test.com";
//					String url2 = "www.test2.com";
//					String url3 = "www.test3.com";
//					String url4 = "www.test4.com";
//					String url5 = "www.test5.com";
//					String url6 = "www.test6.com";
//					e.add("hello");
//					e.add("hello1");
//					e.add("hello2");
//					e.add("hello3");
//					e.add("hello4");
//					e.add("hello5");
//					e.add("hello6");
//					e.add("hello7");
//					e.add("hello8");
//					e.add("hello9");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//
//					t.loadData(e, url1);
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					t.loadData(e, url2);
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					t.loadData(e, url3);
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					t.loadData(e, url4);
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello11");
//					t.loadData(e, url5);
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hello10");
//					e.add("hen");
//					e.add("hens");
//					e.add("hell");
//					t.loadData(e, url6);
//					JSONArray obj = new JSONArray();
//					if (t.findCorrection(request.getParameter("prefix")) != null) {
//						for (String s : t.findCorrection(request.getParameter("prefix"))) {
//							if (s != null) {
//								obj.add(s);
//							}
//						}
//					}
//					response.getWriter().print(obj);
//				}
//			}
//		}
//		response.getWriter().print("");
//	}
	
	public static void main (String[] args) {
		Collection<WebCrawlerNode> nodesSaved = null;
		try {
			System.out.println("### DEBUG - Servlet Initialization ###");
			System.out.println("### DEBUG - Will try to load WebCrawler Serialized file.");
			nodesSaved = (Collection<WebCrawlerNode>)WebCrawlerManager.loadSerializedObject("luis", "LinkedList");
			System.out.println("### DEBUG - WebCrawler Serialized file loaded Successfully");			
			System.out.println("### DEBUG - Will instantiate INVERTED INDEX Search Structure");
			InvertedIndex invertedIndexEngine = new InvertedIndex();
			System.out.println("### DEBUG - INVERTED INDEX Search Structure Instantiated");
			invertedIndexEngine.updatedloadData(nodesSaved);
			System.out.println("### DEBUG - INVERTED INDEX Will be saved to file");
			WebCrawlerManager.saveSerializableObject("InvertedIdxIluisRueda", invertedIndexEngine);			
			System.out.println("### DEBUG - INVERTED INDEX saved to file");			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}		
	}
	

}
