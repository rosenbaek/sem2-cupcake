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
            <c:forEach var="item" items="${sessionScope.shoppingcart}">
                <tr>
                    <td>${item.topping.name}</td>
                    <td>${item.bottom.name}</td>
                    <td>${item.quantity}</td>
                    <td>${item.totalCupcakePrice}</td>
                </tr>
            </c:forEach>


        </table>
            <c:if test="${sessionScope.role == 'employee' }">
                <p style="font-size: larger">This is what you can do,
                    since your are logged in as an employee</p>
                 <p><a href="fc/employeepage">Employee Page</a>
             </c:if>

             <c:if test="${sessionScope.role == 'customer' }">
                <p style="font-size: larger">This is what you can do, since your
                    are logged in as a customer</p>
                <p><a href="fc/customerpage">Customer Page</a>
            </c:if>



    </jsp:body>
</t:genericpage>