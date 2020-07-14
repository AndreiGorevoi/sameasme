$('#delete-post-button').click(function () {
    var idForDelete = $('#id-for-delete-input').val();
$.ajax({
    type:"DELETE",
    url:"http://localhost:8080/admin/deletePost",
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
            var divElement = $('#div-get-all-posts');
            var table = document.createElement("TABLE");
            for(var i=0;i<data.length;i++){
                var row = table.insertRow(-1);
                row.insertCell(-1).innerText="id: " + data[i].id;
                row.insertCell(-1).innerText="Title: " + data[i].title;
                row.insertCell(-1).innerText="Owner: " + data[i].user.login;
            }
            divElement.append(table);
        }
    })
})

function destroyChildren(node)
{
    while (node.firstChild)
        node.removeChild(node.firstChild);
}



