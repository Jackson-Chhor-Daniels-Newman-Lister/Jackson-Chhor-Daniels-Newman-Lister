<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <h1>Welcome, ${sessionScope.user.username}!</h1>
    </div>

    <div class="row gap-3 d-flex justify-content-between bg-dark-subtle">
        <div class="col">
            <div class="row gap-3 d-flex justify-content-between">
                <c:forEach var="ad" items="${ads}">
                    <article class="card col-3 border border-1 p-0">
                        <div class="card-header p-0 d-flex justify-content-center">
                            <img src="${pageContext.request.contextPath}/data/images/${ad.image}" alt="alt" class="img-fluid rounded-top">
                                <%--                    <img src="../../../data/images/image_missing.webp" alt="alt">--%>
                        </div>
                        <div class="card-body">
                            <p class="card-title">${ad.title}</p>
                            <p>${ad.shortDescription}</p>
                        </div>
                        <div class="card-footer d-flex justify-content-between">
                            <p>${ad.price}</p>
                            <a href="more-info?adId=${ad.id}" class="btn btn-primary">More info</a>
                        </div>
                    </article>
                </c:forEach>
            </div>
        </div>
    </div>
<div>

</div>
</body>
</html>
