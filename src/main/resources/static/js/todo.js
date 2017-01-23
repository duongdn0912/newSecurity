var tagToAddData = [];
var tagsUpdate = [];

$("#addTodo").click(function () {
    var todo = $('#todoToAdd').val();
    if(todo == '') {
        alert('input the todo');
        return;
    }
    tagToAddData.length > 0 ? tagToAddData : tags = tagToAddData.push($("#tagToAdd").val());
    $.post('/add',
        {
            todo: todo,
            tagName: tagToAddData
        }
        , function (data, status) {
            if (status == 'success') {
                tagToAddData.pop();
                window.location.reload();
            }
        });
});

function showInfo(info) {
    var _this = this;
    $.get('/todo/' + info
        , function (data, status) {
            if (status == 'success') {
                removeInfoDiv();
                $('#updateArea' + info).append(data);
            }
        })
}

function updateData(todoId) {
    tagsUpdate.length > 0 ? tagsUpdate : tags = tagsUpdate.push($("#todoTagUpdate").val());
    $.post('/update',
        {
            todoId: todoId,
            todoName: $('#todoName').val(),
            todoDesc: $('#todoDesc').val(),
            tagNames: tagsUpdate
        }
        , function (data, status) {
            window.location.reload();
        });
}

function deleteTodo(todo) {
    $.post('/deleteTodo',
        {
            todoId: todo
        }, function (data, status) {

            window.location.reload();

        });
}

$('#addTagToTodo').click(function () {
    var tagInfo = '#tagToAdd';
    var place = '#tags';
    addTag(tagInfo, tagToAddData, place, tagToAddData);
});

function addTagUpdate() {
    var allTagData = [];
    $('.tags').each(function () {
        allTagData.push($( this ).text());
    });
    var tagInfo = '#todoTagUpdate';
    var place = '#newUpdateTag';
    addTag(tagInfo, allTagData, place, tagsUpdate);
}

function addTag(tagInfo, oldTags, place, tagList) {
    var tag = $(tagInfo).val();
    if (jQuery.inArray(tag, oldTags) != -1) {
        alert('duplicate tag is not valid');
        return;
    }
    tagList.push(tag);
    $(place).append('<a href="/" class="deleteTag">' + tag + '</a>').append('  ');
    $(tagInfo).val('');
}

function removeInfoDiv() {
    if($('#updatePopupDiv')){
        this.remove();
    }
}