<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <div>
            <h2>Order your cupcakes here</h2>
        </div>
        <div>
            <form>
                <div class="form-check form-check-inline">
                    <label for="toppings">Topping</label>
                    <select name="toppings" id="toppings">
                        <c:forEach var="topping" items="${applicationScope.toppingList}">
                            <option value="${topping.id}">${topping.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-check form-check-inline">
                    <label for="bottoms">Bottoms</label>
                    <select name="bottoms" id="bottoms">
                        <c:forEach var="bottom" items="${applicationScope.bottomList}">
                            <option value="${bottom.id}">${bottom.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-check form-check-inline">
                    <label for="quantity">Quantity</label>
                    <input type="number" id="quantity" name="quantity" value="1" min="1" max="50">
                </div>
                <div class="form-check form-check-inline">
                    <input class="btn btn-primary" type="submit" value="Submit">
                </div>
            </form>
        </div>
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