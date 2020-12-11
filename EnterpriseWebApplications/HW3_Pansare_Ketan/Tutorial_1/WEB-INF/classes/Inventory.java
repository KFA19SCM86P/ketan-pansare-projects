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

@WebServlet("/Inventory")

public class Inventory extends HttpServlet {
	
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
		pw.print("<form name ='Inventory' action='Inventory' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Inventory Reports</a>");
		pw.print("</h2><div class='entry'>");

	
		if(request.getParameter("Lp")==null  && request.getParameter("Lpg")==null && request.getParameter("Lps")==null && request.getParameter("Lpr")==null)
		{
			utility.printHtml("Inventory.html");
		}		
		
		if(request.getParameter("Lp")!=null)
		{
				pw.print("<h2> List of Product and Count </h2>");
				ArrayList<Product> products = MySqlDataStoreUtilities.selectProduct();
				printProducts(products,pw,0);
		}
		
		if(request.getParameter("Lps")!=null)
		{
			
			pw.print("<h2> List of Product on Sale</h2>");
			ArrayList<Product> products = MySqlDataStoreUtilities.selectProductwithSale();
			printProducts(products,pw,1);
			
		}
		
		if(request.getParameter("Lpg")!=null)
		{
			
			pw.print("<h2> List of Product and Count</h2>");
			ArrayList<Product> products = MySqlDataStoreUtilities.selectProduct();
			
			
			pw.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
			pw.println("<script type='text/javascript'>");
			pw.println("google.charts.load('current', {packages: ['corechart', 'bar']});");
			pw.println("google.charts.setOnLoadCallback(drawBasic);");
			pw.println("function drawBasic() {");
			pw.println("var data = google.visualization.arrayToDataTable([");
			pw.println("['Product Name', 'Total Items'],");

			
			for(Product p : products){
			
			String name = p.getName();
			int quantity = p.getCount();
			pw.println("[' " +name+ " ', "+quantity+ "],");
			}
			pw.println("]);");
			pw.println("var options = {");
			pw.println("title: 'product names and the total number of items available',");
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
		
		if(request.getParameter("Lpr")!=null)
		{
			
			pw.print("<h2> List of Product with Rebate </h2>");
			ArrayList<Product> products = MySqlDataStoreUtilities.selectProductwithRebate();
			printProducts(products,pw,2);
			
		}
		
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	}

public void printReviews(ArrayList<Review> reviews,PrintWriter pw){
	
	
		String userName = "";
		String productName = "";
		String reviewRating = "";
		String reviewDate;
		String reviewText = "";	
		String age = "";	
		String gender = "";	
		String occupation = "";	
		String price = "";
		String city ="";
		String productType = "";
	for (Review r : reviews) 
				 {		
				pw.print("<table class='gridtable'>");
				pw.print("<tr>");
				pw.print("<td> Product Name: </td>");
				productName = r.getProductName();
				pw.print("<td>" +productName+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> Product Type: </td>");
				productType = r.getProductType();
				pw.print("<td>" +productType+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> userName: </td>");
				userName = r.getUserName();
				pw.print("<td>" +userName+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> price: </td>");
				price = r.getPrice();
				pw.print("<td>" +price+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> Retailer City: </td>");
				city = r.getRetailerCity();
				pw.print("<td>" +city+ "</td>");
				pw.print("</tr>");
				pw.println("<tr>");
				pw.println("<td> Review Rating: </td>");
				reviewRating = r.getReviewRating().toString();
				pw.print("<td>" +reviewRating+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> Review Date: </td>");
				reviewDate = r.getReviewDate().toString();
				pw.print("<td>" +reviewDate+ "</td>");
				pw.print("</tr>");			
				pw.print("<tr>");
				pw.print("<td> Review Text: </td>");
				reviewText = r.getReviewText();
				pw.print("<td>" +reviewText+ "</td>");
				pw.print("</tr>");
				pw.print("<td> Age : </td>");
				age = r.getAge();
				pw.print("<td>" +age+ "</td>");
				pw.print("</tr>");
				pw.print("<td> Gender : </td>");
				gender = r.getGender();
				pw.print("<td>" +gender+ "</td>");
				pw.print("</tr>");
				pw.print("<td> Occupation : </td>");
				occupation = r.getOccupation();
				pw.print("<td>" +occupation+ "</td>");
				pw.print("</tr>");
				pw.println("</table>");
				pw.println("</br>");
				}
				
		if(reviews.isEmpty()){
			pw.println("<h4>Nothing to Display</h4>");
		}

}
	
public void printProducts(ArrayList<Product> products,PrintWriter pw, int a){
	
	
		String p_name = "";
		double price = 0.0;
		double p_discount = 0.0;
		double p_rebate = 0.0;
		int p_count = 0;
		
		pw.print("<table class='gridtable'>");
		pw.print("<tr>");
		pw.print("<td><b>Product Name: </b></td>");
		pw.print("<td><b> Product Price: </b></td>");
		pw.print("<td><b> Product Count: </b></td>");
		if(a == 1)
			pw.print("<td><b> Product Discount: </b></td>");
		if(a == 2)
			pw.print("<td><b> Product Rebate: </b></td>");
		pw.print("</tr>");
		
	for (Product p : products) 
				{		
				
				p_name = p.getName();
				price = p.getPrice();
				p_count = p.getCount();
				p_discount = p.getDiscount();
				p_rebate = p.getRebate();
				pw.print("<tr>");
				pw.print("<td>" +p_name+ "</td>");
				pw.print("<td>" +price+ "</td>");
				pw.print("<td>" +p_count+ "</td>");
				if(a == 1)
					pw.print("<td>" +p_discount+ "</td>");
				if(a == 2)
					pw.print("<td>" +p_rebate+ "</td>");
				pw.print("</tr>");
				}
				
		if(products.isEmpty()){
			pw.println("<h4>Nothing to Display</h4>");
		}
		
		pw.println("</table>");
		pw.println("</br>");

}

}


