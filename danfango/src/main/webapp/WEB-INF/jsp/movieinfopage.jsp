<%-- 
    Document   : movieinfopage
    Created on : Apr 3, 2017, 7:02:23 PM
    Author     : johnlegutko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

        <%--<jsp:include page="header.jsp" />--%>

        <jsp:include page="header.jsp" >
            <jsp:param name="contextPath" value="${contextPath}"/>
        </jsp:include>

        <div class="container">
            <h1 class="spacing movietitle">${movie.title}
                <c:if test="${user != null}">
                    <c:if test="${favoriteState == true}">
                        <form role="form" data-toggle="validator" action="/danfango/removeFavorite/${movie.id}" id="remFavorite" method="GET" style="display:inline">
                            <i id="favorite" class="fa fa-heart fa-inverse favoriteState" aria-hidden="true" onclick="document.getElementById('remFavorite').submit()"></i>
                        </form>
                    </c:if>
                    <c:if test="${favoriteState == false}">
                        <form role="form" data-toggle="validator" action="/danfango/addFavorite/${movie.id}" id="addFavorite" method="GET" style="display:inline">
                            <i id="favorite" class="fa fa-heart fa-inverse hi" aria-hidden="true" onclick="document.getElementById('addFavorite').submit()"></i>
                        </form>
                    </c:if>
                </c:if>
            </h1>

            <ul class="spacing nav nav-pills">
                <li class="active" role="presentation"><a href="/danfango/movieinfopage/${movie.id}"><h3>Overview</h3></a></li>
                <li role="presentation"><a href="/danfango/ticketselectpage/${movie.id}"><h3>Movie Times + Tickets</h3></a></li>
                <li role="presentation"><a href="/danfango/movietrailerpage/${movie.id}"><h3>Trailers</h3></a></li>
            </ul>

            <div class="row">
                <div class="col-sm-3 leftpadding">
                    <img class="movieposter" src="https://image.tmdb.org/t/p/w500/${movie.poster}"/>
                    <div class="movieInfo">
                        <fmt:parseDate value="${movie.releaseDate}" var="dateObject" pattern="yyyy-MM-dd HH:mm:ss"/>
                        <p><b>Release Date:</b> <fmt:formatDate value="${dateObject}" pattern="MM/dd/yyyy"/></p>
                        <p><b>Rating:</b> ${movie.rating}</p>
                        <p><b>Runtime:</b> ${movie.runTime}</p>
                        <p><b>Genre:</b> 
                            <c:forEach items="${genres}" var="genre">
                                ${genre.name} 
                            </c:forEach>      
                        </p>
                        <p><b>Score:</b> ${movie.movieScore}</p>
                    </div>
                    <a href="/danfango/ticketselectpage/${movie.id}" id="buyticketbutton" class="spacing btn-system btn-mini border-btn btn-gray">
                        <i class="fa fa-ticket" aria-hidden="true"></i><b>
                            BUY MOVIE TICKETS
                        </b><i class="fa fa-ticket" aria-hidden="true"></i>
                    </a>
                </div>


                <div class="col-md-4">
                    <div class="synopsis">
                        <p class="synopsisFont">${movie.synopsis}</p> 
                    </div>
                </div>

            </div>

            <hr>

            <!-- Start Recent Projects Carousel -->
            <div class="recent-projects">
                <h1 class="spacing movietitle">CAST + CREW</h1>
                <div class="spacing projects-carousel touch-carousel">

                    <c:forEach items="${crewMemberMovie}" var="crewMember" varStatus="loop">
                        <a href="/danfango/actorinfopage/${crewMember.crewMember.id}">
                            <div class="portfolio-item item">
                                <div class="portfolio-border">
                                    <div class="portfolio-thumb">
                                        <div class="thumb-overlay"></div>
                                        <img alt="" src="https://image.tmdb.org/t/p/w500/${crewMember.crewMember.poster}" />
                                    </div>
                                    <div class="portfolio-details">
                                        <h4>${crewMember.crewMember.fullName}</h4>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </c:forEach>

                </div>
            </div>
            <!-- End Recent Projects Carousel -->

            <!--            <h1 class="spacing movietitle">MOVIE NEWS</h1>-->
            <hr>

            <h1 class="spacing movietitle">MOVIE REVIEWS</h1>

            <div class="spacing row">
                <c:if test="${user != null}">
                    <div class="col-md-7">
                        <a href="" data-toggle="modal" data-target="#myModalMessage" id="leavereviewbutton"  class="spacing btn-system btn-mini border-btn btn-gray"><b>LEAVE A REVIEW</b></a>
                    </div>
                </c:if>
            </div>

            <div class="modal fade" id="myModalMessage" role="dialog">
                <div class="modal-dialog">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Tell Us What You Think!</h4>
                        </div>

                        <div class="modal-body">
                            <form role="form" data-toggle="validator" action = "/danfango/submitReview/${movie.id}" id="myReview" method="POST">

                                <fieldset class="rating">
                                    <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                                    <input type="radio" id="star4half" name="rating" value="4.5" /><label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
                                    <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                                    <input type="radio" id="star3half" name="rating" value="3.5" /><label class="half" for="star3half" title="Meh - 3.5 stars"></label>
                                    <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
                                    <input type="radio" id="star2half" name="rating" value="2.5" /><label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
                                    <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                                    <input type="radio" id="star1half" name="rating" value="1.5" /><label class="half" for="star1half" title="Meh - 1.5 stars"></label>
                                    <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                                    <input type="radio" id="starhalf" name="rating" value=".5" /><label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
                                </fieldset>

                                <input placeholder="Subject" type="text" class="form-control" name="reviewSubject" id="reviewSubject">

                                <textarea rows="10" cols="50" placeholder="Type Review Here" type="text" class="form-control" name="reviewContent" id="reviewContent"></textarea>                 

                                <input type="submit" value="submit" class="btn-system btn-large btn-gray joinbutton spacing">
                            </form>
                        </div>

                    </div>
                </div>
            </div>

            <c:forEach items="${reviews}" var="review" varStatus="loop">
                <div class="row spacing">
                    <div class="col-md-12">
                        <div class="reviewCard">
                            <h4 class="theatreTimeCardsName">${review.title} By ${review.user.firstName} ${review.user.lastName}</h4>
                            <p class="theatreTimeCardsAddress">${review.rating}/5</p>
                            <fieldset class="rating">
                                <input type="radio" id="star${review.rating}" name="rating" value="${review.rating}" />
                            </fieldset>
                            <p class ="ticketInfo">${review.content}</p>
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
