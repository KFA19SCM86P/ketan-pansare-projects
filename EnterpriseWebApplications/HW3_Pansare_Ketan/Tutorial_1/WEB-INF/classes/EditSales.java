import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.*;
import java.io.*;

@WebServlet("/EditSales")

public class EditSales extends HttpServlet {	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		


		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		Utilities utility = new Utilities(request, pw);
		String id = request.getParameter("id");
		String uname = request.getParameter("uname");
		String pname = request.getParameter("pname");
		String date = request.getParameter("date");
		
		
		/*if(category.equals("tv")){
			Tv tv = new Tv();
			tv.setId(id);
			tv.setName(name);
			tv.setPrice(Double.parseDouble(price));
			tv.setImage(image);
			tv.setRetailer(maker);
			tv.setCondition(condition);
			tv.setDiscount(Double.parseDouble(discount));
			
			SaxParserDataStore.tvs.put(id,tv);
			
			
			HashMap<String, Tv> hm=new HashMap<String, Tv>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
			
			try
			{
 			 FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\AddedTv.txt"));
			 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
			 hm= (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
				
			}
			 
			 hm.put(id,tv);
			 
			 FileOutputStream fileOutputStream = new FileOutputStream(TOMCAT_HOME+"\\webapps\\Tutorial_1\\AddedTv.txt");
			 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			 objectOutputStream.writeObject(hm);
			 objectOutputStream.flush();
			 objectOutputStream.close();       
			 fileOutputStream.close();
			
			
		}*/
		
		MySqlDataStoreUtilities.editSales(id,uname,pname,date);
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Status</a>");
		pw.print("<h2> Edited Order with ID  :"+id+"</h2>");
		pw.print("</div></div>");		
		utility.printHtml("Footer.html");
		
		
		//displayCart(request, response);
		
	}
}
