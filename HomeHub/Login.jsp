<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <meta content="width=device-width, initial-scale=1.0" name="viewport">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <title>FixIt</title>
      <meta content="text/html" name="descriptison">
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

      <%@ include file="Header.jsp" %>
    </head>
    <body>
      <div class="row">
         <!-- ======= Breadcrumbs ======= -->
         <div class="col-8" >
            <img src="assets/img/loginimg.png" class="img-fluid" style="margin-top: 60px" />
         </div>
         <div class="col-3" style="margin-top: 100px;margin-left: 50px">
            <div class="container border p-5">
               <h2>Login</h2>
               <form method="Post" action="Login">
                  <label for="userId">username:</label><br>
                  <input type="text" id="userId" name="userId" value="" size="30"><br>
                  <label for="password">Password</label><br>
                  <input type="password" id="password" name="password" value="" size="30"><br><br>
                  <input id="" type="submit" value="Login">
               </form>
               <% 
                if (session.getAttribute("login_err")!=null){
              %>
                  <h4 style='color:red'>${login_err}</h4>
              <%}
                  session.removeAttribute("login_err");
              if(session.getAttribute("registration_msg")!=null){%>
                <h4 style='color:green'>${registration_msg}</h4>
              <%}
                  session.removeAttribute("registration_msg");
              %>
               <a href="Registration.jsp"> New User? Register here</a>
            </div>
         </div>
      </div>
   <%@ include file = "footer.html" %>
  </body>
</html>
