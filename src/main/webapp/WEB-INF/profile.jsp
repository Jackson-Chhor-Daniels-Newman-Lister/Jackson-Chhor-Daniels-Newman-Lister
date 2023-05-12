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

<div class="container my-5 bg-info-subtle">
    <h1 class="text-center" style="font-weight: bold; color: darkgoldenrod">Welcome, ${sessionScope.user.username}!</h1>
</div>

<div class="container bg-info-subtle">
    <div class="row row-cols-1 row-cols-md-3 g-4 justify-content-center">
        <c:forEach var="userAd" items="${userAd}">
            <div class="col">
                <div class="card h-100">
                    <img src="${pageContext.request.contextPath}/data/images/${userAd.image}" class="card-img-top" alt="ad image">
                    <div class="card-body">
                        <h5 class="card-title">${userAd.title}</h5>
                        <p class="card-text">${userAd.shortDescription}</p>
                    </div>
                    <div class="card-footer">
                        <div class="d-flex justify-content-between">
                            <p class="card-text">$${userAd.price}</p>
                            <div>
                                <a href="edit-info?adId=${ad.id}" class="btn btn-sm btn-primary text-light me-2">Edit</a>
                                <a href="more-info?adId=${userAd.id}" class="btn btn-sm btn-primary">More Info</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>

</html>
