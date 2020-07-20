$('#delete-user-button').click(function () {
    var userIdForDelete = $('#id-for-delete-input').val();
    $.ajax({
        type:"DELETE",
        url:"http://localhost:8080/admin/user",
        data: {'id': userIdForDelete},
        success:function (data){
            console.log(data);
            if(data==true){
                alert("User has been deleted");
            }
        },
        error:function (e) {
            alert("something wrong");
        }
    })

})

$('#button-list-of-users').click(function () {
    destroyChildren(document.getElementById('block-for-users-list'))
$.ajax({
    type:"GET",
    url:"http://localhost:8080/user/all",
    success:function (data) {
        printListOfUser(data);
    }
})

})

$('#change-user-role-button').click(function () {
    var userId = $('#id-user-change-role-input').val();
    var newUserRole = $('#select-user-change-role').val();
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/admin/role",
        data:{"id": userId , "role": newUserRole},
        success:function (data) {
            if(data==true){
                alert("The role has been added");
            }else {
                alert("This user already has had the role");
            }
        },
        error:function(e){
            alert("something wrong");
    }
    })
})
$('#change-user-role-button-delete').click(function () {
    var userId = $('#id-user-change-role-input').val();
    var newUserRole = $('#select-user-change-role').val();
    $.ajax({
        type:"DELETE",
        url:"http://localhost:8080/admin/role",
        data:{"id": userId , "role": newUserRole},
        success:function (data) {
            if(data==true){
                alert("The role has been removed");
            }else {
                alert("This user already hasn't had the role");
            }
        },
        error:function(e){
            alert("something wrong");
        }
    })
})

function destroyChildren(node)
{
    while (node.firstChild)
        node.removeChild(node.firstChild);
}



function printListOfUser(data) {
    destroyChildren(document.getElementById('block-for-users-list'))
    var divElement = $('#block-for-users-list');
    var table = document.createElement("TABLE");
    table.setAttribute("class","table table-dark");
    var hr = table.insertRow(-1);
    hr.insertCell(-1).innerHTML="<strong>Id</strong>"
    hr.insertCell(-1).innerHTML="<strong>Login</strong>"
    hr.insertCell(-1).innerHTML="<strong>Name</strong>"
    hr.insertCell(-1).innerHTML="<strong>Roles</strong>"
    for(var i=0;i<data.length;i++){
        var listOfRoles = "";
        for(var j=0;j<data[i].roles.length;j++){
            listOfRoles+=data[i].roles[j].name+", ";
        }
        var row = table.insertRow(-1);
        row.setAttribute("id","row-id"+i);
        let cell1 = row.insertCell(-1);
        let cell2 = row.insertCell(-1);
        let cell3 = row.insertCell(-1);
        let cell4 = row.insertCell(-1);
        cell1.setAttribute("id","cell-with-id-"+i);
        cell1.innerText=data[i].id;
        cell2.setAttribute("id","cell-with-login-"+i);
        cell2.innerText=data[i].login;
        cell3.setAttribute("id","cell-with-name-"+i);
        cell3.innerText=data[i].name;
        cell4.setAttribute("id","cell-with-roles-"+i);
        cell4.innerText=listOfRoles;

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
//function for actions after editButton is clicked;
function editFun(i) {
    if($('#button-save-changes-'+i)[0]===undefined){
        let inputElement = document.createElement("input");
        let inputElementName = document.createElement("input");
        var selectElementRoles = document.createElement("select");
        var adminRole = document.createElement("option");
        var moderatorRole = document.createElement("option");
        var userRole = document.createElement("option");
        adminRole.innerText="ADMIN";
        moderatorRole.innerText="MODERATOR";
        userRole.innerText="USER";
        selectElementRoles.setAttribute("id","select-for-new-roles-"+i);
        selectElementRoles.setAttribute("multiple","multiple");
        selectElementRoles.setAttribute("class","custom-select");
        selectElementRoles.append(adminRole,moderatorRole,userRole);
        inputElement.setAttribute("id","input-for-new-login-"+i);
        inputElement.setAttribute("value",$('#cell-with-login-'+i)[0].textContent);
        inputElement.setAttribute("class","form-control");
        inputElementName.setAttribute("id","input-for-new-name-"+i);
        inputElementName.setAttribute("value",$('#cell-with-name-'+i)[0].textContent);
        inputElementName.setAttribute("class","form-control");
        $('#cell-with-login-'+i).html(inputElement);
        $('#cell-with-name-'+i).html(inputElementName);
        $('#cell-with-roles-'+i).html(selectElementRoles);
        let htmlButtonElement = document.createElement("button");
        let htmlButtonElementDelete = document.createElement("button");
        htmlButtonElement.setAttribute("id","button-save-changes-"+i);
        htmlButtonElement.setAttribute("class","btn btn-success");
        htmlButtonElementDelete.setAttribute("class","btn btn-danger")
        htmlButtonElementDelete.setAttribute("id","button-delete-user-"+i);
        htmlButtonElement.setAttribute("onclick","sendNewInfoFun("+i+")");
        htmlButtonElementDelete.setAttribute("onclick","deleteUserFun("+i+")");
        htmlButtonElement.innerText="Save";
        htmlButtonElementDelete.innerText="Delete";
        $('#row-id'+i).append(htmlButtonElement,htmlButtonElementDelete);
    }
}
//Function to send new edit info for user
function sendNewInfoFun(i) {
    var newUser={
        'id' : $('#cell-with-id-'+i)[0].textContent,
        'newLogin': $('#input-for-new-login-' + i).val(),
        'newName': $('#input-for-new-name-' + i).val(),
        'newRoles': $('#select-for-new-roles-'+i).val(),
    }

    $.ajax({
        type:"POST",
        url:"http://localhost:8080/admin/user",
        data:JSON.stringify(newUser),
        contentType:"application/json",
        success:function (data){
            printListOfUser(data);
        },
        error:function (e) {
            console.log(e);
            alert("something wrong");
        }
    })
}
//Function for delete user
function deleteUserFun(i) {
    var userId = $('#cell-with-id-'+i)[0].textContent;
    if(confirm("Do you want to delete this user?")){
        $.ajax({
            type:"DELETE",
            url:"http://localhost:8080/admin/user",
            data:{'id':userId},
            success:function (data){
                printListOfUser(data);
            },
            error:function (e) {
                console.log(e);
                alert("something wrong");
            }
        })
    }
}