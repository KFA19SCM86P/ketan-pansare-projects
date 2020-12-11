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

import pojo.Professional;

@WebServlet("/GetProfessionals")

public class GetProfessionals extends HttpServlet {

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {

            System.out.print("getprofessionals is called!!!!!!");
	    // response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		// PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession(true);

		String cityCode = session.getAttribute("city").toString();
        System.out.println("Printing city in session:"+session.getAttribute("city"));
        String subCategory = request.getParameter("subCategory");
        String category = request.getParameter("category");
        
        System.out.println("In Getprofessionals: "+category); 

        ArrayList<Professional> professionals = new ArrayList<Professional>();
        try{
            professionals = MySqlDataStoreUtilities.getProfessionals(cityCode,category);
        }
        catch(Exception e){
        }
        request.setAttribute("professionals",professionals);
        System.out.println(professionals);
        request.getRequestDispatcher("providers.jsp").forward(request,response);
        // // String professionalJson = new Gson().toJson(professionals);

        
        // response.getWriter().write(professionalJson);
	}
}
