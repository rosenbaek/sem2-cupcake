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
        <div>
            <c:if test="${requestScope.status != null }">
                <h3 class="text-center text-success mb-4">${requestScope.status}</h3>
            </c:if>
        </div>
        <div class="accordion" id="accordionExample">
            <c:forEach var="orders" items="${requestScope.orders}">
                <div class="accordion-item">

                    <div class="accordion-header" id="heading${orders.key}">
                        <a class="row accordion-button collapsed align-items-center text-decoration-none" id="test"
                           style="margin-left: 0;" data-bs-toggle="collapse" data-bs-target="#collapse${orders.key}"
                           aria-expanded="false" aria-controls="collapse${orders.key}">
                            <div class="col-sm-2">Order ID: ${orders.value.id}</div>
                            <div class="col-sm-4">Timestamp: ${orders.value.timestamp}</div>
                            <div class="col-sm-3">Status: ${orders.value.status}</div>
                            <div class="col-sm-2">Total Price: ${orders.value.totalPrice}</div>
                        </a>
                    </div>

                    <div id="collapse${orders.key}" class="accordion-collapse collapse"
                         aria-labelledby="heading${orders.key}" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <div class="list align-items-center">
                                <div class="row align-items-center mx-1"
                                     style="height: 50px; margin-top: 2px; border-radius: 7px; background-color: #F4F3EE;">
                                    <div class="col-sm-4"><b>Name</b></div>
                                    <div class="col-sm-6"><b>Quantity</b></div>
                                    <div class="col-sm-2"><b>Total price</b></div>
                                </div>
                                <c:forEach var="products" items="${orders.value.products}">
                                    <div class="row align-items-center mx-1"
                                         style="height: 50px; margin-top: 2px; border-radius: 7px; background-color: #F4F3EE;">
                                        <div class="col-sm-4">${products.name}</div>
                                        <div class="col-sm-6">${products.quantity}</div>
                                        <div class="col-sm-2">${products.totalPrice}</div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </jsp:body>
</t:genericpage>