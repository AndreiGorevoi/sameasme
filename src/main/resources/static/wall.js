$(document).ready(function () {
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/post/getAllPosts",
        success: function (data) {
          writePosts(data);
        }
    })
})




$('#refresh').click(function () {
    // TODO
    // destroyChildren($('#my-wall').get());
    destroyChildren(document.getElementById('my-wall'));
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/post/getAllPosts",
        success: function (data) {
           writePosts(data);

        }
    })
})

$('#button-apply-filters').click(function () {
    destroyChildren(document.getElementById('my-wall'));
    var tag = $('#select-tag').val();
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/post/getAllPostsByTeg",
        data: {'tag': tag},
        success: function (data){
           writePosts(data);
        }
    })
})

$('#button-add-form-post').click(function () {
    location.href="http://localhost:8080/post/addPostForm"
})

function destroyChildren(node)
{
    while (node.firstChild)
        node.removeChild(node.firstChild);
}

function writePosts(data) {
   if(data.length==0){
       var div = document.createElement("DIV");
       div.setAttribute("class","one-post")
       var h = document.createElement("H4");
       h.setAttribute("class","h-post");
       h.innerText="Sorry! No match for your request";
       div.append(h);
       $('#my-wall').append(div);
   }else {
       for(var i=0;i<data.length;i++){
           var div = document.createElement("DIV");
           div.setAttribute("class","one-post")
           var h = document.createElement("H4");
           var p1 = document.createElement("P");
           var p2 = document.createElement("P");
           var p3 = document.createElement("P");
           var p4 = document.createElement("P");
           var br = document.createElement("BR")
           var img = document.createElement("IMG")
           var aTag = document.createElement("A")
           h.setAttribute("class","h-post");
           aTag.setAttribute("href","#")
           img.setAttribute("src",data[i].img);
           img.setAttribute("class","img-post");
           p1.setAttribute("class","text-post");
           p2.setAttribute("class","contact-number-text text-post");
           p3.setAttribute("class","money text-post");
           p4.setAttribute("class","location text-post");
           h.innerText=data[i].title;
           p1.innerText="Description: "+data[i].description +" When: "+ data[i].matchDate;
           p2.innerText="Number for vacation: " + data[i].contactNumber;
           p3.innerText="Cost: " + data[i].price +" BYR";
           p4.innerText="Where: " + data[i].location;
           aTag.innerText=data[i].tag.name;
           div.append(h,br,img,p1,br,p2,br,p3,br,p4,br,aTag);
           $('#my-wall').append(div);
       }
   }


}