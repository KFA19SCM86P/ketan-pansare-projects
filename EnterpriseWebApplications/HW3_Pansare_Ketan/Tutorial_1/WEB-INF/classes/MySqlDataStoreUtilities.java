import java.sql.*;
import java.util.*;
                	
public class MySqlDataStoreUtilities
{
static Connection conn = null;

public static void getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewa","root","root");							
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}


public static void deleteOrder(int order_id,String p_name)
{
	try
	{
		
		getConnection();
		String deleteOrderQuery ="Delete from orders where order_id=? and p_name=?";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setInt(1,order_id);
		pst.setString(2,p_name);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			
	}
}

public static void insertOrder(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo)
{
	try
	{
	
		getConnection();
		String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,UserName,OrderName,OrderPrice,userAddress,creditCardNo) "
		+ "VALUES (?,?,?,?,?,?);";	
			
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		pst.setInt(1,orderId);
		pst.setString(2,userName);
		pst.setString(3,orderName);
		pst.setDouble(4,orderPrice);
		pst.setString(5,userAddress);
		pst.setString(6,creditCardNo);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}		
}

public static TreeMap<Integer, ArrayList<OrderPayment>> selectOrder()
{	

	TreeMap<Integer, ArrayList<OrderPayment>> orderPayments=new TreeMap<Integer, ArrayList<OrderPayment>>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from orders";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			if(!orderPayments.containsKey(rs.getInt("order_id")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("order_id"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("order_id"));		
			

			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("order_id"),rs.getString("u_name"),rs.getString("p_name"),rs.getDouble("price"),rs.getString("address"),rs.getString("credit_no"));
			order.setDateOfOrder(rs.getString("o_date"));
			order.setDateOfDelivery(rs.getString("d_date"));
			listOrderPayment.add(order);
					
		}
				
					
	}
	catch(Exception e)
	{
		
	}
	return orderPayments;
}


public static void insertUser(String username,String password,String usertype)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO users(uname,pwd,utype) "
		+ "VALUES (?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,usertype);
		pst.execute();
	}
	catch(Exception e)
	{
			e.printStackTrace();
	}	
}

public static void insertProduct(String p_id,String p_name,double price,String img_url,String manu,String p_condition,double discount,String p_type)
{
	try
	{	

		getConnection();
		String insertProduct = "INSERT INTO products(p_id,p_name,price,img_url,manu,p_condition,discount,p_type) "
		+ "VALUES (?,?,?,?,?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertProduct);
		pst.setString(1,p_id);
		pst.setString(2,p_name);
		pst.setDouble(3,price);
		pst.setString(4,img_url);
		pst.setString(5,manu);
		pst.setString(6,p_condition);
		pst.setDouble(7,discount);
		pst.setString(8,p_type);
		pst.execute();
	}
	catch(Exception e)
	{
			e.printStackTrace();
	}	
}

public static void editProduct(String p_id,String p_name,double price,String img_url,String manu,String p_condition,double discount,String p_type)
{
	try
	{	

		getConnection();
		String insertProduct = "UPDATE products set p_id = ? ,p_name = ?,price = ?,img_url = ?,manu = ?,p_condition = ?,discount = ?,p_type = ? "
		+ "WHERE p_id = ? and p_type = ? ;";	
				
		PreparedStatement pst = conn.prepareStatement(insertProduct);
		pst.setString(1,p_id);
		pst.setString(2,p_name);
		pst.setDouble(3,price);
		pst.setString(4,img_url);
		pst.setString(5,manu);
		pst.setString(6,p_condition);
		pst.setDouble(7,discount);
		pst.setString(8,p_type);
		pst.setString(9,p_id);
		pst.setString(10,p_type);
		
		pst.execute();
	}
	catch(Exception e)
	{
			e.printStackTrace();
	}	
}

public static void editSales(String id,String uname,String pname,String date)
{
	try
	{	

		getConnection();
		String insertProduct = "UPDATE orders set d_date = ?"
		+ "WHERE order_id = ? and u_name = ? and p_name = ?;";	
				
		PreparedStatement pst = conn.prepareStatement(insertProduct);
		pst.setString(1,date);
		pst.setString(2,id);
		pst.setString(3,uname);
		pst.setString(4,pname);
		
		pst.execute();
	}
	catch(Exception e)
	{
			e.printStackTrace();
	}	
}

public static void removeProduct(String p_id,String p_type)
{
	try
	{
		
		getConnection();
		String deleteOrderQuery ="Delete from products where p_id=? and p_type=?";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setString(1,p_id);
		pst.setString(2,p_type);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			
	}
}


public static boolean checkUser(String username,String usertype)
{	
	boolean empty = true;
	try
	{	

		getConnection();
		String checkUsers = "Select * from users where uname = ? and utype = ? ;";
				
		PreparedStatement pst = conn.prepareStatement(checkUsers);
		pst.setString(1,username);
		pst.setString(2,usertype);
		ResultSet rs = pst.executeQuery();
		
		empty = rs.next();

		
	}
	catch(Exception e)
	{
			e.printStackTrace();
	}	
	
	return empty;
}

public static boolean validateUser(String username,String password,String usertype)
{	
	boolean valid = true;
	try
	{	

		getConnection();
		String checkUsers = "Select * from users where uname = ? and pwd = ? and utype = ?  ;";
				
		PreparedStatement pst = conn.prepareStatement(checkUsers);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,usertype);
		
		ResultSet rs = pst.executeQuery();
		
		valid = rs.next();

		
	}
	catch(Exception e)
	{
			e.printStackTrace();
	}	
	
	return valid;
}

public static HashMap<String,Tv> selectTVs()
{	
	HashMap<String,Tv> hm=new HashMap<String,Tv>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  products where p_type = 'tv' ";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	Tv tv = new Tv(rs.getString("p_name"),rs.getDouble("price"),rs.getString("img_url"),rs.getString("manu"),rs.getString("p_condition"),rs.getDouble("discount"));
				tv.setId(rs.getString("p_id"));
				hm.put(rs.getString("p_id"), tv);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Laptop> selectLaptops()
{	
	HashMap<String,Laptop> hm=new HashMap<String,Laptop>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  products where p_type = 'laptop' ";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	Laptop laptop = new Laptop(rs.getString("p_name"),rs.getDouble("price"),rs.getString("img_url"),rs.getString("manu"),rs.getString("p_condition"),rs.getDouble("discount"));
				laptop.setId(rs.getString("p_id"));
				hm.put(rs.getString("p_id"), laptop);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Wireless> selectWl()
{	
	HashMap<String,Wireless> hm=new HashMap<String,Wireless>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  products where p_type = 'wireless' ";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	Wireless wireless = new Wireless(rs.getString("p_name"),rs.getDouble("price"),rs.getString("img_url"),rs.getString("manu"),rs.getString("p_condition"),rs.getDouble("discount"));
				wireless.setId(rs.getString("p_id"));
				hm.put(rs.getString("p_id"), wireless);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Va> selectVa()
{	
	HashMap<String,Va> hm=new HashMap<String,Va>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  products where p_type = 'va' ";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	Va va = new Va(rs.getString("p_name"),rs.getDouble("price"),rs.getString("img_url"),rs.getString("manu"),rs.getString("p_condition"),rs.getDouble("discount"));
				va.setId(rs.getString("p_id"));
				hm.put(rs.getString("p_id"), va);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Phone> selectPhone()
{	
	HashMap<String,Phone> hm=new HashMap<String,Phone>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  products where p_type = 'phone' ";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	Phone Phone = new Phone(rs.getString("p_id"),rs.getString("p_name"),rs.getDouble("price"),rs.getString("img_url"),rs.getString("manu"),rs.getString("p_condition"),rs.getDouble("discount"));
				hm.put(rs.getString("p_id"), Phone);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Wear> selectWear()
{	
	HashMap<String,Wear> hm=new HashMap<String,Wear>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  products where p_type = 'wear' ";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	Wear Wear = new Wear(rs.getString("p_name"),rs.getDouble("price"),rs.getString("img_url"),rs.getString("manu"),rs.getString("p_condition"),rs.getDouble("discount"));
				Wear.setId(rs.getString("p_id"));
				hm.put(rs.getString("p_id"), Wear);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  users";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("uname"),rs.getString("pwd"),rs.getString("utype"));
				hm.put(rs.getString("uname"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static ArrayList<Product> selectProduct()
{	
	ArrayList<Product> p=new ArrayList<>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  products";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	Product product = new Product(rs.getString("p_name"),rs.getDouble("price"),rs.getInt("p_count"));
				p.add(product);
		}
	}
	catch(Exception e)
	{
	}
	return p;			
}

public static ArrayList<Product> selectProductwithSale()
{	
	ArrayList<Product> p=new ArrayList<>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  products where discount > 0";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	Product product = new Product(rs.getString("p_name"),rs.getDouble("price"),rs.getInt("p_count"));
			product.setDiscount(rs.getDouble("discount"));
			p.add(product);
		}
	}
	catch(Exception e)
	{
	}
	return p;			
}

public static ArrayList<Product> selectProductwithRebate()
{	
	ArrayList<Product> p=new ArrayList<>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  products where rebate > 0";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	Product product = new Product(rs.getString("p_name"),rs.getDouble("price"),rs.getInt("p_count"));
			product.setRebate(rs.getInt("rebate"));
			p.add(product);
		}
	}
	catch(Exception e)
	{
	}
	return p;			
}

public static ArrayList<Product> selectSoldProduct()
{	
	ArrayList<Product> p=new ArrayList<>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  products where p_sold > 0";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	Product product = new Product(rs.getString("p_name"),rs.getDouble("price"),rs.getInt("p_count"));
			product.setSold(rs.getInt("p_sold"));
			p.add(product);
		}
	}
	catch(Exception e)
	{
	}
	return p;			
}

public static ArrayList<Product> selectSoldProductAll()
{	
	ArrayList<Product> p=new ArrayList<>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  products";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	Product product = new Product(rs.getString("p_name"),rs.getDouble("price"),rs.getInt("p_count"));
			product.setSold(rs.getInt("p_sold"));
			p.add(product);
		}
	}
	catch(Exception e)
	{
	}
	return p;			
}

public static ArrayList<Order> selectOrders()
{	
	ArrayList<Order> o=new ArrayList<>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  orders";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	Order order = new Order(rs.getString("p_name"),rs.getString("o_date"));
			
			o.add(order);
		}
	}
	catch(Exception e)
	{
	}
	return o;			
}

public static void saveProduct(int order_id,String u_name,String p_name,double price,String credit_no,String address,String o_date,String d_date)
{
	try
	{	

		getConnection();
		String saveProduct = "INSERT INTO orders(order_id,u_name,p_name,price,credit_no,address,o_date,d_date) "
		+ "VALUES (?,?,?,?,?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(saveProduct);
		pst.setInt(1,order_id);
		pst.setString(2,u_name);
		pst.setString(3,p_name);
		pst.setDouble(4,price);
		pst.setString(5,credit_no);
		pst.setString(6,address);
		pst.setString(7,o_date);
		pst.setString(8,d_date);
		pst.execute();
	}
	catch(Exception e)
	{
			e.printStackTrace();
	}	
}


	
}	