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

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				String userrole = session.getAttribute("usertype").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+"<li><div class=\"dropdown\">"+
						"<button class=\"dropbtn\">Action</button>"+
							"<div class=\"dropdown-content\">"+
							"<a href='Account'>View Account</a>";
							if(userrole.equals("retailer")){
								result = result +"<a href=\"Actions\">Manage Products</a>";
							}else if(userrole.equals("manager")){
								result = result +"<a href=\"ActionsSales\">Edit Orders</a>";
							}
							result = result +"</div></div></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
			}
			else
				result = result +"<li><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				result = result +"<li><a href='Cart'><span class='glyphicon'>Cart("+CartCount()+")</span></a></li></ul></div></div><div id='page'>";
				pw.print(result);
		} else
				pw.print(result);
	}
	

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{		
				FileInputStream fileInputStream=new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\UserDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				hm= (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		TreeMap<Integer, ArrayList<OrderPayment>> orderPayments = new TreeMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (TreeMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
			
			if(!orderPayments.isEmpty()){
				return orderPayments.lastKey();	
			}
			else{
				return 1;
			}	
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		if(type.equals("tvs")){
			Tv tv;
			tv = SaxParserDataStore.tvs.get(name);
			OrderItem orderitem = new OrderItem(tv.getName(), tv.getPrice(), tv.getImage(), tv.getRetailer(),tv.getDiscount());
			orderitem.setType("TV");
			orderItems.add(orderitem);
		}
		
		if(type.equals("laptops")){
			Laptop laptop;
			laptop = SaxParserDataStore.laptops.get(name);
			OrderItem orderitem = new OrderItem(laptop.getName(), laptop.getPrice(), laptop.getImage(), laptop.getRetailer(),laptop.getDiscount());
			orderitem.setType("Laptop");
			orderItems.add(orderitem);
		}
		
		if(type.equals("wears")){
			Wear wear;
			wear = SaxParserDataStore.wears.get(name);
			OrderItem orderitem = new OrderItem(wear.getName(), wear.getPrice(), wear.getImage(), wear.getRetailer(),wear.getDiscount());
			orderItems.add(orderitem);
		}
		if(type.equals("phones")){
			Phone phone = null;
			phone = SaxParserDataStore.phones.get(name);
			OrderItem orderitem = new OrderItem(phone.getName(), phone.getPrice(), phone.getImage(), phone.getRetailer(),phone.getDiscount());
			orderItems.add(orderitem);
		}
		if(type.equals("vas")){
			Va va = null;
			va = SaxParserDataStore.vas.get(name);
			OrderItem orderitem = new OrderItem(va.getName(), va.getPrice(), va.getImage(), va.getRetailer(),va.getDiscount());
			orderItems.add(orderitem);
		}
		
		if(type.equals("wirelesss")){
			Wireless wireless = null;
			wireless = SaxParserDataStore.wirelesss.get(name);
			OrderItem orderitem = new OrderItem(wireless.getName(), wireless.getPrice(), wireless.getImage(), wireless.getRetailer(),wireless.getDiscount());
			orderItems.add(orderitem);
		}
		
	}
	// store the payment details for orders
	public void storePayment(int orderId,
		String orderName,double orderPrice,String userAddress,String creditCardNo){
		TreeMap<Integer, ArrayList<OrderPayment>> orderPayments = null;
		String TOMCAT_HOME = System.getProperty("catalina.home");
			// get the payment details file 
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (TreeMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
			if(orderPayments==null)
			{
				orderPayments = new TreeMap<Integer, ArrayList<OrderPayment>>();
			}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
		OrderPayment orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice,userAddress,creditCardNo);
		SimpleDateFormat sdf  = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String dateofOrder = sdf.format(new Date());
		orderpayment.setDateOfOrder(dateofOrder);
		
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
		
		orderpayment.setDateOfDelivery(deliveryDate);
		listOrderPayment.add(orderpayment);	
			
			// add order details into file

			try
			{	
				FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            	objectOutputStream.writeObject(orderPayments);
				objectOutputStream.flush();
				objectOutputStream.close();       
				fileOutputStream.close();
			}
			catch(Exception e)
			{
				System.out.println("inside exception file not written properly");
			}	
	}

	
	/* getWears Functions returns the Hashmap with all wears in the store.*/

	public HashMap<String, Wear> getWears(){
			HashMap<String, Wear> hm = new HashMap<String, Wear>();
			hm.putAll(SaxParserDataStore.wears);
			return hm;
	}
	
	/* getGames Functions returns the  Hashmap with all Games in the store.*/

	public HashMap<String, Phone> getPhones(){
			HashMap<String, Phone> hm = new HashMap<String, Phone>();
			hm.putAll(SaxParserDataStore.phones);
			return hm;
	}
	
	/* getVas Functions returns the Hashmap with all Va in the store.*/

	public HashMap<String, Va> getVas(){
			HashMap<String, Va> hm = new HashMap<String, Va>();
			hm.putAll(SaxParserDataStore.vas);
			return hm;
	}
	
	public HashMap<String, Wireless> getWirelesss(){
			HashMap<String, Wireless> hm = new HashMap<String, Wireless>();
			hm.putAll(SaxParserDataStore.wirelesss);
			return hm;
	}
	
	/* getProducts Functions returns the Arraylist of wears in the store.*/

	public ArrayList<String> getProducts(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Wear> entry : getWears().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of phones in the store.*/

	public ArrayList<String> getProductsPhone(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Phone> entry : getPhones().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of Vas in the store.*/

	public ArrayList<String> getProductsVas(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Va> entry : getVas().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsWirelesss(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Wireless> entry : getWirelesss().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	

}
