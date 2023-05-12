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
<img src="${pageContext.request.contextPath}/data/images/${ad.image}" alt="alt" class=" rounded-top ratio ratio-1x1">
<p>AD id: ${ad.id}</p>
<p>Ad short description: ${ad.short_description}</p>
<p>Ad descrpition: ${ad.description}</p>
<p>Ad Title: ${ad.title}</p>
<p>Dog Price: ${ad.price}</p>
<p>Dog Breed(s): ${ad.breeds}</p>
<p>Dog Trait(s): ${ad.traits}</p>
<p>Dog Name: ${ad.name}</p>
<p>Dog Age: ${ad.age}</p>
<p>Playfulness: ${ad.playfulness}</p>
<p>Socialization: ${ad.socialization}</p>
<p>Affection: ${ad.affection}</p>
<p>Training: ${ad.training}</p>

</body>
</html>
