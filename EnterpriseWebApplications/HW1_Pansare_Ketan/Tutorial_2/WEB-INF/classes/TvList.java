import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.InputStreamReader;

@WebServlet("/TvList")

public class TvList extends HttpServlet {

	/* Tv Page Displays all the tvs and their Information in Best Deals */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        

		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, Tv> hm = new HashMap<String, Tv>();
		HashMap<String, Tv> newprods = new HashMap<String, Tv>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{
 			 FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\AddedTv.txt"));
			 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
			 newprods= (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
				
			}
			
		   HashMap<String, Tv> sp =SaxParserDataStore.tvs;
		   sp.putAll(newprods);
		   
		if(CategoryName==null){
			hm.putAll(sp);
			name = "";
		}
		else
		{	
			
		   
		   if(CategoryName.equals("toshiba"))
		   {
			 for(Map.Entry<String,Tv> entry : sp.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Toshiba"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Toshiba";
		   }
		   else if(CategoryName.equals("sony"))
		    {
			for(Map.Entry<String,Tv> entry : sp.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Sony"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Sony";
			}
			else if(CategoryName.equals("samsung"))
			{
				for(Map.Entry<String,Tv> entry : sp.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Samsung"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Samsung";
			}
			else if(CategoryName.equals("philips"))
			{
				for(Map.Entry<String,Tv> entry : sp.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Philips"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Philips";
			}
			else if(CategoryName.equals("mi"))
			{
				for(Map.Entry<String,Tv> entry : sp.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("MI"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "MI";
			}
		}

		
		/* Header, Left Navigation Bar are Printed.

		All the Tv and Tv information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Tvs</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Tv> entry : hm.entrySet())
		{
			Tv tv = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+tv.getName()+"</h3>");
			pw.print("<strong>Price :$"+tv.getPrice()+"</strong><ul>");
			pw.print("<strong>Discount : "+tv.getDiscount()+" %</strong><ul>");
			
			
			pw.print("<li id='item'><img src='images/tvs/"+tv.getImage()+"' alt='' /></li>");
			
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tvs'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tvs'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tvs'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
		
	}
}
