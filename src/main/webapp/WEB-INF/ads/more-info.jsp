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
<c:set var="dog" scope="session" value="${dog}"/>
<c:set var="breed" scope="session" value="${breed}"/>

    <div class="container">
        <h1 class="mt-5 mb-3">${ad.title}</h1>
        <div class="row">
            <div class="col">
                <img src="${pageContext.request.contextPath}/data/images/${ad.image}" alt="alt" class="rounded-top img-fluid mb-3">
                <h5>Personality:</h5>
                <p>
                    <c:forEach var="traits" items="${traits}">
                    <span class="me-2">${traits.name} </span>
                    </c:forEach>
                </p>

                <p><span>About ${dog.name}:</span> ${ad.description}</p>
            </div>
            <div class="col">
                <p><span>Name:</span> ${dog.name}</p>
                <p><span>Age:</span> ${dog.age}</p>
                <p><span>Breed:</span> ${breed.name}</p>

                <h5>Traits:</h5>
                <p><span>Playfulness:</span> ${dog.playfulness} | <span>Affection:</span> ${dog.affection}</p>
                <p><span>Socialization:</span> ${dog.socialization} | <span>Training:</span> ${dog.training}</p>

                <p><span>Price:</span> ${ad.price}</p>
            </div>
        </div>
    </div>
</body>
</html>


