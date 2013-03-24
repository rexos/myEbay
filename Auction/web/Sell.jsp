<%-- 
    Document   : Sell
    Created on : Jan 23, 2013, 12:39:10 AM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sell</title>
        <link rel="stylesheet" type="text/css" href="bootstrap.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-responsive.css">
        <link rel="stylesheet" type="text/css" href="style.css">
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="formCheck.js"></script>
    </head>
    <body>
        <div class="wrapper">
            <div class="wrapper">
                <div class="navbar">
                    <div class="navbar-inner">

                        <a class="brand"><i class="icon-user"></i> <c:out value="${sessionScope.user.name}"></c:out></a>
                            <ul class="nav">
                                <li><a href="User.jsp"><i class="icon-home"></i> Home</a></li>
                                <li class="active"><a><i class="icon-share"></i> Sell</a></li>
                                <li><a href="UserServ"><i class="icon-shopping-cart"></i> Auctions</a></li>
                                <li><a><i class="icon-calendar"></i> <c:out value="${sessionScope.date}"></c:out></a></li>
                        </ul>
                    </div>
                </div>
                <div class="page-header">
                    <h1><small>Section : Create Auction</small></h1>
                </div>
            </div>
            <div class="hero-unit" style="padding-top: 20px">
                <h1><small>Fill in the form</small></h1>
                <form action="UploadServ" method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="type" value="ins"/>
                    <table id="tab">
                        <tr><td><label class="control-label">Product : </label></td><td><input type="text" name="name" id="product"/></td></tr>
                        <tr><td><label class="control-label">Quantity : </label></td><td><input type="text" name="quantity" id="quant"/></td></tr>
                        <tr><td><label class="control-label">Category : </label></td><td><input type="text" name="category" id="category"/></td></tr>
                        <tr><td><label class="control-label">Start Price : </label></td><td><input type="text" min ="0" max="9999" name="start_price" id="start"/></td></tr>
                        <tr><td><label class="control-label">Minimum Price : </label></td><td><input type="text" min ="0" max="9999" value="0" name="min_price" id="min"/></td></tr>
                        <tr><td><label class="control-label">Increase bid : </label></td><td><input type="text" min ="0" max="9999" name="increase" id="increase"/></td></tr>
                        <tr><td><label class="control-label">Send Price : </label></td><td><input type="text" min ="0" max="9999" name="send_price" id="send"/></td></tr>
                        <tr><td><label class="control-label">Description : </label></td><td><input type="text" name="description"></td></tr>
                        <tr><td><label class="control-label">Stop Auction : </label></td><td><input type="text" placeholder="yyyy/mm/dd" name="stop" id="stop"></td></tr>
                        <tr><td><label class="control-label">Hour to Stop : </label></td><td><input type="text" name="hour" placeholder="hh:mm:ss" id="hour"></td></tr>
                        <tr><td><label class="control-label">Upload Photo : </label></td><td><input type="file" name="photo"></td></tr><br>
                        <tr><td></td><td><button class="btn btn-default btn-primary" id="submit" type="submit"><i class="icon-ok icon-white"></i><b> Let's Go!</b></button></td></tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
