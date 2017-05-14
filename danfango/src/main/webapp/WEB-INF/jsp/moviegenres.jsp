<%-- 
    Document   : moviegenres
    Created on : Apr 3, 2017, 6:57:08 PM
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

        <jsp:include page="header.jsp" >
            <jsp:param name="contextPath" value="${contextPath}"/>
        </jsp:include>

        <div class="spacing container">

            <h2 class="spacing movietitle">MOVIE <font color="EA6630"><b>GENRES</b></font></h2>

            <ul class="spacing nav nav-pills">
                <li role="presentation"><a href="/danfango/nowplaying"><h3>Now Playing</h3></a></li>
                <li role="presentation"><a href="/danfango/comingsoon"><h3>Coming Soon</h3></a></li>
                <li class="active" role="presentation"><a href="/danfango/moviegenres"><h3>Movie Genres</h3></a></li>
                <li role="presentation"><a href="/danfango/athomedvd"><h3>At Home</h3></a></li>
            </ul>

            <div class="row">

                <div class="col-md-6 spacing list-group genretable">
                    <a href="/danfango/moviegenres/Action" class="list-group-item list-group-item-action">Action</a>
                    <a href="/danfango/moviegenres/Adventure" class="list-group-item list-group-item-action">Adventure</a>
                    <a href="/danfango/moviegenres/Animation" class="list-group-item list-group-item-action">Animation</a>
                    <a href="/danfango/moviegenres/Biography" class="list-group-item list-group-item-action">Biography</a>
                    <a href="/danfango/moviegenres/Comedy" class="list-group-item list-group-item-action">Comedy</a>
                    <a href="/danfango/moviegenres/Crime" class="list-group-item list-group-item-action">Crime</a>
                    <a href="/danfango/moviegenres/Documentary" class="list-group-item list-group-item-action">Documentary</a>
                    <a href="/danfango/moviegenres/Drama" class="list-group-item list-group-item-action">Drama</a>
                    <a href="/danfango/moviegenres/Family" class="list-group-item list-group-item-action">Family</a>
                    <a href="/danfango/moviegenres/Fantasy" class="list-group-item list-group-item-action">Fantasy</a>
                    <a href="/danfango/moviegenres/Film-Noir" class="list-group-item list-group-item-action">Film-Noir</a>
                </div>

                <div class="col-md-6 spacing list-group genretable">
                    <a href="/danfango/moviegenres/History" class="list-group-item list-group-item-action">History</a>
                    <a href="/danfango/moviegenres/Horror" class="list-group-item list-group-item-action">Horror</a>
                    <a href="/danfango/moviegenres/Music" class="list-group-item list-group-item-action">Music</a>
                    <a href="/danfango/moviegenres/Musical" class="list-group-item list-group-item-action">Musical</a>
                    <a href="/danfango/moviegenres/Mystery" class="list-group-item list-group-item-action">Mystery</a>
                    <a href="/danfango/moviegenres/Romance" class="list-group-item list-group-item-action">Romance</a>
                    <a href="/danfango/moviegenres/Sci-Fi" class="list-group-item list-group-item-action">Sci-Fi</a>
                    <a href="/danfango/moviegenres/Sport" class="list-group-item list-group-item-action">Sport</a>
                    <a href="/danfango/moviegenres/Thriller" class="list-group-item list-group-item-action">Thriller</a>
                    <a href="/danfango/moviegenres/War" class="list-group-item list-group-item-action">War</a>
                    <a href="/danfango/moviegenres/Western" class="list-group-item list-group-item-action">Western</a>
                </div>

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
