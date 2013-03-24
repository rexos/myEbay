<%-- 
    Document   : UserPurchased
    Created on : Jan 28, 2013, 8:46:37 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Won</title>
        <link rel="stylesheet" type="text/css" href="bootstrap.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-responsive.css">
        <link rel="stylesheet" type="text/css" href="style.css">
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap.js"></script>
    </head>
    <body>
        <c:set var="test" value="${0}" scope="session"></c:set>
        <div class="wrapper">
            <div class="page-header">
                <h1><small>Section : Won Auctions</small>
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
                    <c:forEach var="p" items="${sessionScope.purchased}">
                        <c:if test="${iterator%2 == 0}">
                            <tr class="info">
                                <td>${p.productid}</td>
                                <td>${p.name}</td>
                                <td>${p.price}</td>
                                <td><a href="UserServ?showbid=${p.productid}">
                                        <button class="btn btn-mini btn-primary">
                                            <i class="icon-eye-open icon-white"></i>
                                            <b> See Bids</b>
                                        </button></a></td>
                            </tr>

                        </c:if>
                        <c:if test="${iterator%2 != 0}">
                            <tr class="warning">
                                <td>${p.productid}</td>
                                <td>${p.name}</td>
                                <td>${p.price}</td>
                                <td><a href="UserServ?showbid=${p.productid}">
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

