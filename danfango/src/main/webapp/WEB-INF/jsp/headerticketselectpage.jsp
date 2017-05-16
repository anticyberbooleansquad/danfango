<%-- 
    Document   : ticketselectpage
    Created on : Apr 3, 2017, 7:22:00 PM
    Author     : johnlegutko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <link href="<c:url value="/resources/css/jquery-ui-1.10.1.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/settings.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/font-awesome.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/slicknav.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/responsive.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/animate.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/colors/red.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/datepicker.css"/>" rel="stylesheet">
        <link href="<c:url value = "https://fonts.googleapis.com/css?family=Press+Start+2P|Roboto|Work+Sans:200|Josefin+Sans:100i" /> rel="stylesheet">
              <link href="<c:url value="/resources/jquery.bxslider/jquery.bxslider.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/mycss.css"/>" rel="stylesheet">

    </head>
    <body>

        <jsp:include page="header.jsp" >
            <jsp:param name="contextPath" value="${contextPath}"/>
        </jsp:include>

        <div class="spacing container">

            <h2 class="spacing movietitle padding">MOVIE THEATRES & TIMES FOR ${date}</h2>

            <form role="form" class="spacing" method="POST" action="/danfango/headerticketselectpage/date">
                <input type="date" id="showingDate" name="showingDate">
                <input type="submit">
            </form>

            <form role="form" class="spacing" method="POST" action="/danfango/headerticketselectpage/theatreFilter">
                <div class="form-group">
                    <label class="col-xs-3 control-label">Nearby Theatres:</label>
                    <div class="col-xs-5 selectContainer">
                        <select class="form-control" name="theatre">
                            <c:forEach items="${allTheatresList}" var="theatre">                        
                                <option value="${theatre.id}">${theatre.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </form>

            <c:forEach items="${theatreMovies}" var="theatreMovie">
                <div class="row form-group spacing">
                    <div class ="spacing col-sm-12">
                        <div class="theatreTimes">
                            <h4 class="theatreTimeCardsName">${theatreMovie.theatre.name} <i id="favorite" class="fa fa-heart fa-inverse" aria-hidden="true" ></i></h4>
                            <p class="theatreTimeCardsAddress">${theatreMovie.theatre.address}, ${theatreMovie.theatre.city} ${theatreMovie.theatre.state}, ${theatreMovie.theatre.zip}</p>
                            <div class="theatreTimeCardsTimes">

                                <c:forEach items="${theatreMovie.movieShowings}" var="movieShowing">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <img class="smallposters" alt="" src="https://image.tmdb.org/t/p/w500/${movieShowing.movie.poster}" />
                                        </div>
                                        <div class="col-md-3">
                                            <h1>${movieShowing.movie.title}</h1>
                                            <h4>Rating: ${movieShowing.movie.rating}</h4>
                                            <h4>RunTime: ${movieShowing.movie.runTime}</h4>
                                        </div>
                                        <div class="col-md-6">
                                            <p class ="ticketInfo"><i class="fa fa-ticket" aria-hidden="true"></i> Select a movie time to buy tickets</p>
                                            <c:forEach items="${movieShowing.showings}" var="showing">
                                                <fmt:parseDate value="${showing.time}" var="dateObject" pattern="yyyy-MM-dd HH:mm:ss" />
                                                <a href="/danfango/checkoutpage/${showing.id}" class="btn btn-primary timeButton">
                                                    <fmt:formatDate value="${dateObject}" pattern="hh:mm a"/>
                                                </a>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <hr>
                                </c:forEach>

                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>


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
    <script src="<c:url value="/resources/js/notify.min.js" />"></script>


    <script src="<c:url value="/resources/js/myjs.js" />"></script>
    <script src="<c:url value="/resources/js/script.js" />"></script>

</body>
</html>
