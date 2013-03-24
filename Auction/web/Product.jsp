<%-- 
    Document   : Product.jsp
    Created on : Jan 23, 2013, 9:44:07 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${requestScope.product.name}"></c:out></title>
            <link rel="stylesheet" type="text/css" href="bootstrap.css">
            <link rel="stylesheet" type="text/css" href="bootstrap-responsive.css">
            <link rel="stylesheet" type="text/css" href="style.css">
            <script type="text/javascript" src="jquery.js"></script>
            <script type="text/javascript" src="bootstrap.js"></script>
            <script type="text/javascript" src="betMatch.js"></script>
        </head>
        <body>
            <div class="wrapper">
                <div class="page-header">
                    <h1>
                        <small>Auction for </small><c:out value="${requestScope.product.name}"></c:out>
                        <a href="UserServ" style="float : right">
                            <button class="btn btn-primary btn-default">
                                <i class="icon-ok icon-white"></i>
                                <b> Back</b>
                            </button>
                        </a>
                    </h1>
                </div>
                <center><div class="hero-unit" style="width: 220px; height : 220px">
                        <img class="img-rounded" src="http://localhost${requestScope.product.photo}" style="width: 210px; height : 210px">
                </div></center>
                <c:if test="${requestScope.product.isopen != 0}">
                <div class="alert alert-error"><strong><h2>This auction has been closed by an Administrator and will be soon removed</h2></strong></div> 
            </c:if>
            <c:if test="${sessionScope.date == requestScope.product.stop && requestScope.product.isopen == 0}">
                <div class="alert alert-warning"><strong><h3>Heads Up! This auction will be closed today! So place your offers quickly!</h3></strong></div> 
            </c:if>
            <h1><small>General infos  </small></h1>
            <div class="hero-unit">
                <div class="row">
                    <div class="span2" style="float:left">

                        <h4>Description</h4>
                        <h3><small><c:out value="${requestScope.product.description}"></c:out></small></h3>
                        </div>
                        <div class="span2" style="float:left">
                            <h4>Category</h4>
                            <h3><small><c:out value="${requestScope.product.category}"></c:out></small></h3>   
                        </div>
                        <div class="span2" style="float:left">
                            <h4>Quantity</h4>
                            <h3><small><span class="badge badge-info"><c:out value="${requestScope.product.quantity}"></c:out></span> piece/s</small></h3>   
                        </div>
                        <div class="span2" style="float:left">
                            <h4>Starting Price</h4>
                            <h3><small id="start"><span class="badge badge-warning"><c:out value="${requestScope.product.start_price}"></c:out></span> Euros</small></h3>   
                        </div>
                        <div class="span2" style="float:left">
                            <h4>Provider</h4>
                            <h3><small><c:out value="${requestScope.product.vendor}"></c:out></small></h3>   
                        </div>
                        <div class="span2" style="float:left">
                            <h4>Auction stops on </h4>
                            <h3><small><c:out value="${requestScope.product.stop}"></c:out></small>
                            <small>at <c:out value="${requestScope.product.hour}"></c:out></small></h3>
                        </div>
                        <div class="span2" style="float:left">
                            <h4>Bet Increment</h4>
                            <h3><small id="increase"><span class="badge badge-success"><c:out value="${requestScope.product.increase}"></c:out></span></small>
                        </div>
                        <div class="span2" style="float:left">
                            <h4>Minimum Price</h4>
                            <h3><small id="minimum"><span class="badge badge-important"><c:out value="${requestScope.product.min_price}"></c:out></span></small>
                        </div>
                    </div>
                    <center><a href=".modal" role="button" class="btn btn-large btn-primary" data-toggle="modal">
                            <i class="icon-plus icon-white"></i>
                            <b> Place your offer</b>
                        </a></center>


                    <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                            <h3 id="myModalLabel">Your offer for <c:out value="${requestScope.product.name}"></c:out></h3>
                        </div>
                        <div class="modal-body">
                            <p>Type in the maximum amount you want to spend</p>
                        <c:set var="productid" value="${requestScope.product.id}" scope="session"></c:set>
                            <center><form class="navbar-form pull-left" action="ProductServ" method="POST">
                                <c:if test="${requestScope.product.isopen != 0}">
                                    <div class="alert alert-error" style ="width : 80%"><strong>Your offer will not be considered</strong></div> 
                                </c:if>    
                            <input id="cash" type="text" name="amount" class="span2"/></center>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">
                            <i class="icon-remove"></i> Close
                        </button>
                        <button id="bet" type="submit" class="btn btn-primary">
                            <i class="icon-ok icon-white"></i> Place Offer
                        </button>
                    </div>
                    </form>
                </div>



            </div>

        </div>
    </body>
    <%@ page errorPage="InternalError.jsp" %>
</html>
