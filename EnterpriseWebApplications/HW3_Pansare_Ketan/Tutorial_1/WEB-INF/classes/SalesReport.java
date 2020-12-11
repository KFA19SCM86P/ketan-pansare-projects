import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.text.*;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.io.PrintWriter;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/SalesReport")

public class SalesReport extends HttpServlet {
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
		//check if the user is logged in
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View your Orders");
			response.sendRedirect("Login");
			return;
		}
		String username=utility.username();
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='SalesReport' action='SalesReport' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Sales Reports</a>");
		pw.print("</h2><div class='entry'>");

	
		if(request.getParameter("Sp")==null && request.getParameter("Spg")==null && request.getParameter("Spd")==null)
		{
			utility.printHtml("SalesReport.html");
		}		
		
		if(request.getParameter("Sp")!=null)
		{
				pw.print("<h2> List of Sold Product and Count </h2>");
				ArrayList<Product> products = MySqlDataStoreUtilities.selectSoldProduct();
				printProducts(products,pw,0);
		}
		
		if(request.getParameter("Spg")!=null)
		{
			
			pw.print("<h2> List of Sold Product and Count</h2>");
			ArrayList<Product> products = MySqlDataStoreUtilities.selectSoldProductAll();
			
			
			pw.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
			pw.println("<script type='text/javascript'>");
			pw.println("google.charts.load('current', {packages: ['corechart', 'bar']});");
			pw.println("google.charts.setOnLoadCallback(drawBasic);");
			pw.println("function drawBasic() {");
			pw.println("var data = google.visualization.arrayToDataTable([");
			pw.println("['Product Name', 'Total Items'],");

			
			for(Product p : products){
			
			String name = p.getName();
			int quantity = p.getSold();
			pw.println("[' " +name+ " ', "+quantity+ "],");
			}
			pw.println("]);");
			pw.println("var options = {");
			pw.println("title: 'product names and the total number of items sold',");
			pw.println("chartArea: {width: '65%', height: 950},");
			pw.println("hAxis: {");
			pw.println("title: 'Total number of products',");
			pw.println("minValue: 10");
			pw.println("},");
			pw.println("vAxis: {");
			pw.println("title: 'Product Name'");
			pw.println("}");
			pw.println("};");
			pw.println("var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");
			pw.println("chart.draw(data, options);");
			pw.println("}");
			pw.println("</script>");
			pw.println("<div id='chart_div' style='width:900px; height:1000px'></div>");
			
		}
		
		if(request.getParameter("Spd")!=null)
		{
			try{
			pw.print("<h2> List of Product Sold Daily </h2>");
			ArrayList<Order> orders = MySqlDataStoreUtilities.selectOrders();
			HashMap<Date,Integer> m = new HashMap<>();
			for(Order o: orders){
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
				Date date = sdf.parse(o.getOdate());
				if(m.containsKey(date)){
					int x = m.get(date);
					m.put(date,x+1);
				}
				else{
					m.put(date,1);	
				}
			}
			
			pw.print("<table class='gridtable'>");
			pw.print("<tr>");
			pw.print("<td><b>Date </b></td>");
			pw.print("<td><b> Items Sold</b></td>");
			pw.print("</tr>");
			
			for (Map.Entry<Date,Integer> e : m.entrySet()) 
				{
				pw.print("<tr>");
				pw.print("<td>" +e.getKey()+ "</td>");
				pw.print("<td>" +e.getValue()+ "</td>");
				pw.print("</tr>");
				}
			pw.println("</table>");
			pw.println("</br>");
			
			pw.print("</div></div></div>");		
			utility.printHtml("Footer.html");
			}
			catch (ParseException e) {
			e.printStackTrace();
		}
		}
	}
	
public void printProducts(ArrayList<Product> products,PrintWriter pw, int a){
	
	
		String p_name = "";
		double price = 0.0;
		double p_discount = 0.0;
		int p_sold = 0;
		
		pw.print("<table class='gridtable'>");
		pw.print("<tr>");
		pw.print("<td><b>Product Name: </b></td>");
		pw.print("<td><b> Product Price: </b></td>");
		pw.print("<td><b> Product Sold: </b></td>");
		pw.print("</tr>");
		
	for (Product p : products) 
				{		
				
				p_name = p.getName();
				price = p.getPrice();
				p_sold = p.getSold();
				
				pw.print("<tr>");
				pw.print("<td>" +p_name+ "</td>");
				pw.print("<td>" +price+ "</td>");
				pw.print("<td>" +p_sold+ "</td>");
				pw.print("</tr>");
				}
				
		if(products.isEmpty()){
			pw.println("<h4>Nothing to Display</h4>");
		}
		
		pw.println("</table>");
		pw.println("</br>");

}



}


