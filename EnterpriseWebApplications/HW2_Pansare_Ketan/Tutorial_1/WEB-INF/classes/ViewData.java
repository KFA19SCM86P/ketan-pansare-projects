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

@WebServlet("/ViewData")

public class ViewData extends HttpServlet {
	
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
		pw.print("<form name ='ViewData' action='ViewData' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Top N Search</a>");
		pw.print("</h2><div class='entry'>");

	
		if(request.getParameter("Topn")==null && request.getParameter("ViewDataComm")==null && request.getParameter("Median_val")==null)
		{
			utility.printHtml("TopN.html");
		}		

		/*if(request.getParameter("Analyse")!=null)
		{		
				ArrayList<Review> reviews = MongoDBDataStoreUtilities.selectAllReview();
				
				if(request.getParameter("pname")!= null && request.getParameter("pname_val")!= null ){
					String  name = request.getParameter("pname_val");
					if(name.equalsIgnoreCase("all")){
						pw.print("<h4 style='color:red'>Reviews for all Products </h4>");
						
					}
					else{
						ArrayList<Review> select1List =new ArrayList<>();
						for(Review r : reviews){
							if(r.getProductType().equalsIgnoreCase(name)){
								select1List.add(r);
							}
						}
						
						pw.print("<h4 style='color:red'>You Selected"+name+"</h4>");
						reviews = select1List;	
					}
				}
				
				if(request.getParameter("pprice")!= null && request.getParameter("pprice_val")!= null ){
					String  price = request.getParameter("pprice_val");
					int price_int = Integer.parseInt(price);
					String  compare = request.getParameter("pprice_val_comp"); 
					ArrayList<Review> select2List =new ArrayList<>();
					for(Review r : reviews){
							int r_price_int = Integer.parseInt(r.getPrice());
							if(compare.equalsIgnoreCase("eq") && r_price_int == price_int){
									select2List.add(r);
							}
							else if(compare.equalsIgnoreCase("gt") && r_price_int > price_int){
									select2List.add(r);
							}
							else if(compare.equalsIgnoreCase("lt") && r_price_int < price_int){
									select2List.add(r);
							}
						}
						reviews = select2List;	
				}
				
				if(request.getParameter("rating")!= null && request.getParameter("rating_val")!= null ){
					String  rating = request.getParameter("rating_val");
					int rating_int = Integer.parseInt(rating);
					String  compare = request.getParameter("rating_val_comp"); 
					ArrayList<Review> select3List =new ArrayList<>();
					for(Review r : reviews){
							int r_rating_int = Integer.parseInt(r.getReviewRating());
							if(compare.equalsIgnoreCase("eq") && r_rating_int == rating_int){
									select3List.add(r);
							}
							else if(compare.equalsIgnoreCase("gt") && r_rating_int > rating_int){
									select3List.add(r);
							}
						}
						reviews = select3List;	
				}
				
				if(request.getParameter("city")!= null && request.getParameter("city_val")!= null ){
					String  city = request.getParameter("city_val");
					ArrayList<Review> select4List =new ArrayList<>();
					for(Review r : reviews){
							if(city.equalsIgnoreCase(r.getRetailerCity())){
									select4List.add(r);
							}
						}
						reviews = select4List;	
				}
				
				if(request.getParameter("groupby")!= null && request.getParameter("groupby_val")!= null ){
					
					
					String  gby = request.getParameter("groupby_val");
					
					String  gby_opt = request.getParameter("groupby_opt"); 
					HashMap<String,Integer> hm =new HashMap<String,Integer>();
					pw.print("<h4 style='color:red'> In group by</h4>");
					for(Review r : reviews){
							if(gby.equalsIgnoreCase("Product")){
								String p_name = r.getProductName();
								if(!hm.containsKey(p_name)){
									hm.put(p_name,1);
								}
								else{
									int val = hm.get(p_name);
									hm.put(p_name,val+1);		
								}
							}
							if(gby.equalsIgnoreCase("Price")){
								String  price = r.getPrice();
								if(!hm.containsKey(price)){
									hm.put(price,1);
								}
								else{
									int val = hm.get(price);
									hm.put(price,val+1);		
								}
							}
							if(gby.equalsIgnoreCase("Review")){
								String  r_rating = r.getReviewRating();
								if(!hm.containsKey(r_rating)){
									hm.put(r_rating,1);
								}
								else{
									int val = hm.get(r_rating);
									hm.put(r_rating,val+1);		
								}
							}
							if(gby.equalsIgnoreCase("City")){
								String  city = r.getRetailerCity();
								if(!hm.containsKey(city)){
									hm.put(city,1);
								}
								else{
									int val = hm.get(city);
									hm.put(city,val+1);		
								}
							}
						}
					
					
					
					pw.print("<table class='gridtable'>");
					pw.print("<tr><td>"+gby+"</td><td>Count</td></tr>");
					
					for (Map.Entry<String,Integer> entry : hm.entrySet()){
							
							pw.print("<tr>");
							pw.print("<td>"+entry.getKey()+"</td>");
							pw.print("<td>" +entry.getValue()+ "</td>");
							pw.print("</tr>");
					}
					pw.println("</table>");
					pw.println("</br>");
					
				}
				else{
					printReviews(reviews,pw);
				}
				
				pw.print("</form>");
		} */
		
		if(request.getParameter("Topn")!=null)
		{
				pw.print("<h2> In Top 5</h2>");
				if(request.getParameter("top_n")!= null && request.getParameter("category_val")!= null && request.getParameter("rating_val_t5")!= null && request.getParameter("sort_val")!= null ){
					
					String  rating = request.getParameter("rating_val_t5");
					String  n = request.getParameter("top_n");
					
					int ratingInt = Integer.parseInt(rating);
					int nInt = Integer.parseInt(n);
					
					
					boolean rating_req = ratingInt == 0 ? false : true;
					HashMap<String,ArrayList<Review>> map = new HashMap<>();
					
					if(request.getParameter("sort_val").equalsIgnoreCase("zip")){
						map = MongoDBDataStoreUtilities.selectTop5ProdsByZip(nInt,ratingInt,rating_req);			
						
					}else if(request.getParameter("sort_val").equalsIgnoreCase("city")){
						map = MongoDBDataStoreUtilities.selectTop5ProdsByCity(nInt,ratingInt,rating_req);			
						
					}
					else if(request.getParameter("sort_val").equalsIgnoreCase("brand")){
						map = MongoDBDataStoreUtilities.selectTop5ProdsByBrand(nInt,ratingInt);			
						
					}
					
					for(Map.Entry<String,ArrayList<Review>> entry : map.entrySet())
						{
							pw.print("<h4>"+entry.getKey()+"</h4>");
							printReviews(entry.getValue(),pw);
							
						}
					
				}
		}
		
		if(request.getParameter("ViewDataComm")!=null)
		{
			
			pw.print("<h2>In Review Comment Search</h2>");
			if(request.getParameter("ip_text")!= null){
				printReviews(MongoDBDataStoreUtilities.selectCommentPattern(request.getParameter("ip_text")),pw);
			}
			
		}
		
		if(request.getParameter("Median_val")!=null)
		{
			
			pw.print("<h2>In Median Calculation</h2>");
			if(request.getParameter("Median_val")!= null){
				HashMap<String,Integer> map = new HashMap<>();
					
				map = MongoDBDataStoreUtilities.getMedian();	
				pw.print("<h4>Median</h4>");
				pw.print("<table class='gridtable'>");
				pw.print("<tr>");
				pw.print("<td> City Name: </td>");
				pw.print("<td> Median: </td>");
				pw.print("</tr>");
				for(Map.Entry<String,Integer> entry : map.entrySet())
				{
					pw.print("<tr>");
					pw.print("<td>" +entry.getKey()+ "</td>");
					pw.print("<td>" +entry.getValue()+ "</td>");
					pw.print("</tr>");
								
				}
				pw.println("</table>");
				pw.println("</br>");
			}
			
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
	
	

}


