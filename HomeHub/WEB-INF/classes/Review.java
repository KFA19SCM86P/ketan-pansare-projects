package pojo;
import java.io.IOException;
import java.io.*;

/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
	private int appointmentId;
	private String userId;
	private String category;
	private String professionalId;
	private String serviceId;
	private String city;
	private double totalCharges;
	private int reviewRating;
	private String reviewText;
	private String reviewDate;

	public Review (int appointmentId,String userId,String category,
			String professionalId,String serviceId,String city,double totalCharges,
			int reviewRating,String reviewText,String reviewDate){
		this.appointmentId=appointmentId;
		this.userId=userId;
		this.category=category;
		this.professionalId= professionalId;
		this.serviceId = serviceId;
		this.city = city;
		this.totalCharges = totalCharges;
	 	this.reviewRating=reviewRating;
		this.reviewText=reviewText;
		this.reviewDate=reviewDate;
	}

	// public Review(String productName, String storeZip, String reviewRating, String reviewText) {
    //    this.productName = productName;
    //    this.storeZip = storeZip;
    //    this.reviewRating = reviewRating;
    //    this.reviewText = reviewText;
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


	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}


	public String getProfessionalId() {
		return professionalId;
	}
	public void setPofessionalId(String professionalId) {
		this.professionalId = professionalId;
	}


	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}


	public String getCity() {
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}


	public double getTotalCharges() {
		return totalCharges;
	}
	public void setTotalCharges(double totalCharges) {
		this.totalCharges = totalCharges;
	}


	public int getReviewRating() {
		return reviewRating;
	}
	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

}
