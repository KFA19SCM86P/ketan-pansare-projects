package pojo;

public class City{
	private String code;
	private String name;
    private String state;
    private String country;
	
	public City(String code,String name,String state,String country) {
        this.code = code;
		this.name = name;
        this.state = state;
        this.country = country;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


    public String getState() {
		return state;
	}
    public void setState(String state) {
		this.state = state;
	}


    public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
