<%-- 
    Document   : Admin
    Created on : Jan 22, 2013, 7:37:53 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator</title>
        <link rel="stylesheet" type="text/css" href="bootstrap.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-responsive.css">
        <link rel="stylesheet" type="text/css" href="style.css">
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap.js"></script>
        <script type="text/javascript" src="carouselPause.js"></script>
    </head>
    <body>
        <div class="wrapper">
            <div class="navbar">
                <div class="navbar-inner">
                    <a class="brand" ><i class="icon-star"></i> <c:out value="${sessionScope.user.name}"></c:out></a>
                    <ul class="nav">
                        <li class="active"><a href="#"><i class="icon-home"></i> Home</a></li>
                        <li><a href="AdmminClosed.jsp"><i class="icon-ban-circle"></i> Closed Auctions</a></li>
                        <li><a><i class="icon-calendar"></i> <c:out value="${sessionScope.date}"></c:out></a></li>
                    </ul>
                </div>
            </div>
            <div class="page-header">
                <h1><small>Welcome Admin : ${sessionScope.user.name} ${sessionScope.user.surname}</small>
                    <a href="checkLog" style="float: right">
                        <button class="btn btn-primary btn-default">
                            <i class="icon-off icon-white"></i>
                            <b>Logout ${sessionScope.user.name}</b>
                        </button>
                    </a>
                </h1>
            </div>
            <div class="hero-unit">
                <div id="myCarousel" class="carousel slide">
                    <!-- Carousel items -->
                    <div class="carousel-inner">
                        <div class="active item" style="height : 300px">
                            <br><br><br><br>
                            <center><h1><small>Slide to see currently open auctions</small></h1></center>
                        </div>
                        <c:forEach var="a" items="${sessionScope.auctions}">
                            <div class="item" style ="height : 300px">
                                <table class="table table-bordered" style="width : 250px">
                                    <tr><th>Item</th><th>Vendor</th><th>Description</th><th>Current price</th>
                                    </tr>

                                    <tr>
                                        <td>${a.prodid}</td>
                                        <td>${a.sellerid}</td>
                                        <td>${a.description}</td>
                                        <td>${a.currentPrice}</td>
                                    </tr>

                                </table>
                                <c:set var="auctionid" value="${a.prodid}" scope="session"></c:set>
                                <center>
                                    <span class="label label-info">
                                            Type in the reason of the closure
                                        </span><br><br></center>
                                     
                                        <center><form action="AdminServ" method="POST">
                                            <input type="hidden" value="${a.prodid}" name="auctionid"/>
                                            <input type="text" name="message" autofocus/><br>
                                            <button class="btn btn-primary" type="submit"><i class="icon-envelope icon-white"></i><b> Close & Send</b></button>
                                        </form><center>
                                    
                                

                            </div>
                        </c:forEach>


                    </div>
                    <!-- Carousel nav -->
                    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
                </div>
            </div>
        </div>
    </body>
</html>
