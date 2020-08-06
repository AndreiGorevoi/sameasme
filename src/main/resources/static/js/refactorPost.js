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
    destroyChildren($('#div-get-all-posts')[0]);
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
    destroyChildren($('#div-get-all-posts')[0]);
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
        let buttonElementDelete = document.createElement("button");
        buttonElementDelete.setAttribute("id","button-delete"+i);
        buttonElementDelete.setAttribute("onclick","deleteFun("+i+")");
        buttonElementDelete.setAttribute("class","btn btn-danger");
        buttonElementDelete.innerText="Delete";
        $('#row-id'+i).append(buttonElementEdit,buttonElementDelete);
    }

}
//print post for change
function editFun(i) {
    var postId= $('#cell-with-id-'+i)[0].textContent;
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/post/"+postId,
        success:function (data) {
            //write block with inputs for edit post;
            destroyChildren($('#div-get-all-posts')[0]);
            var mainDiv = document.getElementById('div-get-all-posts');
            var divForPostEdit = document.createElement("div");
            divForPostEdit.setAttribute("class","alert alert-info");
            var inputTitle = document.createElement("input");
            var inputNumOfPlayer = document.createElement("input");
            var selectTag = document.createElement("select");
            var optionTagActually = document.createElement("option");
            var optionTagFootball = document.createElement("option");
            var optionTagBasketball = document.createElement("option");
            var optionTagVolleyball = document.createElement("option");
            var optionTagTennis = document.createElement("option");
            var inputTelOfContact = document.createElement("input");
            var textAreDescription = document.createElement("textarea");
            var inputLinkOfImg = document.createElement("input");
            var inputAddress = document.createElement("input");
            var inputMatchDate = document.createElement("input");
            var inputCost = document.createElement("input");
            var checkBoxShower = document.createElement("input");
            var inputId = document.createElement("input");
            var br = document.createElement("br");
            inputId.setAttribute("id","input-for-id-"+i);
            inputId.setAttribute("class","form-control");
            inputId.setAttribute("value",data.id);
            inputId.setAttribute("readonly","readonly");
            inputTitle.setAttribute("value",data.title);
            inputTitle.setAttribute("id","input-for-title-"+i);
            inputTitle.setAttribute("type","text");
            inputTitle.setAttribute("class","form-control");
            textAreDescription.setAttribute("id","text-area-"+i);
            textAreDescription.setAttribute("value",data.description);
            textAreDescription.setAttribute("class","form-control");
            inputLinkOfImg.setAttribute("id","input-for-link-"+i);
            inputLinkOfImg.setAttribute("value",data.img);
            inputLinkOfImg.setAttribute("class","form-control");
            inputAddress.setAttribute("id","input-for-address-"+i);
            inputAddress.setAttribute("type","text");
            inputAddress.setAttribute("value",data.location);
            inputAddress.setAttribute("class","form-control");
            inputMatchDate.setAttribute("id","input-for-data-"+i);
            inputMatchDate.setAttribute("type","datetime-local");
            inputMatchDate.setAttribute("value",new Date(data.matchDate));
            inputMatchDate.setAttribute("class","form-control");
            inputCost.setAttribute("id","input-for-cost-"+i);
            inputCost.setAttribute("type","number");
            inputCost.setAttribute("value",data.price);
            inputCost.setAttribute("class","form-control");
            var span = document.createElement("span")
            span.innerText="Shower is present:"
            checkBoxShower.setAttribute("id","checkbox-for-shower-"+i);
            checkBoxShower.setAttribute("type","checkbox");
            if(data.showerPresent){
                checkBoxShower.setAttribute("checked","checked");
            }
            inputNumOfPlayer.setAttribute("id","input-for-numOfPeople-"+i);
            inputNumOfPlayer.setAttribute("type","number");
            inputNumOfPlayer.setAttribute("class","form-control");
            inputNumOfPlayer.setAttribute("value",data.amountOfPeople);
            inputTelOfContact.setAttribute("id","input-for-telContact-"+i);
            inputTelOfContact.setAttribute("type","text");
            inputTelOfContact.setAttribute("value",data.contactNumber);
            inputTelOfContact.setAttribute("class","form-control");
            selectTag.setAttribute("id","select-for-tag-"+i);
            selectTag.setAttribute("class","form-control");
            optionTagActually.setAttribute("id","option-for-tag-actually-"+i);
            optionTagActually.innerText=data.tag.name;
            optionTagFootball.setAttribute("id","option-for-tag-football-"+i);
            optionTagFootball.innerText="FOOTBALL"
            optionTagBasketball.setAttribute("id","option-for-tag-basketball-"+i);
            optionTagBasketball.innerText="BASKETBALL";
            optionTagVolleyball.setAttribute("id","option-for-tag-volleyball-"+i);
            optionTagVolleyball.innerText="VOLLEYBALL";
            optionTagTennis.setAttribute("id","option-for-tag-tennis-i"+i);
            optionTagTennis.innerText="TENNIS";
            selectTag.append(optionTagActually,optionTagFootball,optionTagBasketball,optionTagVolleyball,optionTagTennis);
            divForPostEdit.append(inputId,inputTitle,inputNumOfPlayer,selectTag,inputTelOfContact,textAreDescription,inputLinkOfImg,inputAddress,inputMatchDate,inputCost,span,checkBoxShower,br);

            //creating button for save
            var saveButton = document.createElement("button");
            saveButton.setAttribute("id","button-for-save"+i);
            saveButton.setAttribute("onclick","save("+i+")");
            saveButton.setAttribute("class","btn btn-success");
            saveButton.innerText="Save";
            divForPostEdit.append(saveButton);
            mainDiv.append(divForPostEdit);
        }
    })



}
function save(i) {

    var newPost = {
        'id': $('#input-for-id-'+i).val(),
        'title': $('#input-for-title-'+i).val(),
        'description':$('#text-area-'+i).val(),
        'img': $('#input-for-link-'+i).val(),
        'matchDate': new Date($('#input-for-data-'+i).val()),
        'contactNumber': $('#input-for-telContact-'+i).val(),
        'location' : $('#input-for-address-'+i).val(),
        'amountOfPeople': $('#input-for-numOfPeople-'+i).val(),
        'price': $('#input-for-cost-'+i).val(),
        'showerPresent' :$('#checkbox-for-shower-'+i).prop("checked"),
        'tag' :$('#select-for-tag-'+i).val(),
    }
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/admin/post",
        data:JSON.stringify(newPost),
        contentType:"application/json",
        success:function (data){
            destroyChildren($('#div-get-all-posts')[0]);
            $.ajax({
                type:"GET",
                url:"http://localhost:8080/post/all",
                success:function (data) {
                    printAllPost(data);
                }
            })
        },
        error:function (e) {
            console.log(e);
            alert("something wrong");
        }
    })
}

function deleteFun(i) {
var postId = $('#cell-with-id-'+i)[0].textContent;
    if(confirm("Do you want to delete this post?")){
        $.ajax({
            type:"DELETE",
            url:"http://localhost:8080/admin/post",
            data: {'id':postId},
            success:function (data){
                destroyChildren($('#div-get-all-posts')[0]);
                $.ajax({
                    type:"GET",
                    url:"http://localhost:8080/post/all",
                    success:function (data) {
                        printAllPost(data);
                    }
                })
            },
            error:function (e) {
                console.log(e);
                alert("something wrong");
            }
        })
    }

}



