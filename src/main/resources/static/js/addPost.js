$('#add-post-button').click(function () {
    var matchDate = new Date($('#id-match-date').val());
    var createDate = new Date($('#id-create-date').val());
    if($('#id-shower-present').val()=="on"){
        var shower = true;
    }else {
        var shower = false;

    }

var newPost={
    "title" : $('#id-title').val(),
    "description" : $('#id-description').val(),
    "img" : $('#id-img').val(),
    "contactNumber" : $('#id-tel').val(),
    "createDate" : createDate,
    "matchDate" : matchDate,
    "location" : $('#id-location').val(),
    "amountOfPeople" : $('#id-amount-of-people').val(),
    "price" : $('#id-price').val(),
    "showerPresent" : shower,
    "tag" : $('#id-tag').val(),
}
$.ajax({
    type:"POST",
    url:"http://localhost:8080/post/addPost",
    data:JSON.stringify(newPost),
    contentType:"application/json",
    success:function (data){
        alert("Match has been added")
        setTimeout("",5000);
        location.href="http://localhost:8080/wall"
    },
    error:function (e) {
        alert("something wrong")
    }
})
})



