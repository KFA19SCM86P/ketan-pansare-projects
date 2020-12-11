package pojo;

public class SalesReport{
	private String serviceId;
	private String serviceName;
	private String city;
	private double totalRevenue;
	
	public SalesReport(String serviceName,String city,double totalRevenue){
		// this.serviceId = serviceId;
		this.serviceName=serviceName;
		this.city=city;
		this.totalRevenue = totalRevenue;
	}

	public SalesReport(String serviceName,double totalRevenue){
		// this.serviceId = serviceId;
		this.serviceName=serviceName;
		this.totalRevenue = totalRevenue;
	}

	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

    public double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
}
