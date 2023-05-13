<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
</head>

<body class="bg-info-subtle">
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
        <div class="container">
            <h1 class="mt-5 mb-3">Please Log In</h1>
            <form action="/login" method="POST" class="mx-auto" style="max-width: 500px;">

                <div class="form-floating form-group mb-3">
                    <input id="username" name="username" class="form-control" type="text" placeholder="Username">
                    <label for="username">Username</label>
                </div>

                <div class="form-floating form-group mb-3">
                    <input id="password" name="password" class="form-control" type="password" placeholder="Password">
                    <label for="password">Password</label>
                </div>

                <button type="submit" class="btn btn-info btn-block text-light mb-3">Log In</button>
            </form>
            <img class="bg-img img-fluid mt-5" src='${pageContext.request.contextPath}/data/images/background3.jpg' style="max-width: 500px; display: block; margin: 0 auto;">
        </div>
</body>


</html>
