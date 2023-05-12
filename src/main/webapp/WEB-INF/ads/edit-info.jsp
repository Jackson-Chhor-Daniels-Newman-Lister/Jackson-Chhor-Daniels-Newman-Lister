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

    <button type="submit">Save Changes</button>
</form>

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
