<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="updatePopupDiv">
    <input id="todoName" value="${todo.name}">
    <input id="todoDesc" value="${todo.description}">

    <input id="todoTagUpdate" type="text">
    <div id="newUpdateTag"></div>
    <input id="addTagUpdate" type="button" value="+ TAG" onclick="addTagUpdate()">

    <c:forEach var="tag" items="${todo.tags}">
        <a href="/" class="deleteTag tags">${tag.tagName}</a>
    </c:forEach>

    <input type="button" id="buttonToDoUpdate" value="update" onclick="updateData(${todo.id})">
</div>