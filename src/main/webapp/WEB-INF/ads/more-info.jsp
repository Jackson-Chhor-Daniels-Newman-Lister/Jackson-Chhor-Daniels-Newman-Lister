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
    <style>
        body {
            font-family: 'Merriweather', serif;
            text-align: center;
            font-style: italic;
            font-weight: bold;
        }
        h1 {
            font-weight: bold;
            color:darkgoldenrod
        }
        h5 {
            font-weight: bold;
            color:darkgoldenrod
        }
        .container {
            display: flex;
            justify-content: center;
            height: 185vh;
        }
        .ad-details {
            margin-top: 30px;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 5px;
            max-width: 500px;
            width: 90%;
            box-shadow: 2px 2px 5px #ccc;
        }
        p {
            font-weight: bold;
            color: darkgoldenrod;
        }
        #column1{
            display: flex;
            justify-content: center;
            margin-bottom: 200px;
            margin-top: 400px;
            max-height: 50%;
        }
        #column3{
            display: flex;
            justify-content: center;
            margin-bottom: 200px;
            margin-top: 500px;
            max-height: 40%;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <div class="col-4" id="column1">
            <img src="${pageContext.request.contextPath}/data/images/paws.png" alt="alt" class="rounded-top ratio ratio-1x1">
        </div>
        <div class="col-4">
            <h1>More Info</h1>
            <div class="ad-details">
                <c:set var="ad" scope="session" value="${ad}"/>
                <img src="${pageContext.request.contextPath}/data/images/${ad.image}" alt="alt" class="rounded-top ratio ratio-1x1">
                <p><h5>Name:</h5> ${ad.name}</p>
                <p><h5>Age:</h5> ${ad.age}</p>
                <p><h5>Breed(s):</h5> ${ad.breeds}</p>
                <p><h5>About ${ad.name}:</h5> ${ad.description}</p>
                <p><h5>Price:</h5> ${ad.price}</p>
                <p><h5>Personality:</h5> ${ad.traits}</p>


                <p><h5>Playfulness:</h5> ${ad.playfulness}</p>
                <p><h5>Socialization:</h5> ${ad.socialization}</p>
                <p><h5>Affection:</h5> ${ad.affection}</p>
                <p><h5>Training:</h5> ${ad.training}</p>
            </div>
        </div>
        <div class="col-4" id="column3">
            <img src="${pageContext.request.contextPath}/data/images/info.gif" alt="alt" class="rounded-top ratio ratio-1x1">
        </div>
    </div>
</body>
</html>


