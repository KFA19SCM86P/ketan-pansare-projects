<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
  </head>
 
  <!-- =======================================================
  * Template Name: Gp - v2.1.0
  * Template URL: https://bootstrapmade.com/gp-free-multipurpose-html-bootstrap-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
   <body>
    <div><%@ include file = "Header.jsp" %></div>
    <div class="row">
      <div class="col-8" >
      <img src="assets/img/loginimg.png" class="img-fluid"  style="margin-top: 70px" />
    </div>
      <div class="col-3" style="margin-top: 100px;margin-left: 50px">
        <div class="container border p-5">
          <h2>Register as a customer</h2>
          <% 
            if (session.getAttribute("registration_msg")!=null){
          %>
              <h4 style='color:red'>${registration_msg}</h4>
          <%}
              session.removeAttribute("registration_msg");
          %>
          <form method="post" action="Registration" >
            <label for="fname">First name :</label><br>
            <input type="text" id="fname" name="fname" value="" size="30" required><br>

            <label for="lname">Last name :</label><br>
            <input type="text" id="lname" name="lname" value="" size="30" required><br>

            <label for="userid">username:</label><br>
            <input type="text" id="username" name="userid" value="" size="30" required><br>

            <label for="email">email :</label><br>
            <input type="email" id="email" name="email" value="" size="30" required><br>

           <label for="contact">Contact No. :</label><br>
            <input type="text" id="contact" name="contact" size="30" value=""><br>

            <label for="password">password :</label><br>
            <input type="password" id="Password" name="password" value="" size="30" required><br>

            <label for="password">Confirm password</label><br>
            <input type="password" id="confirm_password" name="confirm" value="" size="30" required><br>
            <br/>
            <input type="submit" value="Submit" >
            
          </form> 
        <a href="Login.jsp">Click here to Login</a>
      </div>
    </div>
  </div>
</body>
</html>
