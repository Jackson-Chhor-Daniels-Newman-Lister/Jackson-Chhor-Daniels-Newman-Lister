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

<section class="container">
    <h1 class="mt-5 mb-3">Welcome, ${sessionScope.user.username}!</h1>

    <div class="row row-cols-2 row-cols-md-3 row-cols-lg-4 justify-content-center">
        <c:set var="dog" scope="session" value="${dog}"/>
        <c:forEach var="userAd" items="${userAd}" varStatus="adStatus">
            <article class="col">
                <div class="card h-100" style="border-color: darkgoldenrod; border-width: 4px">
                    <div class="card-header p-0">
                        <img src="${pageContext.request.contextPath}/data/images/${userAd.image}" class="rounded-top img-fluid" alt="ad image">
                    </div>
                    <div class="card-body">
                        <h5 class="text-center">Your dog, ${dog[adStatus.index].name}</h5>
                    </div>
                    <div class="card-footer">
                        <div class="d-flex justify-content-between gap-1">
                            <a href="edit-info?adId=${userAd.dogId}" class="btn btn-sm btn-info text-light">Edit</a>
                            <a href="more-info?adId=${userAd.dogId}" class="btn btn-sm btn-info text-light">View</a>
                            <a href="delete-ad?adId=${userAd.dogId}" class="btn btn-sm btn-danger text-light">Delete</a>
                        </div>
                    </div>
                </div>
            </article>
        </c:forEach>
    </div>
</section>
</body>

</html>
