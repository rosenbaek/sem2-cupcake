<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         About
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${true}" scope="request"/>
    </jsp:attribute>

    <jsp:body>
        <div class="row mt-5">
            <div class="col-lg-6">
                <h2>Contact Info</h2>
                <h3>Email: Cupcakes@Olsker.com</h3>
                <h3>Address: Rønnevej 67, Olsker, 3770 Allinge</h3>
            </div>
            <div class="col-lg-6 text-center">
                <img src="${pageContext.request.contextPath}/images/bornholm_kort.png">
            </div>
        </div>

    </jsp:body>
</t:genericpage>