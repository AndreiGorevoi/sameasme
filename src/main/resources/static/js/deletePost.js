$('#delete-post-button').click(function () {
    var idForDelete = $('#id-for-delete-input').val();
$.ajax({
    type:"DELETE",
    url:"http://localhost:8080/admin/deletePost",
    data: {'id': idForDelete},
    success:function (data){
        console.log(data);
    },
    error:function (e) {
        alert("something wrong")
    }
})
})



