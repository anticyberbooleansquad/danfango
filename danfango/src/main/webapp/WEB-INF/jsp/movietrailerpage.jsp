<%-- 
    Document   : movietrailerpage
    Created on : Apr 3, 2017, 7:06:38 PM
    Author     : johnlegutko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/settings.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/font-awesome.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/slicknav.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/responsive.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/animate.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/colors/red.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/jquery.bxslider/jquery.bxslider.css"/>" rel="stylesheet">

        <link href="<c:url value="/resources/css/mycss.css"/>" rel="stylesheet">

    </head>
    <body>

        <!-- Full Body Container -->
        <div id="container">

            <!-- Start Header Section -->
            <header class="clearfix">
                <!-- Start  Logo & Naviagtion  -->
                <div class="navbar navbar-default navbar-top" role="navigation" data-spy="affix" data-offset-top="50">
                    <div class="container">
                        <div class="navbar-header">
                            <!-- Stat Toggle Nav Link For Mobiles -->
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                <i class="fa fa-bars"></i>
                            </button>
                            <!-- End Toggle Nav Link For Mobiles -->
                            <a href="/danfango/index.html">
                                <img id="danfangologo" alt="" src="resources/images/danfango-site-logo.jpg">
                            </a>
                        </div>
                        <div class="navbar-collapse collapse toppadding">
                            <!-- Start Search -->  
                            <input type="text" id="search" placeholder="Enter City + State, ZIP Code, or Movie" required data-error="Please enter valid info">
                            <a href="/danfango/searchresultspage.html" class="btn-system btn-mini border-btn btn-gray"><i class="icon-heart-4"></i>Search</a>

                            <!-- End Search -->

                            <!-- Start Navigation List -->
                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="/danfango/nowplaying.html">Movies</a>
                                </li>
                                <li>
                                    <a href="/danfango/ticketselectpage.html">Movie Times + Tickets</a>
                                </li>
                                <li>
                                    <a href="/danfango/movienews.html">Movie News</a>
                                </li>
                                <li>
                                    <a href="/danfango/signuppage.html">DanfangoVIP</a>
                                    <ul class="dropdown">

                                        <c:if test="${isloggedin == 1}">
                                            <li><a href="/danfango/logout.html">Sign-Out</a>
                                            </li>
                                        </c:if>


                                        <c:if test="${isloggedin != 1}">
                                            <li><a href="/danfango/signuppage.html">Join Now</a>
                                            </li>
                                            <li><a href="/danfango/signinpage.html">Sign-In</a>
                                            </li>
                                        </c:if>

                                    </ul>
                                </li>

                            </ul>
                            <!-- End Navigation List -->
                        </div>
                    </div>
                </div>
                <!-- End Header Logo & Naviagtion -->
            </header>
            <!-- End Header Section -->


            <div class="container">

                <h1 class="spacing movietitle">MOVIE TRAILERS</h1>

                <ul class="spacing nav nav-pills">
                    <li role="presentation"><a href="/danfango/movieinfopage.html"><h3>Overview</h3></a></li>
                    <li role="presentation"><a href="/danfango/ticketselectpage.html"><h3>Movie Times + Tickets</h3></a></li>
                    <li role="presentation"><a href="/danfango/movieinfopage.html"><h3>Synopsis</h3></a></li>
                    <li role="presentation"><a href="/danfango/#"><h3>Movie Reviews</h3></a></li>
                    <li role="presentation"><a href="/danfango/movietrailerpage.html"><h3>Trailers</h3></a></li>
                </ul>


                <iframe src="https://www.youtube.com/embed/djlsDykmZ_c""></iframe>


            </div>




            <!-- Start Footer Section -->
            <footer class="itemcenter">
                <div class="container">
                    <div class="footer-widget social-widget">
                        <h4>Anti-Graduation Squad</h4>
                        <h6>ft. John Legutko, Joseph Giardina, Konrad Juszkiewicz, Charles Bendernagel</h6>

                        <h4>Follow Us<span class="head-line"></span></h4>
                        <ul class="social-icons">
                            <li>
                                <a class="facebook" href="#"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li>
                                <a class="twitter" href="#"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li>
                                <a class="google" href="#"><i class="fa fa-google-plus"></i></a>
                            </li>
                            <li>
                                <a class="instgram" href="#"><i class="fa fa-instagram"></i></a>
                            </li>
                        </ul>
                    </div>
                    <!-- .row -->
                    <!-- Start Copyright -->
                    <div class="copyright-section">
                        <div class="row">
                            <div class="col-md-6">
                                <p>Copyright © 2016 Margo - Designed &amp; Developed by <a href="/danfango/http://graygrids.com">GrayGrids</a></p>
                            </div>
                            <!-- .col-md-6 -->
                            <div class="col-md-6">
                                <ul class="footer-nav">
                                    <li><a href="/danfango/#">Sitemap</a>
                                    </li>
                                    <li><a href="/danfango/#">Privacy Policy</a>
                                    </li>
                                    <li><a href="/danfango/#">Contact</a>
                                    </li>
                                </ul>
                            </div>
                            <!-- .col-md-6 -->
                        </div>
                        <!-- .row -->
                    </div>
                    <!-- End Copyright -->

                </div>
            </footer>
            <!-- End Footer Section -->


        </div>
        <!-- End Full Body Container -->

        <!-- Go To Top Link -->
        <a href="/danfango/#" class="back-to-top"><i class="fa fa-angle-up"></i></a>

        <div id="loader">
            <div class="spinner">
                <div class="dot1"></div>
                <div class="dot2"></div>
            </div>
        </div>


        <script src="<c:url value="/resources/js/jquery-2.1.4.min.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.migrate.js" />"></script>
        <script src="<c:url value="/resources/js/modernizrr.js" />"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.fitvids.js" />"></script>
        <script src="<c:url value="/resources/js/owl.carousel.min.js" />"></script>
        <script src="<c:url value="/resources/js/nivo-lightbox.min.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.isotope.min.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.appear.js" />"></script>
        <script src="<c:url value="/resources/js/count-to.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.textillate.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.lettering.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.easypiechart.min.js" />"></script>
        <script src="<c:url value="/resources/js/smooth-scroll.js" />"></script>
        <script src="<c:url value="/resources/js/skrollr.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.parallax.js" />"></script>
        <script src="<c:url value="/resources/js/mediaelement-and-player.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.slicknav.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.themepunch.revolution.min.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.themepunch.tools.min.js" />"></script>
        <script src="<c:url value="/resources/jquery.bxslider/jquery.bxslider.min.js" />"></script>
        <script src="<c:url value="/resources/js/myjs.js" />"></script>
        <script src="<c:url value="/resources/js/script.js" />"></script>

    </body>
</html>
