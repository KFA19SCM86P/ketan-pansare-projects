import java.io.IOException;
import java.io.*;
import java.util.Date;


/* 
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno
	  
	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class OrderPayment implements Serializable{
	private int orderId;
	private String userName;
	private String orderName;
	private double orderPrice;
	private String userAddress;
	private String creditCardNo;
	private int saleAmount;
    private Date orderTime;
    private String customerAge;
    private String customerCity;
    private String customerStreet;
    private String customerZip;
    private String deliveryType;
    private String deliveryLocation;
    private String expectedDate;
    private String retailerName;
    private String retType;
    //private String customerOccupation;


	
	public OrderPayment(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo, String customerAge,String customerCity,String customerStreet,String customerZip,String deliveryType,String deliveryLocation,String expectedDate,String retailerName,String retType){
		this.orderId=orderId;
		this.userName=userName;
		this.orderName=orderName;
	 	this.orderPrice=orderPrice;
		this.userAddress=userAddress;
	 	this.creditCardNo=creditCardNo;
	 	this.customerAge=customerAge;
	 	this.customerCity=customerCity;
	 	this.customerStreet=customerStreet;
	 	this.customerZip=customerZip;
	 	this.deliveryType=deliveryType;
	 	this.deliveryLocation=deliveryLocation;
	 	this.expectedDate=expectedDate;
	 	this.retailerName=retailerName;
	 	this.retType=retType;
	 	//this.customerOccupation=customerOccupation;
		}
	
		public OrderPayment(int orderId, String orderName, double orderPrice) {
			this.orderId = orderId;
			this.orderName = orderName;
			this.orderPrice = orderPrice;
		}
	
		public OrderPayment(int orderId, String orderName, double orderPrice, int saleAmount) {
			this.orderId = orderId;
			this.orderName = orderName;
			this.orderPrice = orderPrice;
			this.saleAmount = saleAmount;
		}
	
		public OrderPayment(String orderName, double orderPrice, int saleAmount) {
			this.orderName = orderName;
			this.orderPrice = orderPrice;
			this.saleAmount = saleAmount;
		}
	
		public OrderPayment(int saleAmount, Date orderTime) {
			this.saleAmount = saleAmount;
			this.orderTime = orderTime;
		}
	
		public Date getOrderTime() {
			return orderTime;
		}
	
		public void setOrderTime(Date orderTime) {
			this.orderTime = orderTime;
		}
	
		public int getSaleAmount() {
			return saleAmount;
		}
	
		public void setSaleAmount(int saleAmount) {
			this.saleAmount = saleAmount;
		}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getCustomerAge() {
		return customerAge;
	}

	public void setCustomerAge(String customerAge) {
		this.customerAge = customerAge;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerStreet() {
		return customerStreet;
	}

	public void setCustomerStreet(String customerStreet) {
		this.customerStreet = customerStreet;
	}
	public String getCustomerZip() {
		return customerZip;
	}

	public void setCustomerZip(String customerZip) {
		this.customerZip = customerZip;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDeliveryLocation() {
		return deliveryLocation;
	}

	// public void setCustomerOccupation(String customerOccupation) {
	// 	this.customerOccupation = customerOccupation;
	// }

	// public String getCustomerOccupation() {
	// 	return customerOccupation;
	// }


	public void setDeliveryLocation(String deliveryLocation) {
		this.deliveryLocation = deliveryLocation;
	}

	public String getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}

		public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getRetType() {
		return retType;
	}

	public void setRetType(String retType) {
		this.retType = retType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	

}
