<%-- 
    Document   : User
    Created on : Jan 22, 2013, 11:18:35 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
        <link rel="stylesheet" type="text/css" href="bootstrap.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-responsive.css">
        <link rel="stylesheet" type="text/css" href="style.css">
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap.js"></script>
    </head>
    <body>
        <div class="wrapper">
            <div class="navbar">
                <div class="navbar-inner">

                    <a class="brand"><i class="icon-user"></i> <c:out value="${sessionScope.user.name}"></c:out></a>
                    <ul class="nav">
                        <li class="active"><a><i class="icon-home"></i> Home</a></li>
                        <li><a href="Sell.jsp"><i class="icon-share"></i> Sell</a></li>
                        <li><a href="UserServ"><i class="icon-shopping-cart"></i> Auctions</a></li>
                        <li><a><i class="icon-calendar"></i> <c:out value="${sessionScope.date}"></c:out></a></li>

                    </ul>
                </div>
            </div>
            <div class="page-header">
                <h1><small>Welcome <c:out value="${sessionScope.user.name} ${sessionScope.user.surname}"></c:out></small>
                    <a href="checkLog" style="float : right">
                        <button class="btn btn-primary btn-default">
                            <i class="icon-off icon-white"></i>
                            <b> Logout ${sessionScope.user.name}</b>
                        </button>
                    </a>
                </h1>
            </div>
            <div class="hero-unit">
                <div id="myCarousel" class="carousel slide">
                    <!-- Carousel items -->
                    <div class="carousel-inner">
                        <div class="active item" style="height : 310px">
                            <br><br><br><br>
                            <center><h1><small>Slide to see your committees</small></h1></center>
                        </div>
                        <div class="item" style ="height : 300px">
                            <center><h2><small>Last 5 auctions created by ${sessionScope.user.name}</small></h2></center>
                            <table class="table table-bordered" style="width : 250px">
                                <tr><th>Item</th><th>Name</th><th>Category</th><th>Start Price</th></tr>
                                <c:set var="iter" value="${0}"></c:set>
                                <c:forEach var="p" items="${sessionScope.offered}">
                                    <c:if test="${iter <5}">
                                        <tr>
                                            <td>${p.id}</td>
                                            <td>${p.name}</td>
                                            <td>${p.category}</td>
                                            <td>${p.start_price}</td>
                                        </tr>
                                    </c:if>
                                    <c:set var="iter" value="${iter + 1}"></c:set>
                                </c:forEach>
                            </table>
                            <center><a href="UserLost.jsp"><button class="btn btn-default btn-info"><b>See All Lost</b></button></a></center>
                        </div>
                        <div class="item" style="height : 300px">
                            <center><h2><small>Last 5 Items purchased by ${sessionScope.user.name}</small></h2></center>
                            <table class="table table-bordered" style="width : 250px">
                                <tr><th>Item</th><th>Name</th><th>Category</th><th>Final Price</th></tr>
                                <c:set var="iter" value="${0}"></c:set>
                                <c:forEach var="a" items="${sessionScope.purchased}">
                                    <c:if test="${iter <5}">
                                        <tr>
                                            <td>${a.productid}</td>
                                            <td>${a.name}</td>
                                            <td>${a.category}</td>
                                            <td>${a.price}</td>
                                        </tr>
                                    </c:if>
                                    <c:set var="iter" value="${iter + 1}"></c:set>
                                </c:forEach>
                            </table>
                            <center><a href="UserPurchased.jsp"><button class="btn btn-default btn-info"><b>See All Won</b></button></a></center>
                        </div>
                    </div>
                    <!-- Carousel nav -->
                    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
                </div>
            </div>

        </div>
        <h1></h1>
    </body>
</html>
