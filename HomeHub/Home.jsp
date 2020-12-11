<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="pojo.SalesReport"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@ include file="Header.jsp" %>
        <title>Home</title>
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
        <link href="assets/css/mystyle.css" rel="stylesheet">
    </head>
    <script type="text/javascript" src="javascript.js"></script>
    <%
    if (session.getAttribute("city")!=null){
%>
    <body onload="init()">
        <!-- ======= Header ======= -->
        <!-- ======= Hero Section ======= -->
        <section id="hero" class="d-flex align-items-center justify-content-center">
          <div class="container" data-aos="fade-up">
            
            <div class="row justify-content-center" data-aos="fade-up" data-aos-delay="150">
              <div class="col-xl-6 col-lg-8">
                <h1>FixIt<span>.</span></h1>
                <h2>
                  <div class="row">
                    <div class="col-3">
                      <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <span  id="selected">
                          ${cityName}
                          </span>
                        </button>
                        <br/>
                        <br/>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                          <a class="dropdown-item" href="SetCity?city=CHI&cityName=Chicago" value="CHI">Chicago</a>
                          <a class="dropdown-item" href="SetCity?city=NYC&cityName=New York" value="NYC">New York</a>
                          <a class="dropdown-item" href="SetCity?city=LA&cityName=Los Angeles" value="LA">Los Angeles</a>
                          
                        </div>
                      </div>
                    </div>
                    <div class="col-9 input-group md-form form-sm form-1 pl-0">
                      <div class="input-group-prepend">
                        <!-- <span class="input-group-text cyan lighten-2" id="basic-text1"><i class="fas fa-search text-white" aria-hidden="true"></i></span> -->
                      </div>
                      <!-- <input class="form-control my-0 py-1" type="text" onkeyup=""placeholder="Search" aria-label="Search"> -->
                      <div name="autofillform">
                        <input type="text" name="searchId" value="" class="input"
                          id="searchId" onkeyup="doCompletion()"
                          placeholder="search here.." style="padding: 5px; font-size: 16px;" size="40" />
                        <div id="auto-row">
                          <table id="complete-table" class="gridtable"
                            style="position: absolute; width: 315px;"></table>
                        </div>
                      </div>
                    </div>
                  </div>
                </h2>
              </div>
            </div>
      
            <div class="row mt-5 justify-content-center" data-aos="zoom-in" data-aos-delay="250">
              <div class="col">
                <div class="icon-box">
                  <i class="ri-store-line"></i>
                  <!-- <h3><a data-toggle="modal" data-target="#electriciansModal">Electricians</a></h3> -->
                  <h3><a href="AvailableProfessionals?category=Electrician">Electricians</a></h3>
                </div>
              </div>
              <div class="col ">
                <div class="icon-box">
                  <i class="ri-bar-chart-box-line"></i>
                  <h3><a href="AvailableProfessionals?category=Plumber">Plumbers</a></h3>
                </div>
              </div>
              <div class="col ">
                <div class="icon-box">
                  <i class="ri-calendar-todo-line"></i>
                  <h3><a href="AvailableProfessionals?category=Painter">Painters</a></h3>
                </div>
              </div>
              <div class="col ">
                <div class="icon-box">
                  <i class="ri-paint-brush-line"></i>
                  <h3><a href="AvailableProfessionals?category=PestControl">Pest Control</a></h3>
                </div>
              </div>
              <div class="col ">
                <div class="icon-box">
                  <i class="ri-database-2-line"></i>
                  <h3><a href="AvailableProfessionals?category=Carpenter">Carpenters</a></h3>
                </div>
              </div>
              <div class="col ">
                <div class="icon-box">
                  <i class="ri-database-2-line"></i>
                  <h3><a href="AvailableProfessionals?category=Appliance">Appliances</a></h3>
                </div>
              </div>
              <div class="col ">
                <div class="icon-box">
                  <i class="ri-database-2-line"></i>
                  <h3><a href="AvailableProfessionals?category=Cleaning">Cleaning</a></h3>
                </div>
              </div>
            </div>
      
          </div>
        </section><!-- End Hero -->
      
        <main id="main">
      
          <!-- ======= About Section ======= -->
      
      
          <!-- ======= Clients Section ======= -->
      
      
          <!-- ======= Features Section ======= -->
      
      
          <!-- ======= Services Section ======= -->
          <section id="services" class="services">
            <div class="container" data-aos="fade-up">
      
              <div class="section-title">
                <h2>From Tweets</h2>
                <p>News Feed</p>
              </div>
      
              <div>
                <ul>
                <li><h5>We have some shocking deals on our shocking Electricians</h5></li>
                <li><h5>It's easy to think about getting organized, but sometimes it's hard to actually be organized. These are 10 things t… https://twitter.com/i/web/status/1313932621290897410 #Carpenters </h5></li>
                <li><h5>Keeping your place spotless has never been so easy. Download FixIton. Hire our professional Plumbers</h5></li> 
                </ul>         
              </div>
      
              
      
            </div>
          </section><!-- End Services Section -->
      
          <!-- ======= Cta Section ======= -->
    
        </main><!-- End #main -->
      
      
        <!--modals-->
        <div class="modal fade" id="electriciansModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Select the type of service:</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                
                <form action="AvailableProfessionals">
                  <div class="list-group">
                  <div class="radio list-group-item list-group-item-action">
                    <label><input type="radio" name="category" value="electrician">&nbsp;&nbsp;&nbsp;Repairs and fixes</label>
                  </div>
                  <div class="radio list-group-item list-group-item-action">
                    <label><input type="radio" name="category" value="electrician">&nbsp;&nbsp;&nbsp;Electricity Breakdown</label>
                  </div>
                  <div class="radio list-group-item list-group-item-action">
                    <label><input type="radio" name="category" value="electrician">&nbsp;&nbsp;&nbsp;Electrical Wiring</label>
                  </div>
                  <div class="radio list-group-item list-group-item-action">
                    <label><input type="radio" name="category" value="electrician">&nbsp;&nbsp;&nbsp;Installing Service</label>
                  </div>
                   <input class="mt-3" type="submit" value="Submit" style="background-color: #ffc451;">
                   </div>
               </form>
              </div>
      
            </div>
          </div>
        </div>
      
        
      
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
        <%@ include file="Footer.jsp" %>
      </body>
      <%}
      else{%>
        <body>
          

            <!-- ======= Header ======= -->
            <!-- ======= Hero Section ======= -->
            <section id="hero" class="d-flex align-items-center justify-content-center">
              <div class="container" data-aos="fade-up">
          
                <div class="row justify-content-center" data-aos="fade-up" data-aos-delay="150">
                  <div class="col-xl-6 col-lg-8">
                    <h1>FixIt<span>.</span></h1>
                    <h2>Where do you need a service?</h2>>
                    <h2>
                      <div class="row">
                        
                        <div class="col-4 mx-auto">
                          <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              
                              Select City
                              </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                              <a class="dropdown-item" href="SetCity?city=CHI&cityName=Chicago" value="CHI">Chicago</a>
                              <a class="dropdown-item" href="SetCity?city=NYC&cityName=New York" value="NYC">New York</a>
                              <a class="dropdown-item" href="SetCity?city=LA&cityName=Los Angeles" value="LA">Los Angeles</a>
                            </div>
                          </div>
                        </div>
                        
                      </div>
                    </h2>
                  </div>
                </div>
              </div>
            </section><!-- End Hero -->
          
            <main id="main">
          
              <!-- ======= About Section ======= -->
          
          
              <!-- ======= Clients Section ======= -->
          
          
              <!-- ======= Features Section ======= -->
          
          
              <!-- ======= Cta Section ======= -->  
          
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
            <%@ include file="Footer.jsp" %>
          </body>
      <%}%>

      

</html>
