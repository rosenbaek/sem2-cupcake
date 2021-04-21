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
            <form class="mt-5" action="${pageContext.request.contextPath}/fc/addtoshoppingcart" method="post">
                <div class="form-check form-check-inline">
                    <label for="toppingid">Topping</label>
                    <select class="form-select" name="toppingid" id="toppingid">
                        <c:forEach var="topping" items="${applicationScope.toppingMap}">
                            <option value="${topping.key    }">${topping.value.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-check form-check-inline">
                    <label for="bottomid">Bottoms</label>
                    <select class="form-select" name="bottomid" id="bottomid">
                        <c:forEach var="bottom" items="${applicationScope.bottomMap}">
                            <option value="${bottom.key}">${bottom.value.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-check form-check-inline">
                    <label for="quantity">Quantity</label>
                    <input class="form-control" type="number" id="quantity" name="quantity" value="1" min="1" max="50">
                </div>
                <div class="form-check form-check-inline">
                    <input class="btn btn-primary" type="submit" value="Submit">
                </div>
            </form>
        </div>
    </jsp:body>
</t:genericpage>