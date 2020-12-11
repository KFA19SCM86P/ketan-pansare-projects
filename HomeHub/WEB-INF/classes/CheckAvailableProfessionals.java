import com.google.gson.Gson;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpSession;

@WebServlet("/AvailableProfessionals")

public class CheckAvailableProfessionals extends HttpServlet {

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.getParameter("category"));

        System.out.print("CheckAvailableProfessionals is called!!!!!!!!!!!!");
        request.getRequestDispatcher("providers.jsp").forward(request,response);
        // // String professionalJson = new Gson().toJson(professionals);

        // System.out.println(professionalJson);
        // response.getWriter().write(professionalJson);
	}
}
