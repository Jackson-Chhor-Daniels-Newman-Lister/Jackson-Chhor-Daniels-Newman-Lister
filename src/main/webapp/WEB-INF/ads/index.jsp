<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
</head>
<body class="bg-info-subtle">
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <section class="container">
        <div class="row d-flex justify-content-start">
            <h1 class="mt-5 mb-3 col-12" style="color:darkgoldenrod; font-style: italic;">Here Are All The Ads!</h1>
            <button class="btn btn-info my-3 text-light col-auto" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasWithBothOptions">
                <span class="glyphicon glyphicon-search"></span>Search
            </button>
        </div>

        <div class="row row-cols-1 row-cols-md-3 justify-content-center mb-5">
            <c:forEach var="ad" items="${ads}">
                <div class="col mb-4">
                    <article class="card h-100" style="border-color: darkgoldenrod; border-width: 4px">
                        <div class="card-header p-0 d-flex justify-content-center">
                             <img src="${pageContext.request.contextPath}/data/images/${ad.image}" alt="alt" class=" img-fluid rounded-top">
                        </div>
                        <div class="card-body">
                            <p class="card-title">${ad.title}</p>
                            <p>${ad.shortDescription}</p>
                        </div>
                        <div class="card-footer d-flex justify-content-between align-items-center">
                            <span>$${ad.price}</span>
                            <a href="more-info?adId=${ad.dogId}" class="btn btn-info btn-sm text-light">More info</a>
                        </div>
                    </article>
                </div>
            </c:forEach>
        </div>

        <div class="offcanvas offcanvas-start bg-info" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions" aria-labelledby="offcanvasWithBothOptionsLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasWithBothOptionsLabel"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <h2 class="text-light">Find Your Pup!</h2>
                <div class="d-flex flex-column justify-content-start align-items-start row-gap-3 p-3">

                    <form action="/ads?" method="post">
                        <div class="form-floating form-group d-flex flex-column justify-content-start align-items-start">
                            <input id="search-input" class="form-control" name="search-input" placeholder="search">
                            <label for="search-input">Search</label>
                        </div>

                        <p class="mt-5">Breeds</p>
                        <hr>
                        <div class="form-group d-flex flex-column justify-content-start align-items-start mt-3">
                            <label for="select-breed" class="text-light"></label>
                            <select id="select-breed" class="form-select" name="select-breed">
                                <option selected value="0">SELECT BREED</option>
                                <c:forEach var="breed" items="${breeds}">
                                    <option>${breed.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <p class="mt-5">Traits</p>
                        <hr>
                        <div class=" d-flex flex-column justify-content-start align-items-start">
                            <c:forEach var="trait" items="${traits}">
                                <div class="form-group">
                                    <input type="checkbox" id="${trait.name}" value="${trait.name}" name="traits">
                                    <label for="${trait.name}" class="text-light">${trait.name}</label>
                                </div>
                            </c:forEach>
                        </div>
                        <hr>
                        <button class="btn btn-light mt-5 p-3">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>