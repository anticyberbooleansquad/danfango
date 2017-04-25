<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <c:set var="contextPath" value="resources/images"/>

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
                                <img id="danfangologo" alt="" src="${contextPath}/danfango-site-logo.jpg">
                            </a>
                        </div>
                        <div class="navbar-collapse collapse toppadding">
                            <!-- Start Search -->  
<!--                            <input type="text" id="search" placeholder="Enter City + State, ZIP Code, or Movie" required data-error="Please enter valid info">-->

                            <form role="form" id="searchForm" method="POST" action="/danfango/search">                                           
                                <input type="text" name= "searchString" id="searchString" class="search" placeholder="Enter City + State, ZIP Code, or Movie">
                                <input type="submit" value="search" class="btn-system btn-mini border-btn btn-gray">

                            </form>















                            <!--                            <a href="/danfango/searchresultspage.html" class="btn-system btn-mini border-btn btn-gray"><i class="icon-heart-4"></i>Search</a>-->

                            <!-- End Search -->

                            <!-- Start Navigation List -->
                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="/danfango/nowplaying">Movies</a>
                                </li>
                                <li>
                                    <a href="/danfango/ticketselectpage">Movie Times + Tickets</a>
                                </li>
                                <li>
                                    <a href="/danfango/movienews">Movie News</a>
                                </li>
                                <li>


                                    <c:if test="${sessionScope.user  != null}">
                                        <a href="/danfango/signuppage.html">DanfangoVIP (Welcome ${user.firstName})</a>
                                        <ul class="dropdown">
                                            <li><a href="/danfango/userpage.html">My Account</a>
                                            </li>
                                            <li><a href="/danfango/logout.html">Sign-Out</a>
                                            </li>
                                        </c:if>


                                        <c:if test="${sessionScope.user  == null}">
                                            <a href="/danfango/signuppage.html">DanfangoVIP</a>
                                            <ul class="dropdown">
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
    </body>
</html>