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
    <link href="https://fonts.googleapis.com/css2?family=Merriweather:wght@300&family=Oswald&display=swap" rel="stylesheet">
</head>
<body>
<h1>More Info</h1>
<c:set var="ad" scope="session" value="${ad}"/>
<p>${ad.id}</p>
<p>${ad.shortDescription}</p>
<p>${ad.description}</p>
<p>${ad.title}</p>
<p>${ad.price}</p>

</body>
</html>
