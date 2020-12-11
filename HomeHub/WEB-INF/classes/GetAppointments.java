import pojo.Appointment;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import java.util.ArrayList;

@WebServlet("/GetAppointments")

public class GetAppointments extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        HttpSession session = request.getSession(true);
        try {
            if(session.getAttribute("usertype").toString().equalsIgnoreCase("customer")){
                appointments = MySqlDataStoreUtilities.getCustomerAppointments(session.getAttribute("userId").toString());
            }
            else if(session.getAttribute("usertype").toString().equalsIgnoreCase("professional")){
                appointments = MySqlDataStoreUtilities.getProfessionalAppointments(session.getAttribute("userId").toString());
            }
            request.setAttribute("appointments",appointments);
            System.out.println("in servlet getapp"+appointments);
            request.getRequestDispatcher("Profile.jsp").forward(request,response);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}