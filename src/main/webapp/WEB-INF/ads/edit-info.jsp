<%--
  Created by IntelliJ IDEA.
  User: anthonychhor
  Date: 5/12/23
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Editing Ad" />
    </jsp:include>
</head>

<body class="bg-info-subtle">
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<c:set var="ad" scope="session" value="${ad}"/>
<c:set var="dog" scope="session" value="${dog}"/>
<c:set var="breed" scope="session" value="${breed}"/>

    <div class="container">
        <h1 class="mt-5 mb-3">Edit ${dog.name}'s Information</h1>
        <form action="edit-info" method="post">
            <input type="hidden" name="ad_id" value="${ad.dogId}"/>
            <input type="hidden" name="dog_id" value="${ad.dogId}"/>
            <div class="row gap-3">
                <div class="col">

                    <label for="name"></label>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Name</span>
                        <input id="name" type="text" class="form-control font-monospace" name="name" value="${dog.name}" required/>
                    </div>

                    <label for="age"></label>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Age</span>
                        <input id="age" type="number" class="form-control font-monospace" name="age" min="1" max="25" value="${dog.age}" required/>
                    </div>


                    <div class="input-group mb-3">
                        <label for="breeds" class="input-group-text">Breed</label>
                        <select id="breeds" class="form-select" name="breeds"  required>
                            <option value="0">SELECT BREED</option>
                            <c:forEach var="breed" items="${breed}">
                                <option value="${breed.id}">${breed.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class=" d-flex flex-column justify-content-start align-items-start mb-3">
                        <c:forEach var="trait" items="${traits}">
                            <div class="form-group">
                                <input type="checkbox" id="${trait.name}" value="${trait.id}" name="traits">
                                <label for="${trait.name}" style="color: darkgoldenrod;">${trait.name}</label>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="row gap-1">
                        <div class="col-12 col-lg">
                            <label for="playfulness"></label>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Playfulness</span>
                                <input id="playfulness" type="number" class="form-control font-monospace" name="playfulness" min="1" max="5" value="${dog.playfulness}" required/>
                            </div>

                            <label for="socialization"></label>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Socialization</span>
                                <input id="socialization" type="number" class="form-control font-monospace" name="socialization" min="1" max="5" value="${dog.socialization}" required/>
                            </div>
                        </div>

                        <div class="col-12 col-lg">
                            <label for="affection"></label>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Affection</span>
                                <input id="affection" type="number" class="form-control font-monospace" name="affection" min="1" max="5" value="${dog.affection}" required/>
                            </div>

                            <label for="training"></label>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Training</span>
                                <input id="training" type="number" class="form-control font-monospace" name="training" min="1" max="5" value="${dog.training}" required/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">

                    <label for="title"></label>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Title</span>
                        <input  id="title" type="text" class="form-control font-monospace" name="title" value="${ad.title}" required/>
                    </div>

                    <div class="form-floating mb-3">
                        <textarea class="form-control font-monospace" name="short_description" maxlength="225" rows="3" style="height: 100px; resize: none">${ad.shortDescription}</textarea>
                        <%--@declare id="short_description"--%><label for="short_description">Short Description</label>
                    </div>

                    <div class="form-floating mb-3">
                        <textarea class="form-control font-monospace" name="description" rows="6" style="height: 200px; resize: none" required>${ad.description}</textarea>
                        <%--@declare id="description"--%><label for="description">Description</label>
                    </div>

                    <label for="price"></label>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Price</span>
                        <input id="price" type="number" class="form-control font-monospace" name="price" value="${ad.price}" required/>
                    </div>
                </div>
            </div>

            <button type="submit" class="btn btn-info text-light">Save Changes</button>
        </form>
  </div>

</body>

</html>
