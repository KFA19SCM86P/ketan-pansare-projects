package pojo;

public class Service{
	private String serviceId;
	private String serviceName;
    private String category;
    private double serviceCost;
    private double discount;
	
	public Service(String serviceId,String serviceName,String category) {
        this.serviceId = serviceId;
		this.serviceName = serviceName;
        this.category = category;
	}

    public Service(String serviceId,String serviceName,String category,double serviceCost, double discount) {
        this.serviceId = serviceId;
		this.serviceName = serviceName;
        this.category = category;
        this.serviceCost = serviceCost;
        this.discount = discount;
	}

	public Service() {
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


    public String getCategory() {
		return category;
	}
    public void setCategory(String category) {
		this.category = category;
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
}
