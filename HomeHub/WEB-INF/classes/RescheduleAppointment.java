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

@WebServlet("/RescheduleAppointment")

public class RescheduleAppointment extends HttpServlet {
	private String error_msg;

	/*   A json with user information is Obtained from HttpServletRequest variable and User Details are Added to the Users HashMap */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = "fail";
        HttpSession session = request.getSession(true);

        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        // System.out.println(appointmentId);
        // String serviceDetails = request.getParameter("serviceDetails");
        // String serviceDetails = "abcd";
        // String addInstructions = request.getParameter("addInstructions");
        // String addInstructions = "abcd";
        LocalDate serviceDate = LocalDate.parse(request.getParameter("serviceDate"));
        // System.out.println(serviceDate);
        LocalTime serviceTime = LocalTime.parse(request.getParameter("serviceTime"));
        // System.out.println(serviceTime);
        try{
            MySqlDataStoreUtilities.rescheduleAppointment(appointmentId,serviceDate,serviceTime);
            status = "success";
        }
        catch (Exception e){
            System.out.println(e);
            status = "fail";       
            session.setAttribute("appointment_failed", "Could not reschedule appointment!Please try again.");
        }
        // if(status.equals("success")){
		// 	response.sendRedirect("Appointments.jsp");
		// }
		// else{
			response.sendRedirect("GetAppointments");
		// }
	}
}
