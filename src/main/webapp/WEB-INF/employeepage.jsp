<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Admin
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1 class="text-center">Hello ${sessionScope.email} </h1>
        <p class="text-center">You are now logged in as a EMPLOYEE of our wonderful site.</p>
        <div class="row mt-5">
            <div class="col-lg-6">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <form name="addcredittouser" action="${pageContext.request.contextPath}/fc/addcredittouser"
                              method="POST">
                            <h2>Insert credit to user</h2>
                            <div class="form-group mb-2">
                                <label for="userid">User ID</label>
                                <input id="userid" class="form-control" type="number" name="userid"
                                       placeholder="Enter User ID">
                            </div>
                            <div class="form-group mb-3">
                                <label for="amount">Amount</label>
                                <input id="amount" class="form-control" type="number" name="amount"
                                       placeholder="Enter Amount">
                            </div>
                            <c:if test="${requestScope.error != null }">
                                <p style="color:red">
                                        ${requestScope.error}
                                </p>
                            </c:if>
                            <input class="btn btn-primary" type="submit" value="Submit">
                        </form>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <h2>Change order status</h2>
                        <c:if test="${requestScope.status != null }">
                            <h3 class="text-center text-success mb-4">${requestScope.status}</h3>
                        </c:if>
                        <c:if test="${requestScope.changestatuserror != null }">
                            <h3 class="text-center text-danger mb-4">${requestScope.changestatuserror}</h3>
                        </c:if>
                        <form action="${pageContext.request.contextPath}/fc/changeorderstatus" method="post">
                            <div class="form-group mb-2">
                                <label for="idorder">Order ID</label>
                                <input id="idorder" class="form-control" type="number" name="idorder"
                                       placeholder="Enter Order ID">
                            </div>
                            <div class="form-group mb-3">
                                <label for="statusorder">Status</label>
                                <select class="form-select" name="statusorder" id="statusorder">
                                    <c:forEach var="status" items="${applicationScope.statuslist}">
                                        <option value="${status}">${status}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group mb-3">
                                <input class="btn btn-primary" type="submit" value="Submit">
                            </div>
                        </form>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
        </div>
        <div class="accordion mt-5" id="accordionExample">
            <c:forEach var="user" items="${requestScope.users}">
                <div class="accordion-item align-items-center">
                    <div class="align-items-center">
                        <h2 class="row accordion-button collapsed accordionhover"
                            style="margin-left: 0; margin-bottom: 0;" data-bs-toggle="collapse"
                            data-bs-target="#collapse${user.key}" aria-expanded="false"
                            aria-controls="collapse${user.key}" id="heading${user.key}">
                            <div class="col-lg-10 collapsed align-items-center text-decoration-none">
                                <div class="row align-items-center">
                                    <div class="col-sm-3">User ID: ${user.value.id}</div>
                                    <div class="col-sm-5">Email: ${user.value.email}</div>
                                    <div class="col-sm-3">Balance: ${user.value.balance}</div>
                                </div>
                            </div>
                        </h2>

                    </div>
                    <div id="collapse${user.key}" class="accordion-collapse collapse"
                         aria-labelledby="heading${user.key}" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <div class="list align-items-center">
                                <div class="row align-items-center"
                                     style="height: 50px; margin-top: 2px; border-radius: 7px; background-color: #F4F3EE;">
                                    <div class="col-sm-2">Order ID: </div>
                                    <div class="col-sm-5">Timestamp:</div>
                                    <div class="col-sm-3">Status:</div>
                                    <div class="col-sm-2">Total Price:</div>
                                </div>
                                <c:forEach var="order" items="${user.value.orders}">
                                    <div class="row align-items-center"
                                         style="height: 50px; margin-top: 2px; border-radius: 7px; background-color: #F4F3EE;">
                                        <div class="col-sm-2">${order.value.id}</div>
                                        <div class="col-sm-5">${order.value.timestamp}</div>
                                        <div class="col-sm-3">${order.value.status}</div>
                                        <div class="col-sm-2">${order.value.totalPrice}</div>
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
