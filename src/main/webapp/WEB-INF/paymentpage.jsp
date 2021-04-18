<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Payment Page
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <div>
            <h2>Here you can pay</h2>
        </div>

        <div class="row">
            <div class="col-sm-4">
                <h2>Total price for the order: ${sessionScope.shoppingcart.totalPrice}</h2>
            </div>
        <div class="col-sm-4">
            <h2>Your credits: ${sessionScope.user.balance}</h2>
        </div>
        <div class="col-sm-4">
            <a class="btn btn-success" href="${pageContext.request.contextPath}/fc/gotopayment" role="button">Pay now</a>
        </div>
        </div>


    </jsp:body>
</t:genericpage>