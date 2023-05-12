<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
</head>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<section class="container-fluid bg-info-subtle font-monospace text-center py-5">
    <div class="container">
        <h1 class="mb-4" style="font-family:'emoji'; color:darkgoldenrod; font-size: 4rem;">Please Log In</h1>
        <form action="/login" method="POST" class="mx-auto" style="max-width: 500px;">
            <div class="form-group">
                <label for="username" style="color:darkgoldenrod;">Username</label>
                <input id="username" name="username" class="form-control" type="text">
            </div>
            <div class="form-group">
                <label for="password" style="color:darkgoldenrod;">Password</label>
                <input id="password" name="password" class="form-control mb-3" type="password">
            </div>
            <div class="form-group position-relative">
                <img class="bg-img img-fluid" src='${pageContext.request.contextPath}/data/images/background3.jpg'>
            </div>
            <button type="submit" class="btn btn-info btn-block mt-3">Log In</button>
        </form>
    </div>
</section>

</html>
