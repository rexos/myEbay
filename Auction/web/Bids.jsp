<%-- 
    Document   : Bids
    Created on : Jan 28, 2013, 10:59:49 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bids</title>
        <link rel="stylesheet" type="text/css" href="bootstrap.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-responsive.css">
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <div class="wrapper">
            <div class="page-header">
                <h1>
                    <small>Bids for Item number : </small><c:out value="${requestScope.prodid}"></c:out>
                    <c:if test="${sessionScope.test == 1}">
                        <a href="UserLost.jsp" style="float:right">
                            <button class="btn btn-default btn-primary"><i class="icon-ok icon-white"></i><b> Back</b></button>
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.test == 0}">
                        <a href="UserPurchased.jsp" style="float:right">
                            <button class="btn btn-default btn-primary"><i class="icon-ok icon-white"></i><b> Back</b></button>
                        </a>
                    </c:if>
                </h1>
            </div>
            <h1><small>Story of bids</small></h1>
            <div class="hero-unit">
                <table class="table table-striped">
                    <tr><th>Date</th><th>Hour</th><th>Offer</th><th>Offerer</th></tr>
                    <c:forEach var="bid" items="${requestScope.bids}">
                        <tr>
                            <td>${bid.date}</td>
                            <td>${bid.hour}</td>
                            <td>${bid.current}</td>
                            <td>${bid.userid}</td>
                        </tr>
                    </c:forEach>
                </table>

            </div>
        </div>
    </body>
</html>
