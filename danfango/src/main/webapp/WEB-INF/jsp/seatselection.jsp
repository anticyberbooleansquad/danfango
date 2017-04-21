<%-- 
    Document   : movienews
    Created on : Apr 3, 2017, 7:04:16 PM
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

        <div class="spacing container">
            

            <div class ="seats">
                <div class="row">
                    <button id="a1" type="button" class="btn btn-default seatButton seat-unselected">A1</button>
                    <button id="a2" type="button" class="btn btn-default seatButton seat-unselected">A2</button>
                    <button id="a3" type="button" class="btn btn-default seatButton seat-unselected">A3</button>
                    <button id="a4" type="button" class="btn btn-default seatButton seat-unselected">A4</button>
                    <button id="a5" type="button" class="btn btn-default seatButton seat-unselected">A5</button>
                    <button id="a6" type="button" class="btn btn-default seatButton seat-unselected">A6</button>
                    <button id="a7" type="button" class="btn btn-default seatButton seat-unselected">A7</button>
                    <button id="a8" type="button" class="btn btn-default seatButton seat-unselected">A8</button>
                    <button id="a9" type="button" class="btn btn-default seatButton seat-unselected">A9</button>
                    <button id="a10" type="button" class="btn btn-default seatButton seat-unselected">A10</button>
                </div>

                <div class="row">
                    <button id="b1" type="button" class="btn btn-default seatButton seat-unselected">B1</button>
                    <button id="b2" type="button" class="btn btn-default seatButton seat-unselected">B2</button>
                    <button id="b3" type="button" class="btn btn-default seatButton seat-unselected">B3</button>
                    <button id="b4" type="button" class="btn btn-default seatButton seat-unselected">B4</button>
                    <button id="b5" type="button" class="btn btn-default seatButton seat-unselected">B5</button>
                    <button id="b6" type="button" class="btn btn-default seatButton seat-unselected">B6</button>
                    <button id="b7" type="button" class="btn btn-default seatButton seat-unselected">B7</button>
                    <button id="b8" type="button" class="btn btn-default seatButton seat-unselected">B8</button>
                    <button id="b9" type="button" class="btn btn-default seatButton seat-unselected">B9</button>
                    <button id="b10" type="button" class="btn btn-default seatButton seat-unselected">B10</button>
                </div>

                <div class="row">
                    <button id="c1" type="button" class="btn btn-default seatButton seat-unselected">C1</button>
                    <button id="c2" type="button" class="btn btn-default seatButton seat-unselected">C2</button>
                    <button id="c3" type="button" class="btn btn-default seatButton seat-unselected">C3</button>
                    <button id="c4" type="button" class="btn btn-default seatButton seat-unselected">C4</button>
                    <button id="c5" type="button" class="btn btn-default seatButton seat-unselected">C5</button>
                    <button id="c6" type="button" class="btn btn-default seatButton seat-unselected">C6</button>
                    <button id="c7" type="button" class="btn btn-default seatButton seat-unselected">C7</button>
                    <button id="c8" type="button" class="btn btn-default seatButton seat-unselected">C8</button>
                    <button id="c9" type="button" class="btn btn-default seatButton seat-unselected">C9</button>
                    <button id="c10" type="button" class="btn btn-default seatButton seat-unselected">C10</button>
                </div>

                <div class="row">
                    <button id="d1" type="button" class="btn btn-default seatButton seat-unselected">D1</button>
                    <button id="d2" type="button" class="btn btn-default seatButton seat-unselected">D2</button>
                    <button id="d3" type="button" class="btn btn-default seatButton seat-unselected">D3</button>
                    <button id="d4" type="button" class="btn btn-default seatButton seat-unselected">D4</button>
                    <button id="d5" type="button" class="btn btn-default seatButton seat-unselected">D5</button>
                    <button id="d6" type="button" class="btn btn-default seatButton seat-unselected">D6</button>
                    <button id="d7" type="button" class="btn btn-default seatButton seat-unselected">D7</button>
                    <button id="d8" type="button" class="btn btn-default seatButton seat-unselected">D8</button>
                    <button id="d9" type="button" class="btn btn-default seatButton seat-unselected">D9</button>
                    <button id="d10"type="button" class="btn btn-default seatButton seat-unselected">D10</button>
                </div>

                <div class="row">
                    <button id="e1" type="button" class="btn btn-default seatButton seat-unselected">E1</button>
                    <button id="e2" type="button" class="btn btn-default seatButton seat-unselected">E2</button>
                    <button id="e3" type="button" class="btn btn-default seatButton seat-unselected">E3</button>
                    <button id="e4" type="button" class="btn btn-default seatButton seat-unselected">E4</button>
                    <button id="e5" type="button" class="btn btn-default seatButton seat-unselected">E5</button>
                    <button id="e6" type="button" class="btn btn-default seatButton seat-unselected">E6</button>
                    <button id="e7" type="button" class="btn btn-default seatButton seat-unselected">E7</button>
                    <button id="e8" type="button" class="btn btn-default seatButton seat-unselected">E8</button>
                    <button id="e9" type="button" class="btn btn-default seatButton seat-unselected">E9</button>
                    <button id="e10" type="button" class="btn btn-default seatButton seat-unselected">E10</button>
                </div>

                <div class="row">
                    <button id="f1" type="button" class="btn btn-default seatButton seat-unselected">F1</button>
                    <button id="f2" type="button" class="btn btn-default seatButton seat-unselected">F2</button>
                    <button id="f3" type="button" class="btn btn-default seatButton seat-unselected">F3</button>
                    <button id="f4" type="button" class="btn btn-default seatButton seat-unselected">F4</button>
                    <button id="f5" type="button" class="btn btn-default seatButton seat-unselected">F5</button>
                    <button id="f6" type="button" class="btn btn-default seatButton seat-unselected">F6</button>
                    <button id="f7" type="button" class="btn btn-default seatButton seat-unselected">F7</button>
                    <button id="f8" type="button" class="btn btn-default seatButton seat-unselected">F8</button>
                    <button id="f9" type="button" class="btn btn-default seatButton seat-unselected">F9</button>
                    <button id="f10" type="button" class="btn btn-default seatButton seat-unselected">F10</button>
                </div>

                <div class="row">
                    <button id="g1" type="button" class="btn btn-default seatButton seat-unselected">G1</button>
                    <button id="g2" type="button" class="btn btn-default seatButton seat-unselected">G2</button>
                    <button id="g3" type="button" class="btn btn-default seatButton seat-unselected">G3</button>
                    <button id="g4"type="button" class="btn btn-default seatButton seat-unselected">G4</button>
                    <button id="g5" type="button" class="btn btn-default seatButton seat-unselected">G5</button>
                    <button id="g6" type="button" class="btn btn-default seatButton seat-unselected">G6</button>
                    <button id="g7" type="button" class="btn btn-default seatButton seat-unselected">G7</button>
                    <button id="g8" type="button" class="btn btn-default seatButton seat-unselected">G8</button>
                    <button id="g9" type="button" class="btn btn-default seatButton seat-unselected">G9</button>
                    <button id="g10" type="button" class="btn btn-default seatButton seat-unselected">G10</button>
                </div>

                <div class="row">
                    <button id="h1" type="button" class="btn btn-default seatButton seat-unselected">H1</button>
                    <button id="h2" type="button" class="btn btn-default seatButton seat-unselected">H2</button>
                    <button id="h3" type="button" class="btn btn-default seatButton seat-unselected">H3</button>
                    <button id="h4" type="button" class="btn btn-default seatButton seat-unselected">H4</button>
                    <button id="h5" type="button" class="btn btn-default seatButton seat-unselected">H5</button>
                    <button id="h6" type="button" class="btn btn-default seatButton seat-unselected">H6</button>
                    <button id="h7" type="button" class="btn btn-default seatButton seat-unselected">H7</button>
                    <button id="h8" type="button" class="btn btn-default seatButton seat-unselected">H8</button>
                    <button id="h9" type="button" class="btn btn-default seatButton seat-unselected">H9</button>
                    <button id="h10" type="button" class="btn btn-default seatButton seat-unselected">H10</button>
                </div>

                <div class="row">
                    <button id="i1" type="button" class="btn btn-default seatButton seat-unselected">I1</button>
                    <button id="i2" type="button" class="btn btn-default seatButton seat-unselected">I2</button>
                    <button id="i3" type="button" class="btn btn-default seatButton seat-unselected">I3</button>
                    <button id="i4" type="button" class="btn btn-default seatButton seat-unselected">I4</button>
                    <button id="i5" type="button" class="btn btn-default seatButton seat-unselected">I5</button>
                    <button id="i6" type="button" class="btn btn-default seatButton seat-unselected">I6</button>
                    <button id="i7" type="button" class="btn btn-default seatButton seat-unselected">I7</button>
                    <button id="i8" type="button" class="btn btn-default seatButton seat-unselected">I8</button>
                    <button id="i9" type="button" class="btn btn-default seatButton seat-unselected">I9</button>
                    <button id="i10" type="button" class="btn btn-default seatButton seat-unselected">I10</button>
                </div>

                <div class="row">
                    <button id ="j1" type="button" class="btn btn-default seatButton seat-unselected">J1</button>
                    <button id ="j2" type="button" class="btn btn-default seatButton seat-unselected">J2</button>
                    <button id ="j3" type="button" class="btn btn-default seatButton seat-unselected">J3</button>
                    <button id ="j4" type="button" class="btn btn-default seatButton seat-unselected">J4</button>
                    <button id ="j5" type="button" class="btn btn-default seatButton seat-unselected">J5</button>
                    <button id ="j6" type="button" class="btn btn-default seatButton seat-unselected">J6</button>
                    <button id ="j7" type="button" class="btn btn-default seatButton seat-unselected">J7</button>
                    <button id ="j8" type="button" class="btn btn-default seatButton seat-unselected">J8</button>
                    <button id ="j9" type="button" class="btn btn-default seatButton seat-unselected">J9</button>
                    <button id ="j10" type="button" class="btn btn-default seatButton seat-unselected">J10</button>
                </div>
            </div>


            <button type="button" class="btn btn-default changeSeats">CHANGE SEATS</button>

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
