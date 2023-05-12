<%--
  Created by IntelliJ IDEA.
  User: anthonychhor
  Date: 5/12/23
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Editing Ad" />
    </jsp:include>
</head>

<body class="bg-info-subtle">
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <h1>Edit Ad Information</h1>
        <c:set var="ad" scope="session" value="${ad}"/>
        <form action="edit-info" method="post">
            <input type="hidden" name="ad_id" value="${ad.id}"/>
            <input type="hidden" name="dog_id" value="${ad.dogs_id}"/>
            <div class="mb-3">
                <%--@declare id="title"--%><label for="title" class="form-label font-monospace fw-bold">Title:</label>
                <input type="text" class="form-control font-monospace" name="title" value="${ad.title}"/>
            </div>
            <div class="mb-3">
                <%--@declare id="description"--%><label for="description" class="form-label font-monospace fw-bold">Description:</label>
                <textarea class="form-control font-monospace" name="description">${ad.description}</textarea>
            </div>
            <div class="mb-3">
                <%--@declare id="price"--%><label for="price" class="form-label font-monospace fw-bold">Price:</label>
                <input type="number" class="form-control font-monospace" name="price" value="${ad.price}"/>
            </div>
            <div class="mb-3">
                <%--@declare id="breeds"--%><label for="breeds" class="form-label font-monospace fw-bold">Breed(s):</label>
                <input type="text" class="form-control font-monospace" name="breeds" value="${ad.breeds}"/>
            </div>
            <div class="mb-3">
                <%--@declare id="traits"--%><label for="traits" class="form-label font-monospace fw-bold">Trait(s):</label>
                <input type="text" class="form-control font-monospace" name="traits" value="${ad.traits}"/>
            </div>
            <div class="mb-3">
                <%--@declare id="name"--%><label for="name" class="form-label font-monospace fw-bold">Dog Name:</label>
                <input type="text" class="form-control font-monospace" name="name" value="${ad.name}"/>
            </div>
            <div class="mb-3">
                <%--@declare id="age"--%><label for="age" class="form-label font-monospace fw-bold">Dog Age:</label>
                <input type="number" class="form-control font-monospace" name="age" value="${ad.age}"/>
            </div>
            <div class="mb-3">
                <%--@declare id="playfulness"--%><label for="playfulness" class="form-label font-monospace fw-bold">Playfulness:</label>
                <input type="number" class="form-control font-monospace" name="playfulness" value="${ad.playfulness}"/>
            </div>
            <div class="mb-3">
                <%--@declare id="socialization"--%><label for="socialization" class="form-label font-monospace fw-bold">Socialization:</label>
                <input type="number" class="form-control font-monospace" name="socialization" value="${ad.socialization}"/>
            </div>
            <div class="mb-3">
                <%--@declare id="affection"--%><label for="affection" class="form-label font-monospace fw-bold">Affection:</label>
                <input type="number" class="form-control font-monospace" name="affection"  value="${ad.affection}"/>
            </div>
            <div class="mb-3">
                <%--@declare id="training"--%><label for="training" class="form-label font-monospace">Training:</label>
                <input type="number" class="form-control font-monospace" name="training" value="${ad.training}"/>
            </div>

            <button type="submit" class="btn btn-primary">Save Changes</button>
        </form>
  </div>

</body>

</html>
