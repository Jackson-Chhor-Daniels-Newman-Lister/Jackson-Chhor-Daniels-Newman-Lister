<%--
  Created by IntelliJ IDEA.
  User: anthonychhor
  Date: 5/12/23
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Ad Information</title>
</head>
<body>
<h1>Edit Ad Information</h1>
<form action="edit-info" method="post">
    <c:set var="ad" scope="session" value="${ad}"/>
    <input type="hidden" name="adId" value="${ad.id}"/>
    <div>
        <%--@declare id="title"--%><label for="title">Title:</label>
        <input type="text" name="title" value="${ad.title}"/>
    </div>
    <div>
        <%--@declare id="description"--%><label for="description">Description:</label>
        <textarea name="description">${ad.description}</textarea>
    </div>
    <div>
        <%--@declare id="price"--%><label for="price">Price:</label>
        <input type="number" name="price" value="${ad.price}"/>
    </div>
    <div>
        <%--@declare id="breeds"--%><label for="breeds">Breed(s):</label>
        <input type="text" name="breeds" value="${ad.breeds}"/>
    </div>
    <div>
        <%--@declare id="traits"--%><label for="traits">Trait(s):</label>
        <input type="text" name="traits" value="${ad.traits}"/>
    </div>
    <div>
        <%--@declare id="name"--%><label for="name">Dog Name:</label>
        <input type="text" name="name" value="${ad.name}"/>
    </div>
    <div>
        <%--@declare id="age"--%><label for="age">Dog Age:</label>
        <input type="number" name="age" value="${ad.age}"/>
    </div>
    <div>
        <%--@declare id="playfulness"--%><label for="playfulness">Playfulness:</label>
        <input type="number" name="playfulness" value="${ad.playfulness}"/>
    </div>
    <div>
        <%--@declare id="socialization"--%><label for="socialization">Socialization:</label>
        <input type="number" name="socialization" value="${ad.socialization}"/>
    </div>
    <div>
        <%--@declare id="affection"--%><label for="affection">Affection:</label>
        <input type="number" name="affection" value="${ad.affection}"/>
    </div>
    <div>
        <%--@declare id="training"--%><label for="training">Training:</label>
        <input type="number" name="training" value="${ad.training}"/>
    </div>

    <button type="submit">Save Changes</button>
</form>

<p>Ad short description: ${ad.short_description}</p>
<p>Ad description: ${ad.description}</p>
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
