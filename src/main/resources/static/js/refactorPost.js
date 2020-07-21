$('#delete-post-button').click(function () {
    var idForDelete = $('#id-for-delete-input').val();
$.ajax({
    type:"DELETE",
    url:"http://localhost:8080/admin/post",
    data: {'id': idForDelete},
    success:function (data){
        console.log(data);
        if(data==true){
            alert("Post has been deleted")
        }
    },
    error:function (e) {
        alert("something wrong")
    }
})
})

$('#button-get-all-posts').click(function () {
    destroyChildren(document.getElementById('div-get-all-posts'))
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/post/all",
        success:function (data) {
            printAllPost(data);
        }
    })
})

function destroyChildren(node)
{
    while (node.firstChild){
        node.removeChild(node.firstChild);
    }
}

function printAllPost(data) {
    destroyChildren(document.getElementById('div-get-all-posts'))
    var divElement = $('#div-get-all-posts');
    var table = document.createElement("TABLE");
    table.setAttribute("class","table table-dark");
    var hr = table.insertRow(-1);
    hr.insertCell(-1).innerHTML="<strong>Id</strong>"
    hr.insertCell(-1).innerHTML="<strong>Title</strong>"
    hr.insertCell(-1).innerHTML="<strong>Owner</strong>"
    for(var i=0;i<data.length;i++){
        var row = table.insertRow(-1);
        row.setAttribute("id","row-id"+i);
        let cell1 = row.insertCell(-1);
        let cell2 = row.insertCell(-1);
        let cell3 = row.insertCell(-1);
        cell1.setAttribute("id","cell-with-id-"+i);
        cell1.innerText=data[i].id;
        cell2.setAttribute("id","cell-with-title-"+i);
        cell2.innerText=data[i].title;
        cell3.setAttribute("id","cell-with-owner-"+i);
        cell3.innerText=data[i].user.login;

    }
    divElement.append(table);
    for (var i=0;i<data.length;i++){
        let buttonElementEdit = document.createElement("button");
        buttonElementEdit.setAttribute("id","button-edit"+i);
        buttonElementEdit.setAttribute("onclick","editFun("+i+")");
        buttonElementEdit.setAttribute("class","btn btn-warning");
        buttonElementEdit.innerText="Edit";
        $('#row-id'+i).append(buttonElementEdit);
    }

}

function editFun(i) {
    var postId= $('#cell-with-id-'+i)[0].textContent;
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/post/post",
        data:{'postId': postId },
        success:function (data) {
            destroyChildren($('#div-get-all-posts')[0]);
            var mainDiv = document.getElementById('div-get-all-posts');
            var divForPostEdit = document.createElement("div");
            divForPostEdit.setAttribute("class","alert alert-info");
            var inputTitle = document.createElement("input");
            inputTitle.setAttribute("value",data.title);
            inputTitle.setAttribute("id","input-for-title-"+i);
            inputTitle.setAttribute("class","form-control");
            divForPostEdit.append(inputTitle);
            mainDiv.append(divForPostEdit);
        }
    })

}



