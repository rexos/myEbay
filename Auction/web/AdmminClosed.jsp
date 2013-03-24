<%-- 
    Document   : AdmminClosed
    Created on : Jan 27, 2013, 5:26:02 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Closed</title>
        <link rel="stylesheet" type="text/css" href="bootstrap.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-responsive.css">
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
 
        <div class="wrapper">
            <div class="navbar">
                <div class="navbar-inner">
                    <a class="brand"><i class="icon-star"></i> <c:out value="${sessionScope.user.name}"></c:out></a>
                    <ul class="nav">
                        <li><a href="AdminServ"><i class="icon-home"></i> Home</a></li>
                        <li class="active"><a href="">
                                <i class="icon-ban-circle"></i>
                                Closed Auctions
                            </a>
                        </li>
                        <li><a><i class="icon-calendar"></i> <c:out value="${sessionScope.date}"></c:out></a></li>
                    </ul>
                </div>
            </div>
            <div class="page-header">
                <h1><small>Section : Closed Auctions</small></h1>
            </div>
            <h1>
                <small>List of closed auctions</small>
            </h1>
            <div class="hero-unit">
                <table class="table">
                    <tr class="error"><td>Item n</td><td>Name</td><td>Final price</td><td>Auction Costs</td></tr>
                    <c:set var="iterator" value="0"></c:set> 
                    <c:forEach var="c" items="${sessionScope.closed}">
                        <c:if test="${iterator%2 == 0}">
                            <tr class="info">
                                <td>${c.productid}</td>
                                <td>${c.name}</td>
                                <td>${c.price}</td>
                                <td>${c.costs}</td>
                            </tr>
                        </c:if>
                        <c:if test="${iterator%2 != 0}">
                            <tr class="warning">
                                <td>${c.productid}</td>
                                <td>${c.name}</td>
                                <td>${c.price}</td>
                                <td>${c.costs}</td>
                            </tr>
                        </c:if>
                       <c:set var="iterator" value = "${iterator +1}"></c:set>
                    </c:forEach>
                   
                </table>
                    <center><a href="ExcelGenerator">
                            <button class="btn btn-success btn-large">
                                <i class="icon-download icon-white"></i>
                                <b> Download XLS</b>
                            </button>
                        </a></center>
            </div>
        </div>
    </body>
</html>
