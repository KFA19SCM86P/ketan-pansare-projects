import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SoundList")

public class SoundList extends HttpServlet {

	/* Trending Page Displays all the Tablets and their Information in Game Speed */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HashMap<String,Sound> allsounds = new HashMap<String,Sound> ();


		
		try{
		     allsounds = MySqlDataStoreUtilities.getSounds();
		}
		catch(Exception e)
		{
			
		}

	/* Checks the Tablets type whether it is microsft or apple or samsung */

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Sound> hm = new HashMap<String, Sound>();

		if (CategoryName == null)	
		{
			hm.putAll(allsounds);
			name = "";
		} 
		else 
		{
			if(CategoryName.equals("bose")) 
			{	
				for(Map.Entry<String,Sound> entry : allsounds.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Bose"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="Bose";
			} 
			else if(CategoryName.equals("jbl")) 
			{	
				for(Map.Entry<String,Sound> entry : allsounds.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("JBL"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="JBL";
			} 
			
			else if(CategoryName.equals("sonos")) 
			{	
				for(Map.Entry<String,Sound> entry : allsounds.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Sonos"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="Sonos";
			} 
			
			else if(CategoryName.equals("philips")) 
			{	
				for(Map.Entry<String,Sound> entry : allsounds.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Philips"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="Philips";
			} 
			
			
			
	    }

		/* Header, Left Navigation Bar are Printed.

		All the tablets and tablet information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + name + " Sound Systems</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Sound> entry : hm.entrySet()) {
			Sound Sound = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + Sound.getName() + "</h3>");
			pw.print("<strong>" + Sound.getPrice() + "$</strong><ul>");
			pw.print("<li id='item'><img src='images/sounds/"
					+ Sound.getImage() + "' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='sounds'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='sounds'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+Sound.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='sounds'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
