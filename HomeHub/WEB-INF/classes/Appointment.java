package pojo;

import java.time.LocalTime;
import java.time.LocalDate;

/* 
	Users class contains class variables id,name,password,usertype.

	Users class has a constructor with Arguments name, String password, String usertype.
	  
	Users  class contains getters and setters for id,name,password,usertype.

*/

public class Appointment {
    private int appointmentId;
	private String userId;
	private String professionalId;
	private String serviceId;
    private String street;
    private String zip;
	private String serviceDetails;
	private String addInstructions;
    private LocalDate orderDate;
	private LocalTime orderTime;
	private LocalDate serviceDate;
    private LocalTime serviceTime;
	private String creditCard;
    private String serviceStatus = "pending";
	private double serviceCost;
	private double discount;
	private double finalCharges;
	
	public Appointment(int appointmentId,String userId,String professionalId,String serviceId,
					String street,String zip,String serviceDetails,
					String addInstructions,LocalDate serviceDate,LocalTime serviceTime,String creditCard,
					double serviceCost,double discount,double finalCharges) {
        this.appointmentId = appointmentId;
		this.userId = userId;
		this.professionalId = professionalId;
		this.serviceId = serviceId;
        this.street = street;
        this.zip = zip;
		this.serviceDetails = serviceDetails;
		this.addInstructions = addInstructions;
		this.orderDate = LocalDate.now();
		this.orderTime = LocalTime.now();
		this.serviceDate = serviceDate;
		this.serviceTime = serviceTime;
		this.creditCard = creditCard;
		this.serviceCost = serviceCost;
		this.discount = discount;
		this.finalCharges = finalCharges;
	}

	public Appointment(int appointmentId,String userId,String professionalId,String serviceId,
					String street,String zip,String serviceDetails,
					String addInstructions,LocalDate orderdate, LocalTime orderTime,
					LocalDate serviceDate,LocalTime serviceTime,String creditCard, String serviceStatus,
					double serviceCost,double discount,double finalCharges) {
        this.appointmentId = appointmentId;
		this.userId = userId;
		this.professionalId = professionalId;
		this.serviceId = serviceId;
        this.street = street;
        this.zip = zip;
		this.serviceDetails = serviceDetails;
		this.addInstructions = addInstructions;
		this.orderDate = orderdate;
		this.orderTime = orderTime;
		this.serviceDate = serviceDate;
		this.serviceTime = serviceTime;
		this.creditCard = creditCard;
		this.serviceStatus = serviceStatus;
		this.serviceCost = serviceCost;
		this.discount = discount;
		this.finalCharges = finalCharges;
	}

	// public User(String userId,String fn,String ln, String password, String email,String phone) {
    //     this.userId = userId;
	// 	this.firstName = fn;
    //     this.lastName = ln;
	// 	this.password = password;
    //     this.email = email;
    //     this.phone = phone;
	// }

	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProfessionalId(){
		return professionalId;
	}
	public void setProfessionalId(String professionalId) {
		this.professionalId = professionalId;
	}

	public String getServiceId(){
		return serviceId;
	}
	public void setServiceId(String ServiceId) {
		this.serviceId = serviceId;
	}

    public String getStreet() {
		return street;
	}
    public void setStreet(String street) {
		this.street = street;
	}

    public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getServiceDetails() {
		return serviceDetails;
	}
	public void setServiceDetails(String serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public String getAddInstructions() {
		return addInstructions;
	}
	public void setAddInstructions(String addInstructions) {
		this.addInstructions = addInstructions;
	}
    
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate date) {
		this.orderDate = date;
	}

	public LocalTime getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}

	public LocalDate getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(LocalDate serviceDate) {
		this.serviceDate = serviceDate;
	}

    public LocalTime getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(LocalTime serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

    public String getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}


	public double getServiceCost() {
		return serviceCost;
	}
	public void setServiceCost(double serviceCost) {
		this.serviceCost = serviceCost;
	}


	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getFinalCharges() {
		return finalCharges;
	}
	public void setFinalCharges(double finalCharges) {
		this.finalCharges = finalCharges;
	}
}
