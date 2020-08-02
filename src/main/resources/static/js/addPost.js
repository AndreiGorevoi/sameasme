$('#add-post-button').click(function () {
    if($('#id-title').val()==="" && $('#id-tag').val()==="Choose a tag" && $('#id-tel').val()===""){
        $('#id-title')[0].setAttribute("style","background-color: orangered")
        $('#incorrect-valid-title')[0].removeAttribute("hidden");
        $('#id-tag')[0].setAttribute("style","background-color: orangered")
        $('#incorrect-valid-title2')[0].removeAttribute("hidden");
        $('#id-tel')[0].setAttribute("style","background-color: orangered")
        $('#incorrect-valid-title3')[0].removeAttribute("hidden");
    }else if($('#id-tag').val()==="Choose a tag" && $('#id-tel').val()==="") {
        $('#id-tag')[0].setAttribute("style","background-color: orangered")
        $('#incorrect-valid-title2')[0].removeAttribute("hidden");
        $('#id-tel')[0].setAttribute("style","background-color: orangered")
        $('#incorrect-valid-title3')[0].removeAttribute("hidden");
    }else if($('#id-title').val()==="" && $('#id-tag').val()==="Choose a tag"){
        $('#id-title')[0].setAttribute("style","background-color: orangered")
        $('#incorrect-valid-title')[0].removeAttribute("hidden");
        $('#id-tag')[0].setAttribute("style","background-color: orangered")
        $('#incorrect-valid-title2')[0].removeAttribute("hidden");
    } else if($('#id-title').val()==="" && $('#id-tel').val()===""){
        $('#id-title')[0].setAttribute("style","background-color: orangered")
        $('#incorrect-valid-title')[0].removeAttribute("hidden");
        $('#id-tel')[0].setAttribute("style","background-color: orangered")
        $('#incorrect-valid-title3')[0].removeAttribute("hidden");
    } else if($('#id-tel').val()===""){
        $('#id-tel')[0].setAttribute("style","background-color: orangered")
        $('#incorrect-valid-title3')[0].removeAttribute("hidden");
    }else if($('#id-title').val()===""){
        $('#id-title')[0].setAttribute("style","background-color: orangered")
        $('#incorrect-valid-title')[0].removeAttribute("hidden");
    }else if($('#id-tag').val()==="Choose a tag"){
        $('#id-tag')[0].setAttribute("style","background-color: orangered")
        $('#incorrect-valid-title2')[0].removeAttribute("hidden");
    }
    else {
        var matchDate = new Date($('#id-match-date').val());
        var createDate = new Date();

        var newPost={
            "title" : $('#id-title').val(),
            "description" : $('#id-description').val(),
            "img" : $('#id-img').val(),
            "contactNumber" : $('#id-tel').val(),
            "createDate" : createDate,
            "matchDate" : matchDate,
            "location" :"г."+ $('#id-location1').val()+" ул."+$('#id-location2').val()+" д."+$('#id-location3').val(),
            "amountOfPeople" : $('#id-amount-of-people').val(),
            "price" : $('#id-price').val(),
            "showerPresent" : $('#id-shower-present').prop("checked"),
            "tag" : $('#id-tag').val(),
        }
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/post/",
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
    }


})



