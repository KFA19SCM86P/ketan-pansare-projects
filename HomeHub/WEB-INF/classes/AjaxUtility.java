import java.io.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;

import java.sql.*;

import java.io.IOException;
import java.io.*;
import pojo.Service;



public class AjaxUtility {
	StringBuffer sb = new StringBuffer();
	boolean namesAdded = false;
	static Connection conn = null;
    static String message;
	public static String getConnection()
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/homehubsql","root","3306");							
			message="Successfull";
			return message;
		}
		catch(SQLException e)
		{
			 message="unsuccessful";
		     return message;
		}
		catch(Exception e)
		{
			 message="unsuccessful";
		     return message;
		}
	}
	
	public  StringBuffer readdata(String searchId)
	{	
		System.out.println("SearchId:"+searchId);
		HashMap<String,Service> data;
		data=getData();
		
 	    Iterator it = data.entrySet().iterator();	
        while (it.hasNext()) 
	    {
            Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
				Service s=(Service)pi.getValue();                   
                if (s.getServiceName().toLowerCase().startsWith(searchId))
                {
                        sb.append("<service>");
                        sb.append("<id>" + s.getServiceId() + "</id>");
                        sb.append("<serviceName>" + s.getServiceName() + "</serviceName>");
                        sb.append("</service>");
                }
			}
       }
	   
	   return sb;
	}
	
	public static HashMap<String,Service> getData()
	{
		HashMap<String,Service> hm=new HashMap<String,Service>();
		try
		{
			getConnection();
			
		    String selectproduct="select * from  Service";
		    PreparedStatement pst = conn.prepareStatement(selectproduct);
			ResultSet rs = pst.executeQuery();
			System.out.println("Here in getData!");
			while(rs.next())
			{	Service s = new Service(rs.getString("serviceId"),rs.getString("serviceName"),rs.getString("category"));
				hm.put(rs.getString("serviceName"), s);
			}
			System.out.println(hm);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hm;			
	}
	public static void storeData(HashMap<String,Service> servicedata)
	{
		try
		{
		
			getConnection();
				
			String insertIntoServiceQuery = "INSERT INTO service(serviceId,serviceName,category) "
			+ "VALUES (?,?,?);";	
			for(Map.Entry<String, Service> entry : servicedata.entrySet())
			{	

				PreparedStatement pst = conn.prepareStatement(insertIntoServiceQuery);
				//set the parameter for each column and execute the prepared statement
				pst.setString(1,entry.getValue().getServiceId());
				pst.setString(2,entry.getValue().getServiceName());
				pst.setString(3,entry.getValue().getCategory());
				pst.execute();
			}
		}
		catch(Exception e)
		{	
	
		}		
	}

}
