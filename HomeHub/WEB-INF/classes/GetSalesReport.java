import com.google.gson.Gson;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import pojo.SalesReport;


@WebServlet("/SalesReport")
public class GetSalesReport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<SalesReport> cityvisesalesReports = new ArrayList<SalesReport>();
        HttpSession session = request.getSession(true);
        try {
            cityvisesalesReports = MySqlDataStoreUtilities.getSalesReport();
            request.setAttribute("cityvisesalesReports",cityvisesalesReports);
            System.out.println("in servlet salesReport"+cityvisesalesReports);
            request.getRequestDispatcher("RevenueReport.jsp").forward(request,response);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/JSON");
        response.setCharacterEncoding("UTF-8");
        ArrayList<SalesReport> salesReports = new ArrayList<SalesReport>();
        HttpSession session = request.getSession(true);
        try {
            salesReports = MySqlDataStoreUtilities.getSalesReportforChart();
            System.out.println("in servlet salesReport"+salesReports);
            String salesReportJson = new Gson().toJson(salesReports);
            response.getWriter().write(salesReportJson);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // protected void displayPage(HttpServletRequest request, HttpServletResponse response, PrintWriter pw)
    //         throws ServletException, IOException {

    //     Utilities utility = new Utilities(request, pw);
    //     utility.printHtml("Header.html");
    //     utility.printHtml("LeftNavigationBar.html");

    //     ArrayList<SalesReportClass> salesReportList = new ArrayList<SalesReportClass>();

    //     try
    //     {
    //         salesReportList=MySqlDataStoreUtilities.getSalesReport();
    //     }
    //     catch(Exception e)
    //     {
    //         System.out.println(e);
    //     }


    //     ArrayList<DateWiseSales> dateWiseSalesList = new ArrayList<DateWiseSales>();

    //     try
    //     {
    //         dateWiseSalesList=MySqlDataStoreUtilities.getDateWiseSales();
    //     }
    //     catch(Exception e)
    //     {
    //         System.out.println(e);
    //     }




    //     //here write the code to display the sales table
    //     pw.print("<div id='content'><div class='post'>");

    //     pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Product wise Sales Report</a></h2>"
    //             + "<div class='entry'>");

    //     pw.print("<h3><button id='btnGetChartData'>View Sales Chart</h3>");
    //     pw.println("<div id='chart_div'></div>");

    //     pw.print("<table  class='gridtable'>");
    //     pw.print("<th>Product Name</th>");
    //     pw.print("<th>Price</th>");
    //     pw.print("<th>Items Sold</th>");
    //     pw.print("<th>Total Sales</th>");

    //     for (SalesReportClass sr : salesReportList)
    //     {
    //         pw.print("<tr>");						
    //         pw.print("<td>"+sr.getProductName()+"</td>"+
    //         "<td>"+sr.getProductPrice()+"</td>"+
    //         "<td>"+sr.getItemsSold()+"</td>"+
    //         "<td>"+sr.getTotalSales()+"</td>");
    //         pw.print("</tr>");
    //         // System.out.println(sr.getProductName()+" "+sr.getProductPrice()+" "+sr.getItemsSold()+" "+sr.getTotalSales());
    //         // System.out.println("\n");
    //     }
    //     pw.print("</table>");
    //     pw.println("</div>");

    //     pw.print("<br><br>");

    //     pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Date wise Sales Report</a></h2>"
    //             + "<div class='entry'>");
    //     pw.print("<table  class='gridtable'>");
    //     pw.print("<th>Date</th>");
    //     pw.print("<th>Total Sales</th>");

    //     for (DateWiseSales ds : dateWiseSalesList)
    //     {
    //         pw.print("<tr>");						
    //         pw.print("<td>"+ds.getDate()+"</td>"+
    //                 "<td>"+ds.getTotalSales()+"</td>");
    //         pw.print("</tr>");
    //         // System.out.println(ds.getDate()+" "+ds.getTotalSales());
    //         // System.out.println("\n");
    //     }
    //     pw.print("</table>");
    //     pw.println("</div>");

    //     pw.println("</div></div>");
    //     pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
    //     pw.println("<script type='text/javascript' src='SalesReport.js'></script>");
    //     utility.printHtml("Footer.html");

    // }
    // @Override
    // protected void doPost(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {

    //     try {
    //         ArrayList<SalesReportClass> salesReportList = MySqlDataStoreUtilities.getSalesReport();
            
    //         String salesReportJson = new Gson().toJson(salesReportList);
    //         // System.out.println(salesReportJson);


    //         response.setContentType("application/JSON");
    //         response.setCharacterEncoding("UTF-8");
    //         response.getWriter().write(salesReportJson);

            

    //     } catch (Exception ex) {
    //         System.out.println(ex.getMessage());
    //     }
    // }
}
