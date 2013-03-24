<%-- 
    Document   : UserLost
    Created on : Jan 29, 2013, 10:49:30 AM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lost</title>
        <link rel="stylesheet" type="text/css" href="bootstrap.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-responsive.css">
        <link rel="stylesheet" type="text/css" href="style.css">
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap.js"></script>
    </head>
    <body>
        <c:set var="test" value="${1}" scope="session"></c:set>
        <div class="wrapper">
            <div class="page-header">
                <h1><small>Section : Lost Auctions</small>
                    <a style="float:right" href="User.jsp">
                        <button class="btn btn-primary btn-default">
                            <i class="icon-ok icon-white"></i>
                            <b> Back</b>
                        </button>
                    </a>
                </h1>
            </div>
            <div class="hero-unit">
                <table class="table">
                    <tr class="error"><td>Item n</td><td>Name</td><td>Final price</td><td>Bids</td></tr>
                    <c:set var="iterator" value="0"></c:set> 
                    <c:forEach var="l" items="${sessionScope.lost}">
                        <c:if test="${iterator%2 == 0}">
                            <tr class="info">
                                <td>${l.productid}</td>
                                <td>${l.name}</td>
                                <td>${l.price}</td>
                                <td><a href="UserServ?showbid=${l.productid}">
                                        <button class="btn btn-mini btn-primary">
                                            <i class="icon-eye-open icon-white"></i>
                                            <b> See Bids</b>
                                        </button></a></td>
                            </tr>

                        </c:if>
                        <c:if test="${iterator%2 != 0}">
                            <tr class="warning">
                                <td>${l.productid}</td>
                                <td>${l.name}</td>
                                <td>${l.price}</td>
                                <td><a href="UserServ?showbid=${l.productid}">
                                        <button class="btn btn-mini btn-danger">
                                            <i class="icon-eye-open icon-white"></i>
                                            <b> See Bids</b>
                                        </button></a></td>
                            </tr>
                        </c:if>
                        <c:set var="iterator" value = "${iterator +1}"></c:set>
                        
                    </c:forEach>

                </table>
            </div>
        </div>
    </body>
</html>

