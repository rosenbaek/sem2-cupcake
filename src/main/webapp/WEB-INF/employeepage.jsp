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
        <h1>Hello ${sessionScope.email} </h1>
        You are now logged in as a EMPLOYEE of our wonderful site.
        <div class="row">
            <div class="col-lg-8">
                <form name="addcredittouser" action="${pageContext.request.contextPath}/fc/addcredittouser"
                      method="POST">
                    <h2>Insert credit to user</h2>
                    <div class="row mb-3">
                        <label class="col-sm-1 col-form-label" for="userid">User ID</label>
                        <div class="col-sm-4">
                            <input id="userid" class="form-control" type="number" name="userid"
                                   placeholder="Enter User ID">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label class="col-sm-1 col-form-label" for="amount">Amount</label>
                        <div class="col-sm-4">
                            <input id="amount" class="form-control" type="number" name="amount"
                                   placeholder="Enter Amount">
                        </div>
                    </div>
                    <c:if test="${requestScope.error != null }">
                        <p style="color:red">
                                ${requestScope.error}
                        </p>
                    </c:if>
                    <input class="btn btn-primary" type="submit" value="Submit">
                </form>
            </div>
            <div class="col-lg-4">
                <h2>Change order status</h2>
                <c:if test="${requestScope.status != null }">
                    <h3 class="text-center text-success mb-4">${requestScope.status}</h3>
                </c:if>
                <c:if test="${requestScope.changestatuserror != null }">
                    <h3 class="text-center text-danger mb-4">${requestScope.changestatuserror}</h3>
                </c:if>
                <form action="${pageContext.request.contextPath}/fc/changeorderstatus" method="post">
                        <label class="col-sm-1 col-form-label" for="idorder">Order ID</label>
                        <div class="col-sm-4">
                            <input id="idorder" class="form-control" type="number" name="idorder"
                                   placeholder="Enter Order ID">
                        </div>
                    <div class="form-check form-check-inline">
                        <label for="statusorder">Status</label>
                        <select name="statusorder" id="statusorder">
                            <c:forEach var="status" items="${applicationScope.statuslist}">
                                <option value="${status}">${status}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="btn btn-primary" type="submit" value="Submit">
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
