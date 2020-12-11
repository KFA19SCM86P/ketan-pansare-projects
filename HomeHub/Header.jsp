<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

   <head>
      <meta charset="utf-8">
      <meta content="width=device-width, initial-scale=1.0" name="viewport">
      <title>FixIt</title>
      <meta content="" name="descriptison">
      <meta content="" name="keywords">
      <!-- Favicons -->
      <link href="assets/img/favicon.png" rel="icon">
      <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
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
      <!-- =======================================================
         * Template Name: Gp - v2.1.0
         * Template URL: https://bootstrapmade.com/gp-free-multipurpose-html-bootstrap-template/
         * Author: BootstrapMade.com
         * License: https://bootstrapmade.com/license/
         ======================================================== -->
   </head>


   <body>
      <header id="header" class="fixed-top header-inner-pages">
         <div class="container d-flex align-items-center justify-content-between">
            <h1 class="col-xl-6 col-lg-8"><a href="Home.jsp">FixIt<span>.</span></a></h1>
            <!-- Uncomment below if you prefer to use an image logo -->
            <!-- <a href="index.html" class="logo"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
            <nav class="nav-menu d-none d-lg-block">
               <ul>


                  <li><a href="Home.jsp">Home</a></li>
                  
                  <li><a href="RegisterProvider.jsp">Register</a></li>

                  
                  <li><a href="TopRated">Trending</a></li>
                    <%
                     if (session.getAttribute("userId")!=null){
                    %>
                        <li><a href=''>Welcome ${firstname}</a></li>
                        <li><a href='GetAppointments'>My Appointments</a></li>
                        <%if (session.getAttribute("usertype").toString().equalsIgnoreCase("manager")){%>
                           <li><a href='SalesReport'>Revenue Report</a></li>
                        <%}%>
                        <li><a href='Logout'>Logout</a></li>
                    <%}
                     else{%>
                  <li><a href='Login.jsp'>Login/Sign Up</a></li>
                  <%}%>
               </ul>
            </nav>
         </div>
      </header>
   </body>

</html>