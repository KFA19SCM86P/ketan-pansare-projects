import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletOutputStream;
import com.google.gson.Gson;
import pojo.User;

@WebServlet("/Login")

public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		utility.logout();
		String status = "fail";
		HttpSession session = request.getSession(true);
		// ServletOutputStream sos = response.getOutputStream();
		/* User Information(username,password) is obtained from HttpServletRequest,
		Based on the Type of user(customer,retailer,manager) respective hashmap is called and the username and 
		password are validated and added to session variable and display Login Function is called */
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		HashMap<String, User> hm=new HashMap<String, User>();
		try
		{		
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{			
		}
		// System.out.println(hm.keySet());
		User user = hm.get(userId);
		// System.out.println(hm.containsKey(userId));
		// System.out.println(user!=null);
		if(user!=null)
		{
			System.out.println("User "+user.getFirstName()+" found.");
            user = hm.get(userId);
		    String user_password = user.getPassword();
		    if (password.equals(user_password))
			{
                session.setAttribute("userId", user.getUserId());
				session.setAttribute("firstname", user.getFirstName());
                session.setAttribute("usertype", user.getUsertype());
				status = "success";
			}
		}
		if(status.equals("success")){
			response.sendRedirect("Home.jsp");
		}
		else{
			session.setAttribute("login_err", "Invalid credentials.");
			response.sendRedirect("Login.jsp");
		}
	}
}