var tagToAddData = [];

$("#addTodo").click(function () {
    $.post("/add",
        {
            todo: $('#todoToAdd').val(),
            tagName: tagToAddData
        }, function (data, status) {
            if (status == "success") {
                window.location.reload();
            }
        });
});

function showInfo(info) {
    var _this = this;
    $.get("/todo/" + info
        , function (data, status) {
            if (status == "success") {
                $("#updateArea" + info).append(data);
            }
        })
}

function updateData(todoId) {
    $.post("/update",
        {
            todoId: todoId,
            todoName: $('#todoName').val(),
            todoDesc: $('#todoDesc').val(),
        }
        , function (data, status) {
            window.location.reload();
        });
}

function deleteTodo(todo) {
    $.post("/deleteTodo",
        {
            todoId: todo
        }, function (data, status) {

            window.location.reload();

        });
}

$("#addTagToTodo").click(function(){
    var tag = $("#tagToAdd").val();
    tagToAddData.push(tag);
    $("#tags").append(tag);
    $("#tagToAdd").val("");
});