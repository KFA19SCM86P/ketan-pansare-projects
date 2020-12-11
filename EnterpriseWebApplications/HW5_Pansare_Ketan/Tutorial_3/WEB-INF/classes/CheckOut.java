import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities Utility = new Utilities(request, pw);
		storeOrders(request, response);
	}
	
	protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try
        {
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
        HttpSession session=request.getSession(); 

		//get the order product details	on clicking submit the form will be passed to submitorder page	
		
	    String userName = session.getAttribute("username").toString();
        String orderTotal = request.getParameter("orderTotal");
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='CheckOut' action='Payment' method='post'>");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
		pw.print(userName);
		pw.print("</td></tr>");
		// for each order iterate and display the order name price
		for (OrderItem oi : utility.getCustomerOrders()) 
		{
			pw.print("<tr><td> Product Purchased:</td><td>");
			pw.print(oi.getName()+"</td></tr><tr><td>");
			pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
			pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
			pw.print("Product Price:</td><td>"+ oi.getPrice());
			pw.print("</td></tr>");
		}
		pw.print("<tr><td>");
        pw.print("Total Order Cost</td><td>"+orderTotal);
		pw.print("<input type='hidden' name='orderTotal' value='"+orderTotal+"'>");
		pw.print("</td></tr></table><table><tr></tr><tr></tr>");	
		pw.print("<tr><td>");
     	pw.print("Credit/accountNo</td>");
		pw.print("<td><input type='text' name='creditCardNo'>");
		pw.print("</td></tr>");
		pw.print("<tr><td>");
     	pw.print("Customer Name</td>");
		pw.print("<td><input type='text' name='customerName'>");
		pw.print("</td></tr>");
		pw.print("<tr><td>");
     	pw.print("Customer Age</td>");
		pw.print("<td><input type='text' name='customerAge'>");
		pw.print("</td></tr>");
		pw.print("<tr><td>");
		pw.print("City</td>");
		pw.print("<td><input type='text' name='customerCity'>");
		pw.print("</td></tr>");
		pw.print("<tr><td>");
		pw.print("Street</td>");
		pw.print("<td><input type='text' name='customerStreet'>");
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("Zipcode</td>");
		pw.print("<td><input type='text' name='customerZip'>");
		pw.print("</td></tr>");

		pw.print("<tr><td>");
	    pw.print("Customer Address</td>");
		pw.print("<td><input type='text' name='userAddress'>");
        pw.print("</td></tr>");

        pw.print("<tr><td>");
	    pw.print("Customer Occupation</td>");
		pw.print("<td><input type='text' name='customerOccupation'>");
        pw.print("</td></tr>");

        pw.print("<tr><td>");
	    pw.print("Delivery type</td>");
		pw.print("<td><input type='radio' id='home' name='deliveryType' value='home'>"+
  		"<label for='home'>Home Delivery</label><br>"+
  		"<input type='radio' id='store' name='deliveryType' value='store'>"+
  		"<label for='store'>Store Pickup</label><br>");
        pw.print("</td></tr>");

        pw.print("<tr><td>");
	    pw.print("Pickup Locations</td>");
		//pw.print("<td><input type='text' name='userAddress'>");
		 pw.print("<td>"+
		  "<select name='deliveryLocation' id='deliveryLocation'>"+
		    "<option value='mumbai'>Chunnabhatii 400852</option>"+
		    "<option value='chicago'>Chembur 400071</option>"+
		    "<option value='LA'>Vashi 400125</option>"+
		    "<option value='Vegas'>Airoli 400215</option>"+
		    "<option value='Newyork'>Mulund 400025</option>"+
		    "<option value='utah'>Thane 400012</option>"+
		    "<option value='sanjose'>Ghatkopar 400125</option>"+
		    "<option value='sandiego'>Diva 400034</option>"+
		    "<option value='cali'>Alibaug 400032</option>"+
		    "<option value='portland'>Dadar 400521</option>"+
		    "<option value='maine'>Kurla 400051</option>"+
		  "</select>");
        pw.print("</td></tr>");


		if(session.getAttribute("usertype").equals("retailer"))
		{
		pw.print("<tr><td>");
	    pw.print("Customer Name</td>");
		pw.print("<td><input type='text' name='customername'>");
        pw.print("</td></tr>");
		}
		pw.print("<tr><td colspan='2'>");
		pw.print("<input type='submit' name='submit' class='btnbuy'>");
        pw.print("</td></tr></table></form>");
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	    }
        catch(Exception e)
		{
         
		}  			
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    }
}
