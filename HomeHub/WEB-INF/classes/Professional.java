package pojo;

public class Professional{
	private String id;
	private String firstName;
    private String middleName;
    private String lastName;
    private String city;
	private String password;
    private String email;
	private String category;
    private String phone;
    private String street;
    private String zip;
    private double latitude;
    private double longitude;
    private String image;

	public Professional(String id,String fn,String mn,String ln, 
                String city,String email,String category,String phone,
                String street,String zip,double latitude,double longitude,
                String image) {
        this.id = id;
		this.firstName = fn;
        this.middleName = mn;
        this.lastName = ln;
        this.city = city;
        this.email = email;
        this.category = category;
        this.phone = phone;
        this.street = street;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String name) {
		this.firstName = name;
	}


    public String getMiddleName() {
		return middleName;
	}
    public void setMiddleName(String name) {
		this.middleName = name;
	}


    public String getLastName() {
		return lastName;
	}
	public void setLastName(String name) {
		this.lastName = name;
	}

    
	public String getPassword(){
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}


    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


    public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


    public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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

    public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

    public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

    public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}


}
