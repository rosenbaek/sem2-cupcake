<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="header"/></title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
    <meta name="theme-color" content="#7952b3">
</head>
<body>
    <!--
        This header is inspired by this bootstrap
        example: https://getbootstrap.com/docs/5.0/examples/pricing/
  -->

<div class="container py-3">
    <div>
        <img class="img-fluid" src="${pageContext.request.contextPath}/images/header.png">
    </div>
    <header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-4 border-bottom shadow-sm" style="background-color: #300d4f; color: white">

        <div class="h5 mb-0 mt-3 me-md-auto fw-normal">
            <p style="font-size: larger;">
                <jsp:invoke fragment="header"/>
            </p>
        </div>
        <nav class="my-2 my-md-0 me-md-2">
            <c:if test="${addHomeLink == null }">
                <a class="p-2 text-white navtext" href="<%=request.getContextPath()%>">Home</a>
            </c:if>
            <a class="p-2 text-white navtext" href="#">Orders</a>
            <a class="p-2 text-white navtext" href="#">Profile</a>
            <a class="p-2 text-white navtext" href="#">About</a>
        </nav>


    <div>

        <c:if test="${sessionScope.user != null }">
            ${sessionScope.user.email}
        </c:if>

        <c:set var="thisPage" value="${pageContext.request.servletPath}"/>
        <c:set var="isNotLoginPage" value="${!fn:endsWith(thisPage,'loginpage.jsp')}"/>
        <c:set var="isNotRegisterPage" value="${!fn:endsWith(thisPage,'registerpage.jsp')}"/>

        <c:if test="${isNotLoginPage && isNotRegisterPage}">
            <c:if test="${sessionScope.user != null }">
                <button type="button" class="btn btn-sm  btn-outline-secondary"
                href="${pageContext.request.contextPath}/fc/logoutcommand">Logout</button>
            </c:if>
            <c:if test="${sessionScope.user == null }">
                <button type="button" class="btn btn-sm  btn-rounded btn-outline-secondary me-1 custom"
                   href="${pageContext.request.contextPath}/fc/loginpage">Login</button>
                <button type="button" class="btn btn-sm btn-rounded btn-outline-secondary custom"
                   href="${pageContext.request.contextPath}/fc/registerpage">Sign up</button>
            </c:if>
    </div>
    </c:if>
</header>

<div id="body" class="container" style="min-height: 20vh;">
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container">
    <br>
    <hr>
    <br>
    <jsp:invoke fragment="footer"/>
</div>
</div>
</body>
</html>