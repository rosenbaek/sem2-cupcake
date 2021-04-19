<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Orders
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <c:forEach var="topping" items="${applicationScope.toppingMap}">
            <div class="row mx-3" >
                <input id="order${topping.key}" type="checkbox">
                <label for="order${topping.key}">
                    <div class="row align-items-center" style="height: 50px; margin-top: 3px; border-radius: 7px; background-color: #716598; color: white">
                        <div class="col-sm-4">${topping.value.name}</div>
                        <div class="col-sm-6">${topping.value.name}</div>
                        <div class="col-sm-2 mr-2">${topping.value.name}</div>
                    </div>
                </label>
                <div class="list align-items-center">
                    <div class="row align-items-center" style="height: 50px; margin-top: 0px; border-radius: 7px; background-color: #F4F3EE;">
                        <div class="col-sm-4">${topping.value.name}</div>
                        <div class="col-sm-6">${topping.value.name}</div>
                        <div class="col-sm-2 mr-2">${topping.value.name}</div>
                    </div>

                </div>
            </div>

        </c:forEach>
    </jsp:body>
</t:genericpage>