<%-- 
    Document   : index
    Created on : Apr 4, 2017, 4:09:44 PM
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

        <!-- Start Home Page Slider -->
        <section id="home">
            <!-- Carousel -->
            <div id="main-slide" class="carousel slide" data-ride="carousel">

                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#main-slide" data-slide-to="0" class="active"></li>
                    <li data-target="#main-slide" data-slide-to="1"></li>
                </ol>
                <!--/ Indicators end-->

                <!-- Carousel inner -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img class="img-responsive myFade" src="${contextPath}/resources/images/slider/movietheater2.jpg" alt="slider">
                        <div class="slider-content">
                            <div class="col-md-12 text-center">
                                <h2 class="animated2">
                                    <span>Welcome to <strong>Danfango</strong></span>
                                </h2>
                                <h3 class="animated3">
                                    <span>The ultimate movie site</span>
                                </h3>
                                </p>
                            </div>
                        </div>
                    </div>
                    <!--/ Carousel item end -->
                    <div class="item">
                        <img class="img-responsive" src="${contextPath}/resources/images/slider/people.jpg" alt="slider">
                        <div class="slider-content">
                            <div class="col-md-12 text-center">
                                <h2 class="animated4">
                                    <span><strong>Join</strong> our VIP Program</span>
                                </h2>
                                <h3 class="animated5">
                                    <span>For awesome benefits and rewards</span>
                                </h3>
                                <p class="animated6"><a href="/danfango/signuppage" class="slider btn btn-system btn-large">Join Now</a>
                                </p>
                            </div>
                        </div>
                    </div>
                    <!--/ Carousel item end -->
                </div>
                <!-- Carousel inner end-->

                <!-- Controls -->
                <a class="left carousel-control" href="#main-slide" data-slide="prev">
                    <span><i class="fa fa-angle-left"></i></span>
                </a>
                <a class="right carousel-control" href="#main-slide" data-slide="next">
                    <span><i class="fa fa-angle-right"></i></span>
                </a>
            </div>
            <!-- /carousel -->
        </section>
        <!-- End Home Page Slider -->

        <!-- Start Portfolio Section -->
        <div class="section full-width-portfolio sidepadding" style="border-top:0; border-bottom:0; background:#fff;">

            <!-- Start Big Heading -->
            <div class="big-title text-center" data-animation="fadeInDown" data-animation-delay="01">
                <h1 id="moviesheader">
                    <b>
                        <i class="fa fa-film" aria-hidden="true"></i>
                        Top Box Office
                        <i class="fa fa-film" aria-hidden="true"></i>
                    </b>
                </h1>
            </div>
            <!-- End Big Heading -->
            <c:forEach var="movie" items="${movies}" step="4" varStatus="loop">

                <div class="row spacing row-centered">

                    <div class = col-md-3 col-centered>
                        <c:if test="${movie.poster != null}">
                            <a class="" href="/danfango/movieinfopage/${movie.id}">
                                <img class ="posters" src="https://image.tmdb.org/t/p/w500/${movie.poster}">
                            </a>
                        </c:if>
                    </div>
                    <div class = col-md-3 col-centered>
                        <c:if test="${movies[loop.index+1].poster != null}">
                            <a class="" href="/danfango/movieinfopage/${movies[loop.index+1].id}">
                                <img class ="posters" src="https://image.tmdb.org/t/p/w500/${movies[loop.index+1].poster}">
                            </a>
                        </c:if>
                    </div>
                    <div class = col-md-3 col-centered>
                        <c:if test="${movies[loop.index+2].poster != null}">
                            <a class="" href="/danfango/movieinfopage/${movies[loop.index+2].id}">
                                <img class ="posters" src="https://image.tmdb.org/t/p/w500/${movies[loop.index+2].poster}">
                            </a>
                        </c:if>
                    </div>
                    <div class = col-md-3 col-centered>
                        <c:if test="${movies[loop.index+3].poster != null}">
                            <a class="" href="/danfango/movieinfopage/${movies[loop.index+3].id}">
                                <img class ="posters" src="https://image.tmdb.org/t/p/w500/${movies[loop.index+3].poster}">
                            </a>
                        </c:if>
                    </div>
                </div>
    
            </c:forEach>


        </div><!--row-->

    </div>

</div>
<!-- End Portfolio Section -->

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
