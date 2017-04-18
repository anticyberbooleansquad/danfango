<%-- 
    Document   : nowplaying
    Created on : Apr 3, 2017, 7:09:35 PM
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

        <jsp:include page="header.jsp" />

        <div class="container">

            <h1 class="spacing movietitle">MOVIES <font color="EA6630"><b>NOW PLAYING</b></font></h1>

            <ul class="spacing nav nav-pills">
                <li class="active" role="presentation"><a href="/danfango/nowplaying.html"><h3>Now Playing</h3></a></li>
                <li role="presentation"><a href="/danfango/comingsoon.html"><h3>Coming Soon</h3></a></li>
                <li role="presentation"><a href="/danfango/moviegenres.html"><h3>Movie Genres</h3></a></li>
                <li role="presentation"><a href="/danfango/athomedvd.html"><h3>At Home</h3></a></li>
            </ul>


            <h1 class="spacing accountfont underline">OPENING THIS WEEK</h1>

            <div class = "slider2">

                <div class="slide">
                    <div class="row spacing">

                        <div class = "col-md-3">
                            <a class="" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMzk1NzI1ODg3M15BMl5BanBnXkFtZTgwNzM0Mzc4MTI@._V1_SX300.jpg">
                            </a>
                        </div>
                        <div class = "col-md-3">
                            <a class="link" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMzJiNTI3MjItMGJiMy00YzA1LTg2MTItZmE1ZmRhOWQ0NGY1XkEyXkFqcGdeQXVyOTk4MTM0NQ@@._V1_SX300.jpg">
                            </a>
                        </div>
                        <div class = "col-md-3">
                            <a class="link" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMTUwNjUxMTM4NV5BMl5BanBnXkFtZTgwODExMDQzMTI@._V1_SX300.jpg">
                            </a>
                        </div>
                        <div class = "col-md-3">
                            <a class="link" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMTkwMTgwODAxMl5BMl5BanBnXkFtZTgwNTEwNTQ3MDI@._V1_SX300.jpg">
                            </a>
                        </div>


                    </div><!--row-->
                </div><!-- END SLIDER -->
                
                <div class="slide">
                    <div class="row spacing">

                        <div class = "col-md-3">
                            <a class="" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMTU1MTkxNzc5NF5BMl5BanBnXkFtZTgwOTM2Mzk3MTI@._V1_SX300.jpg">
                            </a>
                        </div>
                        <div class = "col-md-3">
                            <a class="link" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BNzI5MzM3MzkxNF5BMl5BanBnXkFtZTgwOTkyMjI4MTI@._V1_SX300.jpg">
                            </a>
                        </div>
                        <div class = "col-md-3">
                            <a class="link" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMTk2NjI5NzgwNl5BMl5BanBnXkFtZTgwNDc4NTA1OTE@._V1_SX300.jpg">
                            </a>
                        </div>
                        <div class = "col-md-3">
                            <a class="link" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMjI1MjkzMjczMV5BMl5BanBnXkFtZTgwNDk4NjYyMTI@._V1_SX300.jpg">
                            </a>
                        </div>


                    </div><!--row-->
                </div><!-- END SLIDER -->

                

            </div> <!--END MOVIE SLIDER -->

            <h1 class="spacing accountfont underline">NOW PLAYING</h1>

            <div class = "slider2">

                <div class="slide">
                    <div class="row spacing">

                        <div class = "col-md-3">
                            <a class="" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BNDUzOTE5OTk1NF5BMl5BanBnXkFtZTgwNzgwNzA4MDI@._V1_SX300.jpg">
                            </a>
                        </div>
                        <div class = "col-md-3">
                            <a class="link" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMzUxNjQ5MjAyOF5BMl5BanBnXkFtZTgwMjIzOTA1MDI@._V1_SX300.jpg">
                            </a>
                        </div>
                        <div class = "col-md-3">
                            <a class="link" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMjMwMzM4NTE5OV5BMl5BanBnXkFtZTgwMTAwNDU4MDI@._V1_SX300.jpg">
                            </a>
                        </div>
                        <div class = "col-md-3">
                            <a class="link" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMTcyNTEyOTY0M15BMl5BanBnXkFtZTgwOTAyNzU3MDI@._V1_SX300.jpg">
                            </a>
                        </div>


                    </div><!--row-->
                </div><!-- END SLIDER -->


                <div class="slide">
                    <div class="row spacing">

                        <div class = "col-md-3">
                            <a class="" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMjEwMzMxODIzOV5BMl5BanBnXkFtZTgwNzg3OTAzMDI@._V1_SX300.jpg">
                            </a>
                        </div>
                        <div class = "col-md-3">
                            <a class="link" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMjI3NzA1MDk4N15BMl5BanBnXkFtZTgwNzE2ODIyMTI@._V1_SX300.jpg">
                            </a>
                        </div>
                        <div class = "col-md-3">
                            <a class="link" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMjMxOTM1OTI4MV5BMl5BanBnXkFtZTgwODE5OTYxMDI@._V1_SX300.jpg">
                            </a>
                        </div>
                        <div class = "col-md-3">
                            <a class="link" href="movieinfopage.html">
                                <img class ="posters" src="https://images-na.ssl-images-amazon.com/images/M/MV5BMjQyODg5Njc4N15BMl5BanBnXkFtZTgwMzExMjE3NzE@._V1_SX300.jpg">
                            </a>
                        </div>


                    </div><!--row-->
                </div><!-- END SLIDE -->



            </div> <!--END MOVIE SLIDER -->
        </div> <!-- END MOVIE SLIDER CONTAINER -->

    </div><!--END OVERALL CONTAINER -->




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

