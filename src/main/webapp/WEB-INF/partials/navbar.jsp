<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default bg-info font-monospace"style="font-style: italic; color: chocolate">
    <div class="container-fluid">

        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <div class="navbar-header d-flex align-items-center justify-content-between">
                    <a class="text-light display-5 ff text-decoration-none" href="/ads"><strong>Doglister</strong></a>
                    <img class="bg-img img-fluid" src='${pageContext.request.contextPath}/data/images/Dog Walk Cycle.gif' style="max-width: 10%; display: block;">
                    <ul class="nav d-flex align-items-center justify-content-between gap-3" style="font-style: italic; color: chocolate">
                        <li><a href="/ads" class="text-decoration-none text-light">Dogs</a></li>
                        <li><a href="/profile" class="text-decoration-none text-light">Profile</a></li>
                        <li><a href="/logout" class="text-decoration-none text-light">Logout</a></li>
                    </ul>
                </div>
            </c:when>
            <c:otherwise>
                <div class="navbar-header d-flex align-items-center justify-content-between">
                    <a class="text-light display-5 ff text-decoration-none" href="/ads"style="font-weight: bolder"><strong>Doglister</strong></a>
                    <img class="bg-img img-fluid" src='${pageContext.request.contextPath}/data/images/Dog Walk Cycle.gif' style="max-width: 10%;">
                    <ul class="nav d-flex align-items-center justify-content-between gap-3" style="font-style: italic; color: chocolate">
                        <li><a href="/ads" class="text-decoration-none text-light" style="font-weight: bold">Dogs</a></li>
                        <li><a href="/register" class="text-decoration-none text-light" style="font-weight: bold">Register</a></li>
                        <li><a href="/login" class="text-decoration-none text-light" style="font-weight: bold">Login</a></li>
                    </ul>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</nav>
