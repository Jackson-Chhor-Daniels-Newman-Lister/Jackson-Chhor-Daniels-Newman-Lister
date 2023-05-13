<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
<html lang="en">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</html>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Welcome to my site!" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<section class="bg-info-subtle">
    <div class="container text-center py-5 ">
        <h1 class="display-4 font-weight-bold mb-4" style="font-family: 'Montserrat', sans-serif; font-weight: bolder; font-style: italic; color:darkgoldenrod">Welcome to the Adlister!</h1>
        <img class="img-fluid" style="max-width: 500px;" src="${pageContext.request.contextPath}/data/images/background1.jpg">
    </div>
</section>
</body>
