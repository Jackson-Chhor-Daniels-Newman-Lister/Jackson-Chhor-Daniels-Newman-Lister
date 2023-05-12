<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <c:forEach var="userAd" items="${userAd}">
                <article class="card col-3 border border-1 p-0">
                    <div class="card-header p-0 d-flex justify-content-center">
                        <img src="${pageContext.request.contextPath}/data/images/${userAd.image}" alt="alt" class="img-fluid rounded-top">
                            <%--                    <img src="../../../data/images/image_missing.webp" alt="alt">--%>
                    </div>
                    <div class="card-body">
                        <p class="card-title">${userAd.title}</p>
                        <p>${userAd.shortDescription}</p>
                    </div>
                    <div class="card-footer d-flex justify-content-between">
                        <p>${userAd.price}</p>
                        <a href="edit-info?adId=${ad.id}" class="btn btn-info btn-sm text-light">Edit</a>
                        <a href="more-info?adId=${userAd.id}" class="btn btn-primary">More info</a>
                    </div>
                </article>
            </c:forEach>
<%--            <p>The number of ads associated with user ID <%= request.getParameter("userAd") %> is <%= request.getAttribute("userId") %>.</p>--%>

        </div>
    </div>
</div>
<div>

</div>
</body>
</html>
