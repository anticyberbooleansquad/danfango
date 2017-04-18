<%-- 
    Document   : actorinfopage
    Created on : Apr 3, 2017, 6:39:10 PM
    Author     : johnlegutko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
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

        <jsp:include page="header.jsp" />

        <div class="container">

            <h1 class="spacing movietitle">Actor Name <font color="EA6630"><b>Overview</b></font></h1>

            <ul class="spacing nav nav-pills">


                <li class="active" role="presentation"><a href="/danfango/actorinfopage.html"><h3>Overview</h3></a></li>
                <li role="presentation"><a href="/danfango/actorinfopage.html"><h3>Biography</h3></a></li>
                <li role="presentation"><a href="/danfango/actorinfopage.html"><h3>Filmography</h3></a></li>
                <li role="presentation"><a href="/danfango/actorinfopage.html"><h3>Photos</h3></a></li>


            </ul>

            <div class="row">
                <div class="col-sm-3">
                    <img class="spacing movieposter" src="resources/images/actors/willarnet.jpg"/>
                </div>
                <div class="col-md-4">
                    <div class="synopsis"></div>
                </div>
            </div>



            <!-- Start Recent Projects Carousel -->
            <div class="recent-projects">
                <h1 class="spacing movietitle">FILMOGRAPHY</h1>
                <div class="spacing projects-carousel touch-carousel">

                    <a href="/danfango/movieinfopage.html">
                        <div class="portfolio-item item">
                            <div class="portfolio-border">
                                <div class="portfolio-thumb">
                                    <div class="thumb-overlay"></div>
                                    <img alt="" src="resources/images/movies/legobatman.jpg" />
                                </div>
                                <div class="portfolio-details">
                                    <h4>Movie Title</h4>
                                    <h5>Year</h5>
                                </div>
                            </div>
                        </div>
                    </a>

                    <a href="/danfango/movieinfopage.html">
                        <div class="portfolio-item item">
                            <div class="portfolio-border">
                                <div class="portfolio-thumb">
                                    <div class="thumb-overlay"></div>
                                    <img alt="" src="resources/images/movies/legobatman.jpg" />
                                </div>
                                <div class="portfolio-details">
                                    <h4>Movie Title</h4>
                                    <h5>Year</h5>
                                </div>
                            </div>
                        </div>
                    </a>

                    <a href="/danfango/movieinfopage.html">
                        <div class="portfolio-item item">
                            <div class="portfolio-border">
                                <div class="portfolio-thumb">
                                    <div class="thumb-overlay"></div>
                                    <img alt="" src="resources/images/movies/legobatman.jpg" />
                                </div>
                                <div class="portfolio-details">
                                    <h4>Movie Title</h4>
                                    <h5>Year</h5>
                                </div>
                            </div>
                        </div>
                    </a>

                    <a href="/danfango/movieinfopage.html">
                        <div class="portfolio-item item">
                            <div class="portfolio-border">
                                <div class="portfolio-thumb">
                                    <div class="thumb-overlay"></div>
                                    <img alt="" src="resources/images/movies/legobatman.jpg" />
                                </div>
                                <div class="portfolio-details">
                                    <h4>Movie Title</h4>
                                    <h5>Year</h5>
                                </div>
                            </div>
                        </div>
                    </a>

                    <a href="/danfango/movieinfopage.html">
                        <div class="portfolio-item item">
                            <div class="portfolio-border">
                                <div class="portfolio-thumb">
                                    <div class="thumb-overlay"></div>
                                    <img alt="" src="resources/images/movies/legobatman.jpg" />
                                </div>
                                <div class="portfolio-details">
                                    <h4>Movie Title</h4>
                                    <h5>Year</h5>
                                </div>
                            </div>
                        </div>
                    </a>


                </div>
            </div>
            <!-- End Recent Projects Carousel -->

        </div>
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
