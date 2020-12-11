import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.*;
import java.sql.SQLException;
import pojo.Professional;
import pojo.User;
import pojo.City;
import pojo.Appointment;

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
		If the html file name is Header.html then It gets userId from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		if (file == "header.html"){
			// result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("userId")!=null){
				String usertype = session.getAttribute("usertype").toString();
				System.out.println("UserType is : "+usertype+"\n");
				String userId = session.getAttribute("userId").toString();
				userId = Character.toUpperCase(userId.charAt(0)) + userId.substring(1);
				String firstname = session.getAttribute("firstname").toString();
				result = result +"<li><a href=''>Welcome "+firstname+"</a></li>";
				result = result + "<li><a href='Logout'>Logout</a></li>";
			}
			else{
				result = result + "<li><a href='Login'>Login/Sign Up</a></li>";
			}
			result = result + "</u1></nav></div></header>";
		}
		if (file == "login.html"){
			if (session.getAttribute("login_err")!=null){
				result = result +"<h4 style='color:red'>"+session.getAttribute("login_err")+"</h4>";
				session.removeAttribute("login_err");
			}
			if (session.getAttribute("registration_msg")!=null){
				result = result +"<h4 style='color:green'>"+session.getAttribute("registration_msg")+"</h4>";
				session.removeAttribute("registration_msg");
			}
			result = result + "</div></div></div>";
		}
		if (file == "registration.html"){
			if (session.getAttribute("registration_msg")!=null){
				result = result +"<h4 style='color:red'>"+session.getAttribute("registration_msg")+"</h4>";
				session.removeAttribute("registration_msg");
			}
			result = result + "</div></div></div>";
		}
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

	/*  logout Function removes the userId , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("userId");
		session.removeAttribute("usertype");
		session.removeAttribute("firstname");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("userId")==null)
			return false;
		return true;
	}

	/*  userId Function returns the userId from the session variable.*/
	
	public String userId(){
		if (session.getAttribute("userId")!=null)
			return session.getAttribute("userId").toString();
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
			try
			{		
				hm=MySqlDataStoreUtilities.selectUser();
			}
			catch(Exception e)
			{
			}	
		User user = hm.get(userId());
		return user;
	}

	public static int getTotalAppointments(){
		int count=0;
		try
		{
			count = MySqlDataStoreUtilities.getAppointmentsCount();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}	
		return count;		
	}

	// public HashMap<String, City> getCities() throws Exception{
	// 	HashMap<String, City> cities=new HashMap<String, City>();
	// 	try
	// 	{		
	// 		cities=MySqlDataStoreUtilities.getCities();
	// 	}
	// 	catch(Exception e)
	// 	{
	// 		throw e;
	// 	}	
	// 	return cities;
	// }

}
