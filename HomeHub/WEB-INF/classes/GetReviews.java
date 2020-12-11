import java.io.IOException;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import pojo.Review;

@WebServlet("/GetReviews")

public class GetReviews extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HashMap < String, ArrayList < Review >> hm = MongoDBDataStoreUtilities.getReviews();
            request.setAttribute("reviews",hm);
            System.out.println("dhage"+hm);
            request.getRequestDispatcher("ViewReview.jsp").forward(request,response);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}