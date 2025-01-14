import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.*;
//import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.*;

@WebServlet("/Payment")

public class Payment extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		LocalDate orderDate = LocalDate.now();
		System.out.println("Today's date is: "+orderDate);
		System.out.println("2 weeks from now: "+orderDate.plusDays(14));
		
		String uname ;
		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}else{
		uname = utility.username();
		}
		// get the payment details like credit card no address processed from cart servlet	

		String userAddress=request.getParameter("userAddress");
		String cstreet=request.getParameter("cstreet");
		String ccity=request.getParameter("ccity");
		String cstate=request.getParameter("cstate");
		String czipcode=request.getParameter("czipcode");
		String creditCardNo=request.getParameter("creditCardNo");
		if(!userAddress.isEmpty() && !creditCardNo.isEmpty() && !cstreet.isEmpty() && !ccity.isEmpty() && !cstate.isEmpty() && !czipcode.isEmpty())
		{
			//Random rand = new Random(); 
			//int orderId = rand.nextInt(100);
			int orderId=utility.getOrderPaymentSize()+1;

			//iterate through each order

			for (OrderItem oi : utility.getCustomerOrders())
			{
				
				SimpleDateFormat sdf  = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				String dateofOrder = sdf.format(new Date());
				
				
				Calendar c = Calendar.getInstance();
				try{
				//Setting the date to the given date
				c.setTime(sdf.parse(dateofOrder));
				}catch(ParseException e){
				e.printStackTrace();
				}
			   
				//Number of Days to add for now its 15
				c.add(Calendar.DAY_OF_MONTH, 15);  
				//Date after adding the days to the given date
				String deliveryDate = sdf.format(c.getTime()); 
				
				
				//set the parameter for each column and execute the prepared statement
				MySqlDataStoreUtilities.saveProduct(orderId,uname,oi.getName(),oi.getPrice(),userAddress,creditCardNo,dateofOrder,deliveryDate,cstreet,ccity,cstate,czipcode);
				MySqlDataStoreUtilities.saveCustomerAddress(orderId,uname,cstreet,ccity,cstate,czipcode);

			}

			//remove the order details from cart after processing
			
			OrdersHashMap.orders.remove(utility.username());	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h2>Your Order");
			pw.print("&nbsp&nbsp");  
			pw.print("is stored ");
			pw.print("<br>Your Order No is "+(orderId));
			pw.print("<br>Delivery/pickup date is "+(orderDate.plusDays(14)));
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}else
		{
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h4 style='color:red'>Please enter valid address and creditcard number</h4>");
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
