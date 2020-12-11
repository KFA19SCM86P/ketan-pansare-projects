import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import pojo.User;

@WebServlet("/Registration")

public class Registration extends HttpServlet {
	private String error_msg;

	/*   A json with user information is Obtained from HttpServletRequest variable and User Details are Added to the Users HashMap */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
        String status = "fail";
        HttpSession session = request.getSession(true);
        String userId = request.getParameter("userid");
        String firstname = request.getParameter("fname");
        String lastname = request.getParameter("lname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("contact");
        User user = new User(userId,firstname,lastname,password,email,phone);
		Utilities utility = new Utilities(request, pw);
        HashMap<String, User> hm=new HashMap<String, User>();
        try
        {
            hm=MySqlDataStoreUtilities.selectUser();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        hm.put(user.getUserId(), user);
        try{
            MySqlDataStoreUtilities.insertUser(user);
            status = "success";
            session.setAttribute("registration_msg", "Registered successfully!Login to continue.");
        }
        catch (Exception e){
            System.out.println(e);
            status = "fail";       
            session.setAttribute("registration_msg", "Registration failed!Please try again.");
        }
        if(status.equals("success")){
			response.sendRedirect("Login.jsp");
		}
		else{
			response.sendRedirect("Registration.jsp");
		}
	}
}
