<%--
  Created by IntelliJ IDEA.
  User: gagejackson
  Date: 5/14/23
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
</head>

<body class="bg-info-subtle">
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<c:set var="ad" scope="session" value="${ad}"/>
<c:set var="dog" scope="session" value="${dog}"/>

<div class="container">
    <h1 class="mt-5 mb-3">Are you sure you want to delete ${dog.name}?</h1>
    <form action="/delete-ad" method="POST" class="mx-auto" style="max-width: 500px;">
        <input type="hidden" name="ad_id" value="${ad.dogId}"/>
        <div class=" d-flex justify-content-center">
            <button type="submit" class="btn btn-info btn-block text-light mb-3">DELETE ${dog.name}?</button>
        </div>
    </form>
    <img class="bg-img img-fluid mt-5" src='${pageContext.request.contextPath}/data/images/${ad.image}' style="max-width: 500px; display: block; margin: 0 auto;">
</div>
</body>
</html>
