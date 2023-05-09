<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<section class="container">
    <h1>Here Are all the ads!</h1>
    <div class="row gap-3 d-flex justify-content-between">
        <c:forEach var="ad" items="${ads}">
            <article class="card col-5 border border-1 p-0">
                <div class="card-header">
                    <img src="../../../resources/images/image_missing.webp">
                </div>
                <div class="card-body">
                    <p class="card-title">${ad.title}</p>
                    <p>${ad.description}</p>
                </div>
                <div class="card-footer">
                    <p>${ad.price}</p>
                </div>
            </article>
        </c:forEach>
    </div>
</section>
</body>
</html>
