<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Home page</title>
    <link rel="stylesheet" href="/css/user.css">
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<table>

    <tr>
        <td>
            <label>Todo Description</label>
            <input type="text" id="todoToAdd">
        </td>
        <td>
            <label>Tag</label>
            <input type="text" id="tagToAdd">
        </td>
        <td><input type="button" id="addTagToTodo" value="+TAG"></td>
        <td id="tags"></td>
        <td><input type="button" id="addTodo" value="ADD"></td>
    </tr>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Description</td>
        <td>Tag</td>
    </tr>

    <c:forEach var="todo" items="${todos}">
        <tr>
            <td><c:out value="${todo.id}"/></td>
            <td><c:out value="${todo.name}"/></td>
            <td><c:out value="${todo.description}"/></td>
            <td>
                <c:forEach var="tag" items="${todo.tags}">
                    <a href="/">${tag.tagName}</a>
                </c:forEach>
            </td>
            <td><input type="button" value="e" onclick="showInfo(${todo.id})"></td>
            <td><input type="button" value="x" onclick="deleteTodo(${todo.id})"></td>
            <td id="updateArea${todo.id}"></td>
        </tr>
    </c:forEach>

</table>

<a href="/logout">logout</a>

<%--<script src="/js/todo.js"></script>--%>
<script>

    var tagToAddData = [];
    var tagsUpdate = [];

    $("#addTodo").click(function () {
        if($('#todoToAdd').val() == "") {
            alert("input the todo");
            return;
        }
        tagToAddData.length > 0 ? tagToAddData : tags = tagToAddData.push($("#tagToAdd").val());
        $.post("/add",
            {
                todo: $('#todoToAdd').val(),
                tagName: tagToAddData
            }
            , function (data, status) {
                if (status == "success") {
                    tagToAddData.pop();
                    window.location.reload();
                }
            });
    });

    function showInfo(info) {
        var _this = this;
        $.get("/todo/" + info
            , function (data, status) {
                if (status == "success") {
                    $("#updatePopupDiv").remove();
                    $("#updateArea" + info).append(data);
                }
            })
    }

    function updateData(todoId) {
        tagsUpdate.length > 0 ? tagsUpdate : tags = tagsUpdate.push($("#todoTagUpdate").val());
        $.post("/update",
            {
                todoId: todoId,
                todoName: $('#todoName').val(),
                todoDesc: $('#todoDesc').val(),
                tagNames: tagsUpdate
            }
            , function (data, status) {
                if (status == "success") {
                    window.location.reload();
                }
            });
    }

    function deleteTodo(todo) {
        $.post("/deleteTodo",
            {
                todoId: todo
            }, function (data, status) {
                if (status == "success") {
                    window.location.reload();
                }
            });
    }

    $("#addTagToTodo").click(function () {
        var tagInfo = "#tagToAdd";
        var place = "#tags";
        addTag(tagInfo, tagToAddData, place, tagToAddData);
    });

    function addTagUpdate() {
        var allTagData = [];
        $(".tags").each(function () {
            allTagData.push($( this ).text());
        });
        var tagInfo = "#todoTagUpdate";
        var place = "#newUpdateTag";
        addTag(tagInfo, allTagData, place, tagsUpdate);
    }

    function addTag(tagInfo, oldTags, place, tagList) {
        var tag = $(tagInfo).val();
        if (jQuery.inArray(tag, oldTags) != -1) {
            alert("duplicate tag is not valid");
            return;
        }
        tagList.push(tag);
        $(place).append('<a href="/" class="deleteTag">' + tag + '</a>').append("  ");
        $(tagInfo).val("");
    }

    function deleteTagOnTodo(todoId, tagId) {
        $.post("/deleteTag",
            {
                todoId: todoId,
                tagId: tagId
            }, function (data, status) {
                if (status == "success") {
                    window.location.reload();
                }
            });
    }

</script>
</body>
</html>