<%-- 
    Document   : Buy
    Created on : Jan 23, 2013, 12:56:21 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>See Auction</title>
        <link rel="stylesheet" type="text/css" href="bootstrap.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-responsive.css">
        <link rel="stylesheet" type="text/css" href="style.css">
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap.js"></script>
        <script type="text/javascript" src="scrollBuy.js"></script>
        <script type="text/javascript" src="myZoomScript.js"></script>
    </head>
    <body>
        <a name="top"></a>
        <div class="wrapper">
            <br><br>
            
            <div class="navbar" style ="width : 700px;top: 0; position: fixed; z-index: 10">
                <div class="navbar-inner">

                    <a class="brand"><i class="icon-user"></i> <c:out value="${sessionScope.user.name}"></c:out></a>
                        <ul class="nav">
                            <li><a href="User.jsp"><i class="icon-home"></i> Home</a></li>
                            <li><a href="Sell.jsp"><i class="icon-share"></i> Sell</a></li>
                            <li class="active"><a href="UserServ"><i class="icon-shopping-cart"></i> Auctions</a></li>
                            <li><a><i class="icon-calendar"></i> <c:out value="${sessionScope.date}"></c:out></a></li>

                        </ul>
                    </div>
                </div>
                <div class="page-header">
                    <h1><small>Section : Open Auctions</small>
                        <div class="btn-group" style="float :right">
                            <button class="btn btn-default">
                                <i class="icon-list"></i>
                                <b> Categories</b>
                            </button>
                            <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">

                            <c:forEach var="item" items="${requestScope.categories}">
                                <li>
                                    <a href="UserServ?category=${item}"><c:out value="${item}"></c:out></a>
                                    </li>
                            </c:forEach>
                            <li class="divider"></li>
                            <li>
                                <a href="UserServ?category=See_All">See All</a>
                            </li>
                        </ul>
                    </div>

                </h1>
            </div>

            <div class="hero-unit" style="padding-top: 10px">
                <h3><small>Search by name or description:</small></h3>
                <form class="navbar-form pull-left" action="UserServ" method="POST">
                    <input type="hidden" name="type" value="src"/>
                    <input  type="text" name="word" placeholder="Search..." class="span2"/>
                    <button type="submit" class="btn btn-primary">
                        <i class="icon-search icon-white"></i>
                        <b> Search</b>
                    </button>
                </form><br><br>
                <table class="table">
                    <c:set var="iter" value="0"></c:set>

                    <c:forEach var="prod" items="${requestScope.products}">
                        <c:if test="${iter%2 == 0}">
                            <c:set var="img" value="${prod.photo}"></c:set>
                                <tr class="info">
                                    <td>
                                        <img class="img-rounded" style="width : 120px; height : 120px" src="http://localhost${img}">
                                </td>
                                <td>
                                    <h2><small><c:out value="${prod.name}"></c:out></small>
                                        <a href="ProductServ?id=${prod.id}" style="float : right">
                                            <button class="btn btn-primary btn-small">
                                                <i class="icon-eye-open icon-white"></i>
                                                <b> See Item</b>
                                            </button>
                                        </a>
                                    </h2>
                                    <h4><small><c:out value="${prod.description}"></c:out></small></h4>
                                    <h4><small>Vendor : <c:out value="${prod.vendor}"></c:out></small></h4>

                                    </td>
                                </tr>

                        </c:if>
                        <c:if test="${iter%2 != 0}">
                            <c:set var="img" value="${prod.photo}"></c:set>
                                <tr class="warning">
                                    <td>
                                        <img class="img-rounded" style="z-index:0;width : 120px; height : 120px;" src="http://localhost${img}">
                                </td>
                                <td>
                                    <h2><small><c:out value="${prod.name}"></c:out></small>
                                        <a href="ProductServ?id=${prod.id}" style="float : right">
                                            <button class="btn btn-primary btn-small">
                                                <i class="icon-eye-open icon-white"></i>
                                                <b> See Item</b>
                                            </button>
                                        </a>
                                    </h2>
                                    <h4><small><c:out value="${prod.description}"></c:out></small></h4>
                                    <h4><small>Vendor : <c:out value="${prod.vendor}"></c:out></small></h4>

                                    </td>
                                </tr>
                        </c:if>
                        <c:set var="iter" value="${iter + 1}"></c:set>
                    </c:forEach>
                </table>
                <a id="top" href="#" style="position : fixed; left: 150px; bottom: 50px; display: none"><button class="btn default btn-info"><i class="icon-arrow-up icon-white"></i> To the top</button></a>
            </div>
        </div>
    </body>
</html>
