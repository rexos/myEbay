<%-- 
    Document   : NotFound
    Created on : Feb 1, 2013, 1:38:01 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Not Found</title>
        <link rel="stylesheet" type="text/css" href="bootstrap.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-responsive.css">
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%@ page isErrorPage="true"%>
        <c:if test="${sessionScope.user != null}">
            <c:set var="name" value="${sessionScope.user.name}"></c:set>
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <c:set var="name" value="User"></c:set>
        </c:if>
        <div class="wrapper">
            <div class="page-header">
                <h1>File not Found</h1>
            </div>
            <div class="hero-unit">
                <div class="alert alert-error">
                    <h3>
                        <strong>
                            Dear ${name} the resource you are looking for
                            was not found on the server, or maybe it was deleted
                            because of some problem
                        </strong>
                    </h3>
                            </div>
                    <div class="alert alert-warning">
                        
                        <h4>
                            <strong>
                                Try to go back and recharge the page!
                            </strong>
                        </h4>
                    </div>
                    <c:if test="${sessionScope.user != null}">
                    <a href="User.jsp"><button class="btn btn-primary btn-large btn-block">
                            <i class="icon-repeat icon-white"></i>
                            <b> Back to Homepage</b>
                        </button></a>
                    </c:if>
                            <c:if test="${sessionScope.user == null}">
                    <a href="Login.html"><button class="btn btn-primary btn-large btn-block">
                            <i class="icon-repeat icon-white"></i>
                            <b> Back to Login Page</b>
                        </button></a>
                    </c:if>
                
            </div>
        </div>
    </body>
</html>
