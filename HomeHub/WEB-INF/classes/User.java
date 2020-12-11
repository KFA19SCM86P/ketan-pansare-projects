package pojo;

public class User {
	private String userId;
	private String firstName;
    private String middleName;
    private String lastName;
	private String password;
    private String email;
    private String phone;
	private String usertype = "customer";
	
	public User(String userId,String fn,String mn,String ln, String password, String email,String phone, String usertype) {
        this.userId = userId;
		this.firstName = fn;
        this.middleName = mn;
        this.lastName = ln;
		this.password = password;
        this.email = email;
        this.phone = phone;
		this.usertype = usertype;
	}

	public User(String userId,String fn,String ln, String password, String email,String phone) {
        this.userId = userId;
		this.firstName = fn;
        this.lastName = ln;
		this.password = password;
        this.email = email;
        this.phone = phone;
	}

	public User(String userId,String fn,String ln, String email,String phone) {
		System.out.println("I am callled................");
        this.userId = userId;
		this.firstName = fn;
        this.lastName = ln;
        this.email = email;
        this.phone = phone;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String id) {
		this.userId = id;
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

    
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
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


}
