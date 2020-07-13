$('#delete-user-button').click(function () {
    var userIdForDelete = $('#id-for-delete-input').val();
    $.ajax({
        type:"DELETE",
        url:"http://localhost:8080/admin/deleteUser",
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
    url:"http://localhost:8080/user/getAllUsers",
    success:function (data) {
        var divElement = $('#block-for-users-list');
        var table = document.createElement("TABLE");
        for(var i=0;i<data.length;i++){
            var listOfRoles = "";
            for(var j=0;j<data[i].roles.length;j++){
                listOfRoles+=data[i].roles[j].name+", ";
            }
            var row = table.insertRow(-1);
            row.insertCell(-1).innerText="id: " + data[i].id;
            row.insertCell(-1).innerText="Login: " + data[i].login;
            row.insertCell(-1).innerText="Roles: " + listOfRoles;
        }
        divElement.append(table);
    }
})
})

$('#change-user-role-button').click(function () {
    var userId = $('#id-user-change-role-input').val();
    var newUserRole = $('#select-user-change-role').val();
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/admin/addrole",
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
        type:"POST",
        url:"http://localhost:8080/admin/deleterole",
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