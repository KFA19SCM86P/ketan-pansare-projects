<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="pojo.Appointment"%>
<%@ page import="pojo.Professional"%>
<%@ page import="global.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>

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
          <h4>User Profile</h4>
          <ol>
            <li><a href="index.html">Home</a></li>
            <li>User Profile</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->
    
    <section class="inner-page">
      <div class="container">       
        
        <section id="team" class="team">         
          <h5>Customer Name:${firstname}</h5>
          <br/><br/>       
          <div>
            <h3>Appointments:</h3>
            <br/>
            <% ArrayList<Appointment> appointments= (ArrayList)request.getAttribute("appointments");
              HashMap<String,Professional> prohm = ProfessionalsHashMap.hm;
              System.out.println(appointments);
              %>
              <table class="table">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">A id</th>
                    <th scope="col">Date</th>
                    <th scope="col">Time</th>
                    <th scope="col">Service Provider</th>
                    <th scope="col">Price</th>
                    <th scope="col">Status</th>                    
                    <th scope="col">Review</th>
                    <th scope="col">Reschedule</th>
                    <th scope="col">Cancel</th>

                  </tr>
                </thead>
                <tbody>
                  <% for(int i=0;i<appointments.size();i++){
                      %>
                  <tr>
                    <th scope="row"><%=i+1%></th>
                    <td><%=appointments.get(i).getAppointmentId()%></td>
                    <td><%=appointments.get(i).getServiceDate()%></td>
                    <td><%=appointments.get(i).getServiceTime()%></td>
                    <% String profName = prohm.get(appointments.get(i).getProfessionalId()).getFirstName(); %>
                    <% profName += " "+prohm.get(appointments.get(i).getProfessionalId()).getLastName(); %>
                    <td><%=profName%></td>
                    <td><%=appointments.get(i).getServiceCost()%></td>
                    <td><%=appointments.get(i).getServiceStatus()%></td>

                    <%  String serviceStatus= appointments.get(i).getServiceStatus();
                    System.out.println(serviceStatus);
                    if(serviceStatus.equals("pending")){ %>
                      <td><i>Service Pending</i></td>
                      <!-- <td><a href="#">Reschedule</a></td> --><td><a href="Reschedule.jsp?serviceDate=<%=appointments.get(i).getServiceDate()%>&serviceTime=<%=appointments.get(i).getServiceTime()%>&appointmentId=<%=appointments.get(i).getAppointmentId()%>&AppointmentId=<%=appointments.get(i).getAppointmentId()%>">Reschedule</a></td>
                      <td><a href="CancelAppointment?appointmentId=<%=appointments.get(i).getAppointmentId()%>">Cancel</a></td>
                    <%} else{ %>
                    <td>
                      <a href="writeReview.jsp?appointmentId=<%=appointments.get(i).getAppointmentId()%>&professionalId=<%=appointments.get(i).getProfessionalId()%>&serviceId=<%=appointments.get(i).getServiceId()%>&totalCharges=<%=appointments.get(i).getServiceCost()%>&orderDate=<%=appointments.get(i).getOrderDate()%>">
                        write review
                      </a>
                    </td>
                    <td><i>Reschedule</i></td>
                    <td><i>Cancel</i></td>
                    <% } %>
                  </tr>
                  <% } %>
                  
                </tbody>
              </table>
          </div>
          <br/>
          <div >
            <h3>Recommended Professionls</h3>
            <div class="row" style="margin-top:70px;">          
                <div class="col-4 d-flex mt-2" data-aos="zoom-in" data-aos-delay="300">
                  <div class="icon-box" style="width: 300px;">
                    <div style="margin-top:-60px;margin-bottom: 10px;"><img src="assets/img/team/team-1.jpg" class="img-thumbnail"></div>
                    <h4><a href="Payment.jsp?e2">James Blunt</a></h4>
                     <a href=GetReviews?name=e2>View Reviews</a>
                    <p>NYC</p>
                    <form action="Payment.jsp">
                        <input type='hidden' name='professionalId' value='e2'>
                        <input type='hidden' name='serviceDate' value='2020-12-10'>
                        <input type='hidden' name='serviceTime' value='12:24'>
                        <input type='hidden' name='providerName' value='James Blunt'>
                        <input type='hidden' name='category' value='Electrician'>
                        
                        <button type="submit" class="btn btn-secondary">Select</button>
                    </form>
                  </div>
                </div>
                <div class="col-4 d-flex mt-2" data-aos="zoom-in" data-aos-delay="300">
                  <div class="icon-box" style="width: 300px;">
                    <div style="margin-top:-60px;margin-bottom: 10px;"><img src="assets/img/team/team-1.jpg" class="img-thumbnail"></div>
                    <h4><a href="Payment.jsp?e3">Chester Bennington</a></h4>
                     <a href=GetReviews?name=e3>View Reviews</a>
                    <p>NYC</p>
                    <form action="Payment.jsp">
                        <input type='hidden' name='professionalId' value='e3'>
                        <input type='hidden' name='serviceDate' value='2020-12-10'>
                        <input type='hidden' name='serviceTime' value='12:24'>
                        <input type='hidden' name='providerName' value='Chester Bennington'>
                        <input type='hidden' name='category' value='Electrician'>
                        
                        <button type="submit" class="btn btn-secondary">Select</button>
                    </form>
                  </div>
                </div>
                 <div class="col-4 d-flex mt-2" data-aos="zoom-in" data-aos-delay="300">
                  <div class="icon-box" style="width: 300px;">
                    <div style="margin-top:-60px;margin-bottom: 10px;"><img src="assets/img/team/team-1.jpg" class="img-thumbnail"></div>
                    <h4><a href="Payment.jsp?e5">Katy Perry</a></h4>
                     <a href=GetReviews?name=e5>View Reviews</a>
                    <p>NYC</p>
                    <form action="Payment.jsp">
                        <input type='hidden' name='professionalId' value='e5'>
                        <input type='hidden' name='serviceDate' value='2020-12-10'>
                        <input type='hidden' name='serviceTime' value='12:24'>
                        <input type='hidden' name='providerName' value='Katy Perry'>
                        <input type='hidden' name='category' value='Electrician'>
                        
                        <button type="submit" class="btn btn-secondary">Select</button>
                    </form>
                  </div>
                </div>
            </div>
            </div>
         
        </section>
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