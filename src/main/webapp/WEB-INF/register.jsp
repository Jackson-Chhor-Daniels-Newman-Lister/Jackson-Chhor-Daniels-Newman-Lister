<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Register For Our Site!" />
    </jsp:include>
</head>
<body class="bg-info-subtle">
<jsp:include page="partials/navbar.jsp" />
<div class="container">
    <h1 class="text-center mt-5 mb-3">Please Fill Out Your Information Below</h1>
    <form action="register" method="post" class="mx-auto" style="max-width: 500px;">
        <div class="form-floating form-group mb-3">
            <input id="username" name="username" class="form-control" type="text" placeholder="Username">
            <label for="username">Username</label>
        </div>

        <div class="form-floating form-group mb-3">
            <input id="email" name="email" class="form-control" type="text" placeholder="Email">
            <label for="email">Email</label>
        </div>

        <div class="form-floating form-group mb-3">
            <input id="password" name="password" class="form-control" type="password" placeholder="Password">
            <label for="password">Password</label>
        </div>

        <div class="form-floating form-group mb-3">
            <input id="confirm_password" name="confirm_password" class="form-control" type="password" placeholder="Confirm Password">
            <label for="confirm_password">Confirm Password</label>
        </div>

        <input type="submit" class="btn btn-info btn-block text-light">
    </form>
    <img class="bg-img img-fluid mt-5" src='${pageContext.request.contextPath}/data/images/gif.gif' style="max-width: 500px; display: block; margin: 0 auto;">
</div>
</body>
</html>
