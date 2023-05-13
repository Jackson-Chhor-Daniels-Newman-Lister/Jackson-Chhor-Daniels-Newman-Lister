<%--
  Created by IntelliJ IDEA.
  User: anthonychhor
  Date: 5/10/23
  Time: 9:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="More Info" />
    </jsp:include>
</head>

<body class="bg-info-subtle">
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<c:set var="ad" scope="session" value="${ad}"/>

    <div class="container">
        <h1 class="mt-5 mb-3">${ad.title}</h1>
        <div class="row">
            <div class="col">
                <img src="${pageContext.request.contextPath}/data/images/${ad.image}" alt="alt" class="rounded-top img-fluid">
                <p><h5>Personality:</h5> ${ad.traits}</p>
                <p><h5>About ${ad.name}:</h5> ${ad.description}</p>
            </div>
            <div class="col">
                <p><h5>Name:</h5> ${ad.name}</p>
                <p><h5>Age:</h5> ${ad.age}</p>
                <p><h5>Breed:</h5> ${ad.breeds}</p>

                <h5>Traits:</h5>
                <p><span>Playfulness:</span> ${ad.playfulness} | <span>Affection:</span> ${ad.affection}</p>
                <p><span>Socialization:</span> ${ad.socialization} | <span>Training:</span> ${ad.training}</p>

                <p><h5>Price:</h5> ${ad.price}</p>
            </div>
        </div>
    </div>
</body>
</html>


