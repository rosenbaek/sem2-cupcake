<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Admin Orders page
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <c:forEach var="orders" items="${requestScope.orders}">
            <div class="row mx-3" >
                <input id="order${orders.key}" type="checkbox">
                <label for="order${orders.key}">
                    <form action="${pageContext.request.contextPath}/fc/removefromorders" method="post">
                        <div class="row align-items-center" style="height: 50px; margin-top: 3px; border-radius: 7px; background-color: #716598; color: white">
                            <div class="col-sm-2">Order ID: ${orders.value.id}</div>
                            <div class="col-sm-3">Timestamp: ${orders.value.timestamp}</div>
                            <div class="col-sm-2">Status: ${orders.value.status}</div>
                            <div class="col-sm-2">User Id: ${orders.value.userId}</div>
                            <div class="col-sm-3 mr-2">
                                 <button type="submit" class="btn btn-primary" name="delete" value="${orders.key}">
                                     Remove
                                 </button>
                            </div>
                        </div>
                    </form>
                </label>
                <div class="list align-items-center">

                    <c:forEach var="orders" items="${requestScope.orders}">
                    <div class="row align-items-center" style="height: 50px; margin-top: 0px; border-radius: 7px; background-color: #F4F3EE;">
                        <div class="col-sm-4">${orders.value.id}</div>
                        <div class="col-sm-6">${orders.value.id}</div>
                        <div class="col-sm-2 mr-2">${orders.value.id}</div>
                    </div>
                    </c:forEach>
                </div>
            </div>

        </c:forEach>
    </jsp:body>
</t:genericpage>