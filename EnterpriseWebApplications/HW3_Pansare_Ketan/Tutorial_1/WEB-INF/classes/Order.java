import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


public class Order {
	private String name;
	private String o_date;
	
	
	
	public Order(String name, String o_date){
		this.name=name;
		this.o_date=o_date;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOdate() {
		return o_date;
	}
	public void setOdate(String o_date) {
		this.o_date=o_date;
	}
	
}
