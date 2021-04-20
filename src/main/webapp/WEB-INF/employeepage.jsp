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
            <div class="col-lg-6">
                <form name="addcredittouser" action="${pageContext.request.contextPath}/fc/addcredittouser"
                      method="POST">
                    <h2>Insert credit to user</h2>
                    <div class="row mb-3">
                        <label class="col-sm-1 col-form-label" for="userid">User ID</label>
                        <div class="col-sm-4">
                            <input id="userid" class="form-control" type="text" name="userid"
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
            <div class="col-lg-6">
                <form>
<%--                    Order ID kan ikke bruges til form name!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--%>
                    <div class="form-group">
                        <label for="frank">frank</label>
                        <input type="number" class="form-control" id="frank" placeholder="frank" name="frank">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
