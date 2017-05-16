<%-- 
    Document   : userpage
    Created on : Apr 3, 2017, 7:25:06 PM
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

            <h2 class="spacing movietitle padding" style="text-align: center;">Hello ${user.firstName}</h2>

            <h2 class="spacing accountfont underline">Purchase History</h2>
            <c:forEach items="${userPurchases}" var="purchase" varStatus="loop">
                <div class="row spacing">
                    <div class="col-md-12">
                        <div class="reviewCard">
                            <h4 class="theatreTimeCardsName">${purchase.tickets[0].ticket.showing.movie.title}</h4>
                            <h3 class="theatreTimeCardsName">${purchase.order.orderDate}</h3>
                            <p class="theatreTimeCardsAddress">${purchase.tickets[0].ticket.showing.theatre.name}</p>
                            <p class ="ticketInfo">
                                <img class="xsmallposters" alt="" src="https://image.tmdb.org/t/p/w500/${purchase.tickets[0].ticket.showing.movie.poster}" />
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <hr>


            <h2 class="spacing accountfont underline">My Theaters</h2>

            <c:forEach items="${favoriteTheatres}" var="favoriteTheatre">
                <div class="row spacing">
                    <div class="col-md-7">
                        <div class="theatreTimes">
                            <h4 class="theatreTimeCardsName">${favoriteTheatre.theatre.name}</h4>
                            <p class="theatreTimeCardsAddress">${favoriteTheatre.theatre.address}, ${favoriteTheatre.theatre.city} ${favoriteTheatre.theatre.state}, ${favoriteTheatre.theatre.zip}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <hr>
            <!-- Start My Movies Carousel -->
            <h2 class="spacing accountfont underline">My Movies</h2>
            <div class="row">
                <div class="recent-projects">   
                    <div class="spacing projects-carousel touch-carousel">

                        <c:forEach items="${favoriteMovies}" var="favoriteMovie">
                            <a href="/danfango/movieinfopage/${favoriteMovie.movie.id}">
                                <div class="portfolio-item item">
                                    <div class="portfolio-border">
                                        <div class="portfolio-thumb">
                                            <div class="thumb-overlay"></div>
                                            <img alt="" src="https://image.tmdb.org/t/p/w500/${favoriteMovie.movie.poster}" />
                                        </div>
                                        <div class="portfolio-details">
                                            <h4>${favoriteMovie.movie.title}</h4>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <!-- End My Movies Carousel -->
            <hr>

            <h2 class="spacing accountfont underline">My Reviews</h2>

            <c:forEach items="${userReviews}" var="review" varStatus="loop">
                <div class="row spacing">
                    <div class="col-md-12">
                        <div class="reviewCard">
                            <h4 class="theatreTimeCardsName">${review.title}</h4>
                            <h3 class="theatreTimeCardsName">${review.movie.title}</h3>
                            <p class="theatreTimeCardsAddress">${review.rating}/5</p>
                            <p class ="ticketInfo">
                                <img class="posters" alt="" src="https://image.tmdb.org/t/p/w500/${review.movie.poster}" />

                                ${review.content}

                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <hr>

            <h2 class="spacing accountfont underline">Change Account Credentials</h2>
            ${UsedEmail}
            <form role="form" id="contactForm" class="signupform spacing" data-toggle="validator" class="shake" method="POST" action="/danfango/changeemail">
                <div class="controls">
                    <input type="text" name="email" id="email" placeholder="E-Mail" required data-error="Please enter your new email">
                    <div class="help-block with-errors"></div>
                </div>
                <input type="submit" value="Submit" class="btn-system btn-large btn-gray joinbutton spacing">

            </form>
            <form role="form" id="contactForm" class="signupform spacing" data-toggle="validator" class="shake" method="POST" action="/danfango/changepassword">
                <div class="controls">
                    <input type="text" name="password" id="password" placeholder="Password" required data-error="Please enter a new Password">
                    <div class="help-block with-errors"></div>
                </div>
                <input type="submit" value="Submit" class="btn-system btn-large btn-gray joinbutton spacing">
            </form>

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
