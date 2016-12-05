package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import resources.InvertedIndex;

/**
 * Servlet implementation class FirstEntry
 */
@WebServlet("/WebSrcController")
public class WebSrcController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WebSrcController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		System.out.println("hello");

		if (request.getParameter("act") != null) {
			System.out.println("we got it");
			System.out.println("act =" + request.getParameter("act"));
			if (request.getParameter("act").equals("prefix")) {
				if (request.getParameter("prefix") != null) {
					System.out.println("prefix = " + request.getParameter("prefix").length());
					if (request.getParameter("prefix").length() != 0) {
						InvertedIndex t = new InvertedIndex();

						ArrayList<String> e = new ArrayList<String>();
						String url1 = "www.test.com";
						String url2 = "www.test2.com";
						String url3 = "www.test3.com";
						String url4 = "www.test4.com";
						String url5 = "www.test5.com";
						String url6 = "www.test6.com";
						e.add("hello");
						e.add("hello1");
						e.add("hello2");
						e.add("hello3");
						e.add("hello4");
						e.add("hello5");
						e.add("hello6");
						e.add("hello7");
						e.add("hello8");
						e.add("hello9");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hen");
						e.add("hens");
						e.add("hell");
						t.loadData(e, url1);
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						t.loadData(e, url2);
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						t.loadData(e, url3);
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						t.loadData(e, url4);
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello11");
						t.loadData(e, url5);
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						e.add("hello10");
						t.loadData(e, url6);
						JSONArray obj = new JSONArray();
						if (t.guessWord(request.getParameter("prefix")) != null) {
							for (String s : t.guessWord(request.getParameter("prefix"))) {
								obj.add(s);
							}
							System.out.println("Returnung " + t.guessWord(request.getParameter("prefix")));
						}
						response.getWriter().print(obj);
					}
				}
			}
			else if (request.getParameter("act").equals("getTopUrl")){
				System.out.println("prefix = " + request.getParameter("prefix").length());
				if (request.getParameter("prefix").length() != 0) {
					InvertedIndex t = new InvertedIndex();

					ArrayList<String> e = new ArrayList<String>();
					String url1 = "www.test.com";
					String url2 = "www.test2.com";
					String url3 = "www.test3.com";
					String url4 = "www.test4.com";
					String url5 = "www.test5.com";
					String url6 = "www.test6.com";
					e.add("hello");
					e.add("hello1");
					e.add("hello2");
					e.add("hello3");
					e.add("hello4");
					e.add("hello5");
					e.add("hello6");
					e.add("hello7");
					e.add("hello8");
					e.add("hello9");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
				
					t.loadData(e, url1);
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					t.loadData(e, url2);
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					t.loadData(e, url3);
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					t.loadData(e, url4);
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello11");
					t.loadData(e, url5);
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hello10");
					e.add("hen");
					e.add("hens");
					e.add("hell");
					t.loadData(e, url6);
					JSONArray obj = new JSONArray();
					if (t.getTopUrls(request.getParameter("prefix")) != null) {
						for (String s : t.getTopUrls(request.getParameter("prefix"))) {
							obj.add(s);
						}
						System.out.println("Returnung " + t.guessWord(request.getParameter("prefix")));
					}
					response.getWriter().print(obj);
				}
			}
		}
		response.getWriter().print("");
	}

}
