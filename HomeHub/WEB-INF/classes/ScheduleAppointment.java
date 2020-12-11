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

@WebServlet("/ScheduleAppointment")

public class ScheduleAppointment extends HttpServlet {
	private String error_msg;

	/*   A json with user information is Obtained from HttpServletRequest variable and User Details are Added to the Users HashMap */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = "fail";
        HttpSession session = request.getSession(true);

        int appointmentId = Utilities.getTotalAppointments()+1;
        // System.out.println(appointmentId);
        String userId = session.getAttribute("userId").toString();
        // System.out.println(userId);
        String professionalId = request.getParameter("professionalId");
        // System.out.println(professionalId);
        String serviceId = request.getParameter("serviceId");
        // System.out.println(serviceId);
        String street = request.getParameter("street");
        // System.out.println(street);
        String zip = request.getParameter("zip");
        // System.out.println(zip);
        // String serviceDetails = request.getParameter("serviceDetails");
        String serviceDetails = "abcd";
        // String addInstructions = request.getParameter("addInstructions");
        String addInstructions = "abcd";
        LocalDate serviceDate = LocalDate.parse(request.getParameter("serviceDate"));
        // System.out.println(serviceDate);
        LocalTime serviceTime = LocalTime.parse(request.getParameter("serviceTime"));
        // System.out.println(serviceTime);
        String creditCard = request.getParameter("creditCard");
        // System.out.println(creditCard);
        double serviceCost= 100.00;
        double discount = 10.00;
        double finalCharges= serviceCost - discount;
        Appointment appointment = new Appointment(appointmentId,userId,professionalId,serviceId,street,zip,
                                                serviceDetails,addInstructions,serviceDate,serviceTime,creditCard,
                                                serviceCost, discount, finalCharges);

        try{
            MySqlDataStoreUtilities.scheduleAppointment(appointment);
            status = "success";
        }
        catch (Exception e){
            System.out.println(e);
            status = "fail";       
            session.setAttribute("appointment_failed", "Could not schedule appointment!Please try again.");
        }
        // if(status.equals("success")){
		// 	response.sendRedirect("Appointments.jsp");
		// }
		// else{
			response.sendRedirect("GetAppointments");
		// }
	}
}
