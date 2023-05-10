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
    <title>More Info</title>
</head>
<body>
<h1>More Info</h1>
<c:forEach var="ad" items="${adId}">
    <p>${ad.shortDescription}</p>

</c:forEach>

</body>
</html>
