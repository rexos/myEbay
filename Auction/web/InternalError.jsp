<%-- 
    Document   : InternalError
    Created on : Jan 31, 2013, 10:12:22 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <link rel="stylesheet" type="text/css" href="bootstrap.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-responsive.css">
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%@ page isErrorPage="true" %>
        <c:if test="${sessionScope.user != null}">
            <c:set var="name" value="${sessionScope.user.name}"></c:set>
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <c:set var="name" value="User"></c:set>
        </c:if>
        <div class="wrapper">
            <div class="page-header">
                <h1><small>An</small> Error <small>occurred while dispatching your request</small></h1>
            </div>

            <div class="hero-unit">
                <div class="alert alert-error"><h3><strong>
                        Dear ${name}, 
                        Something gone wrong during your inserted data elaboration, 
                        this may be corrected by trying to go back and reinsert 
                        information correctly (i.e. numeric values have to be written like this : 12.34)
                </strong></h3>
                <h4>
                        If this does not solve your problem we think that your pc is going 
                        to explode in less than a minute!
                </h4>
                </div>
                <div class="alert alert-warning"><h4>The server report says:</h4>
                <h4><%= exception.toString()%></h4></div>
                <center>
                    <a href="User.jsp"><button class="btn btn-primary btn-large btn-block">
                        <i class="icon-repeat icon-white"></i>
                        <b> Back to Homepage</b>
                    </button></a>
                </center>
            </div>
        </div>
    </body>
</html>
