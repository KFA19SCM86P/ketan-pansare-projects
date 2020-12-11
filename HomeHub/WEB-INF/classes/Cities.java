import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.City;


@WebServlet("/Cities")

public class Cities extends HttpServlet{
	public static HashMap<String, City> cities = new HashMap<String, City>();
    public Cities(){
        HashMap<String, City> cities=new HashMap<String, City>();
		try
		{		
			cities=MySqlDataStoreUtilities.getCities();
		}
		catch(Exception e)
		{
			
		}
    }
}
