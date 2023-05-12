<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>

<body class="bg-info-subtle">
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<section class="container text-center bg-info-subtle font-monospace">
    <h1 class="my-5" style="color:darkgoldenrod; font-style: italic;">Welcome, ${sessionScope.user.username}!</h1>

    <h2>Your current listings</h2>
    <div class="row gap-3 d-flex justify-content-between">
        <div class="col">
            <div class="row gap-4 d-flex justify-content-start mb-5">
                <c:forEach var="ad" items="${ads}">
                    <article class="card col-3 border-6 p-0" style="border-color: darkgoldenrod; border-width: 4px">
                        <div class="card-header p-0 d-flex justify-content-center">
                            <img src="${pageContext.request.contextPath}/data/images/${ad.image}" alt="alt" class=" rounded-top ratio ratio-1x1">
                                <%--                    <img src="../../../data/images/image_missing.webp" alt="alt">--%>
                        </div>
                        <div class="card-body">
                            <p class="card-title">${ad.title}</p>
                            <p>${ad.shortDescription}</p>
                        </div>
                        <div class="card-footer d-flex justify-content-between align-items-center gap-2">
                            <span>$${ad.price}</span>
                            <a href="edit-info?adId=${ad.id}" class="btn btn-info btn-sm text-light">Edit</a>
                            <a href="more-info?adId=${ad.id}" class="btn btn-info btn-sm text-light">More info</a>
                        </div>
                    </article>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
</body>
</html>
