<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Register For Our Site!" />
    </jsp:include>
</head>
<body>
<jsp:include page="partials/navbar.jsp" />
<div class="container mt-5 bg-info-subtle">
    <h1 class="text-center mb-4" style="font-family: 'Open Sans', sans-serif; color:darkgoldenrod;">Please Fill Out Your Information Below</h1>
    <form class="p-4 bg-white rounded" style="max-width: 500px; margin: 0 auto; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);">
        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" name="username" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input id="email" name="email" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input id="password" name="password" class="form-control" type="password">
        </div>
        <div class="form-group">
            <label for="confirm_password">Confirm Password</label>
            <input id="confirm_password" name="confirm_password" class="form-control" type="password">
        </div>
        <input type="submit" class="btn btn-primary btn-block">
    </form>
    <img class="bg-img img-fluid mt-5" src='${pageContext.request.contextPath}/data/images/gif.gif' style="max-width: 100%; display: block; margin: 0 auto;">
</div>
</body>
</html>
