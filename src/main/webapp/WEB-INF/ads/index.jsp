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

<section class="container text-center bg-info-subtle font-monospace">
    <h1>Here Are all the ads!</h1>
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
        <div class="col-3 bg-primary pt-5">
            <h2 class="text-light">Find Your Pup!</h2>
            <div class="d-flex flex-column justify-content-start align-items-start row-gap-3 p-3">
                <div class="form-floating form-group">
                    <input id="search-input" class="form-control" placeholder="search">
                    <label for="search-input">Search</label>
                    <button class="btn btn-danger mt-2">Submit</button>
                </div>

                <div class="form-group">
                    <label for="select-breed" class="text-light">Breed</label>
                    <select id="select-breed" class="form-select">
                        <option selected value="0">SELECT BREED</option>
                        <c:forEach var="breed" items="${breeds}">
                            <option>${breed.name}</option>
                        </c:forEach>
                    </select>
                </div>

                    <c:forEach var="trait" items="${traits}">
                    <div class="form-group">
                        <label for="${trait}" class="text-light">${trait}</label>
                        <input type="checkbox" id="${trait}" value="${trait}" name="${trait}">
                    </div>
                    </c:forEach>

                <div class="form-group">
                    <label class="text-light">Playfulness</label>
                    <div class="d-flex justify-content-between">
                        <label for="check-playfulness-1" class="text-light">1</label>
                        <input type="radio" id="check-playfulness-1" value="playfulness-1" name="playfulness" class="form-check">
                        <label for="check-playfulness-2" class="text-light">2</label>
                        <input type="radio" id="check-playfulness-2" value="playfulness-2" name="playfulness" class="form-check">
                        <label for="check-playfulness-3" class="text-light">3</label>
                        <input type="radio" id="check-playfulness-3" value="playfulness-3" name="playfulness" class="form-check">
                        <label for="check-playfulness-4" class="text-light">4</label>
                        <input type="radio" id="check-playfulness-4" value="playfulness-4" name="playfulness" class="form-check">
                        <label for="check-playfulness-5" class="text-light">5</label>
                        <input type="radio" id="check-playfulness-5" value="playfulness-5" name="playfulness" class="form-check">
                    </div>
                </div>

                <div class="form-group">
                    <label class="text-light">Socialization</label>
                    <div class="d-flex justify-content-between">
                        <label for="check-socialization-1" class="text-light">1</label>
                        <input type="radio" id="check-socialization-1" value="socialization-1" name="socialization" class="form-check">
                        <label for="check-socialization-2" class="text-light">2</label>
                        <input type="radio" id="check-socialization-2" value="socialization-2" name="socialization" class="form-check">
                        <label for="check-socialization-3" class="text-light">3</label>
                        <input type="radio" id="check-socialization-3" value="socialization-3" name="socialization" class="form-check">
                        <label for="check-socialization-4" class="text-light">4</label>
                        <input type="radio" id="check-socialization-4" value="socialization-4" name="socialization" class="form-check">
                        <label for="check-socialization-5" class="text-light">5</label>
                        <input type="radio" id="check-socialization-5" value="socialization-5" name="socialization" class="form-check">
                    </div>
                </div>

                <div class="form-group">
                    <label class="text-light">Affection</label>
                    <div class="d-flex justify-content-between">
                        <label for="check-affection-1" class="text-light">1</label>
                        <input type="radio" id="check-affection-1" value="affection-1" name="affection" class="form-check">
                        <label for="check-affection-2" class="text-light">2</label>
                        <input type="radio" id="check-affection-2" value="affection-2" name="affection" class="form-check">
                        <label for="check-affection-3" class="text-light">3</label>
                        <input type="radio" id="check-affection-3" value="affection-3" name="affection" class="form-check">
                        <label for="check-affection-4" class="text-light">4</label>
                        <input type="radio" id="check-affection-4" value="affection-4" name="affection" class="form-check">
                        <label for="check-affection-5" class="text-light">5</label>
                        <input type="radio" id="check-affection-5" value="affection-5" name="affection" class="form-check">
                    </div>
                </div>

                <div class="form-group">
                    <label class="text-light">Training</label>
                    <div class="d-flex justify-content-between">
                        <label for="check-training-1" class="text-light">1</label>
                        <input type="radio" id="check-training-1" value="training-1" name="training" class="form-check">
                        <label for="check-training-2" class="text-light">2</label>
                        <input type="radio" id="check-training-2" value="training-2" name="training" class="form-check">
                        <label for="check-training-3" class="text-light">3</label>
                        <input type="radio" id="check-training-3" value="training-3" name="training" class="form-check">
                        <label for="check-training-4" class="text-light">4</label>
                        <input type="radio" id="check-training-4" value="training-4" name="training" class="form-check">
                        <label for="check-training-5" class="text-light">5</label>
                        <input type="radio" id="check-training-5" value="training-5" name="training" class="form-check">
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>
</body>
</html>

<%--http://localhost:8080/Jackson-Chhor-Daniels-Newman-Lister/src/main/resources/images/frozen_fortress.png--%>
