import java.io.IOException;
import java.io.*;


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
	private String dateOfOrder;
	private String dateOfDelivery;
	private String cstreet;
	private String ccity;
	private String cstate;
	private String czipcode;
	
	public OrderPayment(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo, String cstreet, String ccity, String cstate, String czipcode)
	{
		this.orderId=orderId;
		this.userName=userName;
		this.orderName=orderName;
	 	this.orderPrice=orderPrice;
		this.userAddress=userAddress;
	 	this.creditCardNo=creditCardNo;
	 	this.cstreet=cstreet;
	 	this.ccity=ccity;
	 	this.cstate=cstate;
	 	this.czipcode=czipcode;
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

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(String dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	
	public String getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(String dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
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
	
	public String getcstreet() {
		return cstreet;
	}

	public void setcstreet(String cstreet) {
		this.cstreet = cstreet;
	}

	public String getccity() {
		return ccity;
	}

	public void setccity(String ccity) {
		this.ccity = ccity;
	}

	public String getcstate() {
		return cstate;
	}

	public void setcstate(String cstate) {
		this.cstate = cstate;
	}

	public String getczipcode() {
		return czipcode;
	}

	public void setczipcode(String czipcode) {
		this.czipcode = czipcode;
	}

}
