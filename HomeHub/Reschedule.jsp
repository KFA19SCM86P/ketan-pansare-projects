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

  <main id="main">
    <section class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h4></h4>
          <ol>
            <li><a href="index.html">Home</a></li>
            <li>Service Providers</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->
    
    <section class="inner-page">
      <div class="container">       
       <!--  <div>
          <h3>Order Summary</h3>
          <p><b>Name: ${firstname}</b></p>
          <p><b>Price:</b></p>

          <p><b>Time Slot: <%=request.getParameter("time")%> </b></p>
          <p><b>Date: <%=request.getParameter("date")%> </b></p>
          <p><b>Service Provider: <%=request.getParameter("providerName")%></b></p>
          <p><b>City: ${city}</b></p>
        </div> -->
        


        <section id="team" class="team" style="margin-top: -70px;">
          <div>
              <h4>Reschedule Appointment</h4>
              <p> Your Previous appointment Date was on <%=request.getParameter("serviceDate")%>  </p>
              <p> Your Previous appointment Time was on <%=request.getParameter("serviceTime")%></p>
              <p> Appointment Id: <%=request.getParameter("AppointmentId")%></p>

          </div>
          <br>
          <h4>Schedule New Date</h4>
          <form method="POST" action="RescheduleAppointment">
            <div class="form-row">
              <div class="form-group col-md-4">
                <label class="control-label" for="date">Date</label>
                 <input class="form-control" id="date" name="serviceDate" placeholder="MM/DD/YYY" type="date" required/>
                <input type="hidden" value='<%=request.getParameter("AppointmentId")%>' name="appointmentId" >
                </div>
              <div class="form-group col-md-4">
                <label for="inputPassword4">Select Time Slot</label>
                <select id="time" name="serviceTime" class="form-control" required>
                  <option value="09:00:00">9:00 am</option>
                  <option value="10:00:00">10:00 am</option>
                  <option value="11:00:00">11:00 am</option>
                  <option value="12:00:00">12:00 pm</option>
                  <option value="13:00:00">1:00 pm</option>
                  <option value="14:00:00">2:00 pm</option>
                  <option value="15:00:00">3:00 pm</option>
                  <option value="16:00:00">4:00 pm</option>
                  <option value="17:00:00">5:00 pm</option>
                </select>
                
               <!-- <input type="time" class="form-control" id="inputPassword4" placeholder="2 am"> -->
              </div> 
            </div>
            <p>please select date and time slot</p>
            <div>
            <button type="submit" class="btn btn-secondary">Reschedule</button>
        </div>
          </form>

          </section><!-- End Team Section -->
        </div>
      </section>

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer">
  </footer><!-- End Footer -->

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