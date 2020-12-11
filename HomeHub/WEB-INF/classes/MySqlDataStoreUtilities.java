import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.*;
import pojo.*;
import java.time.LocalDate;
import java.time.LocalTime;
import global.*;

public class MySqlDataStoreUtilities {
    static Connection conn = null;
    static String message;

    public static String getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/homehubsql", "root", "3306");
            System.out.println("Trying SQL connection.");
            message = "Successfull";
            return message;
        } catch (SQLException e) {
            System.out.println("SQL connection unsuccessful.");
            message = "unsuccessful";
            return message;

        } catch (Exception e) {
            message = e.getMessage();
            return message;
        }
    }

    public static HashMap < String, User > selectUser() {
        HashMap < String, User > hm = new HashMap < String, User > ();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String query = "select * from  User;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("firstName"));
                User user = new User(rs.getString("userId"), rs.getString("firstName"), rs.getString("middleName"), rs.getString("lastName"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), rs.getString("usertype"));
                hm.put(rs.getString("userId"), user);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hm;
    }

    public static void insertUser(User user) throws SQLException {
        try {
            getConnection();
            String query = "INSERT INTO User(userId,firstname,middlename,lastname,password,email,phone,usertype)" +
                "VALUES (?,?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, user.getUserId());
            pst.setString(2, user.getFirstName());
            pst.setString(3, user.getMiddleName());
            pst.setString(4, user.getLastName());
            pst.setString(5, user.getPassword());
            pst.setString(6, user.getEmail());
            pst.setString(7, user.getPhone());
            pst.setString(8, user.getUsertype());
            pst.execute();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public static HashMap < String, City > getCities() {
        HashMap < String, City > cities = new HashMap < String, City > ();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM City ";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                City city = new City(rs.getString("code"), rs.getString("name"), rs.getString("state"), rs.getString("country"));
                cities.put(rs.getString("code"), city);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return cities;
    }

    public static ArrayList < Professional > getProfessionals(String city, String category) throws Exception {
        ArrayList < Professional > professionals = new ArrayList < Professional > ();
        try {
            getConnection();

            String query = "select * from  professional where city=? and category=?";
            // String query = "select * from  professional";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, city);
            pst.setString(2, category);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Professional professional = new Professional(rs.getString("id"), rs.getString("firstName"), rs.getString("middleName"), rs.getString("lastName"), rs.getString("city"), rs.getString("email"), rs.getString("category"), rs.getString("phone"), rs.getString("street"), rs.getString("zip"), rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("image"));
                professionals.add(professional);
            }

        } catch (Exception e) {

            System.out.println(e);
        }

        return professionals;
    }

    public static void loadAllProfessionals() throws Exception {
        try {
            getConnection();
            String query = "select * from  professional";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Professional professional = new Professional(rs.getString("id"), rs.getString("firstName"), rs.getString("middleName"), rs.getString("lastName"), rs.getString("city"), rs.getString("email"), rs.getString("category"), rs.getString("phone"), rs.getString("street"), rs.getString("zip"), rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("image"));
                ProfessionalsHashMap.hm.put(rs.getString("id"),professional);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void loadAllServices() throws Exception {
        try {
            getConnection();
            String query = "select * from  service;";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Service service = new Service(rs.getString("serviceId"), rs.getString("serviceName"), rs.getString("category"), rs.getDouble("serviceCost"),rs.getDouble("discount"));
                ServicesHashMap.hm.put(rs.getString("serviceId"),service);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void loadAllCustomers() throws Exception {
        try {
            getConnection();
            String query = "select * from user where userType='customer';";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("userId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("phone"));
                CustomersHashMap.hm.put(rs.getString("userId"),user);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void scheduleAppointment(Appointment appointment) throws SQLException {
        try {
            getConnection();
            String query = "INSERT INTO appointment(appointmentId,userId,professionalId,serviceId,street,zip,serviceDetails,addInstructions,orderDate,orderTime,serviceDate,serviceTime,creditCard,serviceStatus,serviceCost,discount,finalCharges)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, appointment.getAppointmentId());
            pst.setString(2, appointment.getUserId());
            pst.setString(3, appointment.getProfessionalId());
            pst.setString(4, appointment.getServiceId());
            pst.setString(5, appointment.getStreet());
            pst.setString(6, appointment.getZip());
            pst.setString(7, appointment.getServiceDetails());
            pst.setString(8, appointment.getAddInstructions());
            Date orderDate = Date.valueOf(appointment.getOrderDate());
            pst.setDate(9, orderDate);
            Time orderTime = Time.valueOf(appointment.getOrderTime());
            pst.setTime(10, orderTime);
            Date serviceDate = Date.valueOf(appointment.getServiceDate());
            pst.setDate(11, serviceDate);
            Time serviceTime = Time.valueOf(appointment.getServiceTime());
            pst.setTime(12, serviceTime);
            pst.setString(13, appointment.getCreditCard());
            pst.setString(14, appointment.getServiceStatus());
            pst.setDouble(15, appointment.getServiceCost());
            pst.setDouble(16, appointment.getDiscount());
            pst.setDouble(17, appointment.getFinalCharges());
            pst.execute();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public static int getAppointmentsCount() throws Exception {
        int count = 0;
        try {
            getConnection();
            String query = "select * from appointment";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (Exception e) {
            throw e;
        }
        System.out.println(count);
        return count;
    }

    public static ArrayList < Appointment > getCustomerAppointments(String userId) throws Exception {
        ArrayList < Appointment > appointments = new ArrayList < Appointment > ();
        try {
            getConnection();

            String query = "select * from  appointment where userId=?";
            // String query = "select * from  professional";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                LocalDate orderDate = rs.getDate("orderDate").toLocalDate();
                LocalTime orderTime = rs.getTime("orderTime").toLocalTime();
                LocalDate serviceDate = rs.getDate("serviceDate").toLocalDate();
                LocalTime serviceTime = rs.getTime("serviceTime").toLocalTime();
                Appointment appointment = new Appointment(rs.getInt("appointmentId"), rs.getString("userId"), rs.getString("professionalId"),
                    rs.getString("serviceId"), rs.getString("street"), rs.getString("zip"), rs.getString("serviceDetails"),
                    rs.getString("addInstructions"), orderDate, orderTime, serviceDate, serviceTime,
                    rs.getString("creditCard"), rs.getString("serviceStatus"),
                    rs.getDouble("serviceCost"), rs.getDouble("discount"), rs.getDouble("finalCharges"));
                appointments.add(appointment);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return appointments;
    }

    public static ArrayList < Appointment > getProfessionalAppointments(String professionalId) throws Exception {
        ArrayList < Appointment > appointments = new ArrayList < Appointment > ();
        try {
            getConnection();

            String query = "select * from  appointment where professionalId=?";
            // String query = "select * from  professional";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, professionalId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                LocalDate orderDate = rs.getDate("orderDate").toLocalDate();
                LocalTime orderTime = rs.getTime("orderTime").toLocalTime();
                LocalDate serviceDate = rs.getDate("serviceDate").toLocalDate();
                LocalTime serviceTime = rs.getTime("serviceime").toLocalTime();
                Appointment appointment = new Appointment(rs.getInt("appointmentId"), rs.getString("userId"), rs.getString("professionalId"),
                    rs.getString("serviceId"), rs.getString("street"), rs.getString("zip"), rs.getString("serviceDetails"),
                    rs.getString("addInstructions"), orderDate, orderTime, serviceDate, serviceTime,
                    rs.getString("creditCard"), rs.getString("serviceStatus"),
                    rs.getDouble("serviceCost"), rs.getDouble("discount"), rs.getDouble("finalCharges"));
                appointments.add(appointment);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return appointments;
    }

    public static void rescheduleAppointment(int appointmentId, LocalDate newDate, LocalTime newTime) throws Exception {
        try {
            getConnection();
            String query = "update appointment set serviceDate=?, serviceTime=? where appointmentId=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setDate(1, Date.valueOf(newDate));
            pst.setTime(2, Time.valueOf(newTime));
            pst.setInt(3, appointmentId);
            pst.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void cancelAppointment(int appointmentId) throws Exception {
        try {
            getConnection();
            String query = "Delete from appointment where appointmentId=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, appointmentId);
            pst.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ArrayList<SalesReport> getSalesReport(){
        ArrayList<SalesReport> salesReportList = new ArrayList<SalesReport>();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String salesReportquery = "SELECT serviceName,name,sum(finalCharges) as totalRevenue FROM appointment INNER JOIN service ON appointment.serviceId = service.serviceId inner join professional on appointment.professionalId=professional.id INNER JOIN city ON city.code=professional.city GROUP BY serviceName,name;";
            ResultSet rs = stmt.executeQuery(salesReportquery);
            while (rs.next()) {
                SalesReport sr = new SalesReport(rs.getString("serviceName"), rs.getString("name"),rs.getDouble("totalRevenue"));
                salesReportList.add(sr);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return salesReportList;
    }

        public static ArrayList<SalesReport> getSalesReportforChart(){
        ArrayList<SalesReport> salesReportList = new ArrayList<SalesReport>();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String salesReportquery = "SELECT serviceName,sum(finalCharges) as totalRevenue FROM appointment INNER JOIN service ON appointment.serviceId = service.serviceId inner join professional on appointment.professionalId=professional.id GROUP BY serviceName;";
            ResultSet rs = stmt.executeQuery(salesReportquery);
            while (rs.next()) {
                SalesReport sr = new SalesReport(rs.getString("serviceName"), rs.getDouble("totalRevenue"));
                salesReportList.add(sr);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return salesReportList;
    }

    public static void rateAppointment(int appointmentId, int userRating) throws Exception {
        try {
            getConnection();
            String query = "update appointment set userRating=? where appointmentId=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, userRating);
            pst.setInt(2, appointmentId);
            pst.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}