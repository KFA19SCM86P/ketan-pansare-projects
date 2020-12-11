<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<head>
  

  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Inner Page - Gp Bootstrap Template</title>
  <meta content="" name="descriptison">
  <meta content="" name="keywords">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/icofont/icofont.min.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="assets/vendor/venobox/venobox.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/aos/aos.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">
  <link href="assets/css/mystyle.css" rel="stylesheet">
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>

  <!-- =======================================================
  * Template Name: Gp - v2.1.0
  * Template URL: https://bootstrapmade.com/gp-free-multipurpose-html-bootstrap-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>
 

<body>
  
 <%@ include file="Header.jsp" %>
  <%@ page import="pojo.Review" %>
  <%@ page import="java.util.HashMap" %>
  <%@ page import="java.util.ArrayList" %>
  <%@ page import="global.*"%>
  <%@ page import="pojo.User"%>
  <%@ page import="pojo.Professional"%>



  <main id="main">
    <section class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h4>Reviews</h4>
          <ol>
            <li><a href="index.html">Home</a></li>
            <li>Reviews</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->
    
    <section class="inner-page">
      <div class="container">       
        
        <section id="team" class="team">
          <div>
            <%HashMap<String,Professional> prohm = ProfessionalsHashMap.hm;
                      Professional prf = prohm.get(request.getParameter("name"));
                      String prfname = prf.getFirstName()+" "+prf.getLastName();%>
            <h4>Customer reviews for <%=prfname%></h4>
            <% String profId=request.getParameter("name");
            HashMap<String,ArrayList<Review>> allReviews= (HashMap)request.getAttribute("reviews");
              
              ArrayList<Review> reviews= (ArrayList)allReviews.get(profId);
              System.out.println("printing this"+reviews);
              %>
            <table class="table table-hover">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Name</th>
                  <th scope="col">Rating</th>
                  <th scope="col">Review</th>                  
                </tr>
              </thead>
              <tbody>
                <% if(reviews!=null){ 
                for(int i=0;i<reviews.size();i++){ %>
                <tr>
                  <th scope="row"><%=i+1%></th>
                  <%HashMap<String,User> custhm = CustomersHashMap.hm;%>
                    
                  <% User cust = custhm.get(reviews.get(i).getUserId()); %>
                  <% String custName = cust.getFirstName()+" "+cust.getLastName(); %>
                  <td width="150px"><%=custName%></td>
                  <td width="70px"><%=reviews.get(i).getReviewRating()%>/5</td>
                  <td><%=reviews.get(i).getReviewText()%></td>
                </tr>
                <% }} %>
                
              </tbody>
            </table>
          </div>
        </section><!-- End Team Section -->
        </div>
      </section>

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
 <%@ include file="Footer.jsp" %><!-- End Footer -->

  <a href="#" class="back-to-top"><i class="ri-arrow-up-line"></i></a>
  <div id="preloader"></div>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/jquery/jquery.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/jquery.easing/jquery.easing.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>
  <script src="assets/vendor/owl.carousel/owl.carousel.min.js"></script>
  <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="assets/vendor/venobox/venobox.min.js"></script>
  <script src="assets/vendor/waypoints/jquery.waypoints.min.js"></script>
  <script src="assets/vendor/counterup/counterup.min.js"></script>
  <script src="assets/vendor/aos/aos.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
 
</body>

</html>