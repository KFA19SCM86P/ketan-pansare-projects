import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.util.*;
import pojo.Service;

public class ServiceRecommenderUtility{
	
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

	public HashMap<String,String> readOutputFile(){

		String TOMCAT_HOME = System.getProperty("catalina.home");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
		HashMap<String,String> prodRecmMap = new HashMap<String,String>();
		try {

            br = new BufferedReader(new FileReader(new File(TOMCAT_HOME+"\\webapps\\Project\\matrixFactorizationBasedRecommendations.csv")));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] prod_recm = line.split(cvsSplitBy,2);
				prodRecmMap.put(prod_recm[0],prod_recm[1]);
            }
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
		
		return prodRecmMap;
	}
	
	public static Service getService(String serviceId){
		Service serviceObj = new Service();
		try 
		{
			String msg = getConnection();
			String selectService="select * from  Service where serviceId=?";
			PreparedStatement pst = conn.prepareStatement(selectService);
			pst.setString(1,serviceId);
			ResultSet rs = pst.executeQuery();
		
			while(rs.next())
			{	
				serviceObj = new Service(rs.getString("serviceId"),rs.getString("serviceName"),rs.getString("category"),rs.getDouble("serviceCost"),rs.getDouble("discount"));
			}
			rs.close();
			pst.close();
			conn.close();
		}
		catch(Exception e)
		{
		}
		return serviceObj;	
	}
}