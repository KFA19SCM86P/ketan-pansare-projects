import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.LocalTime;
import java.time.LocalDate;
import pojo.Appointment;

@WebServlet("/CancelAppointment")

public class CancelAppointment extends HttpServlet {
	private String error_msg;

	/*   A json with user information is Obtained from HttpServletRequest variable and User Details are Added to the Users HashMap */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = "fail";
        HttpSession session = request.getSession(true);
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        try{
            MySqlDataStoreUtilities.cancelAppointment(appointmentId);
            status = "success";
        }
        catch (Exception e){
            System.out.println(e);
            status = "fail";       
            session.setAttribute("appointment_failed", "Could not cancel appointment!Please try again.");
        }
        // if(status.equals("success")){
		// 	response.sendRedirect("Appointments.jsp");
		// }
		// else{
			response.sendRedirect("GetAppointments");
		// }
	}
}
