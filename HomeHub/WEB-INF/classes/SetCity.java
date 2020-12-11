import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SetCity")

public class SetCity extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String cityCode = request.getParameter("city");
		String cityName = request.getParameter("cityName");
        HttpSession session = request.getSession(true);
        session.setAttribute("city", cityCode);
		session.setAttribute("cityName", cityName);
		System.out.println("Set city "+session.getAttribute("city")+" in session.");
        response.sendRedirect("Home.jsp");
        return;
	}
}
