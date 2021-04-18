<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Shoppingcart
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <div>
            <h2>See the items in your shopping cart</h2>
        </div>
        <table class="table">
            <thead><th>Topping</th><th>Bottom</th><th>Quantity</th><th>Cupcake Price</th></thead>
            <c:forEach var="item" items="${sessionScope.shoppingcart.cupcakeList}">
                <tr>
                    <td>${item.topping.name}</td>
                    <td>${item.bottom.name}</td>
                    <td>${item.quantity}</td>
                    <td>${item.totalCupcakePrice}</td>
                </tr>
            </c:forEach>
        </table>
        <div class="row">
            <div class="col-sm-4">
                <h2>${sessionScope.shoppingcart.totalPrice}</h2>
            </div>
        <div class="col-sm-4">

        </div>
            <c:if test="${sessionScope.role == 'customer' }">
                <c:set var = "link" scope = "session" value = "/fc/gotopayment"/>
            </c:if>
            <c:if test="${empty sessionScope.role}">
                <c:set var = "link" scope = "session" value = "/fc/loginpage"/>
            </c:if>
        <div class="col-sm-4">
            <a class="btn btn-success" href="${pageContext.request.contextPath}${link}" role="button">Go to payment</a>
        </div>
        </div>


    </jsp:body>
</t:genericpage>