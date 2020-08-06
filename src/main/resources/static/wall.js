$(document).ready(function () {
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/post/all",
        success: function (data) {
          writePosts(data);
        },
        error:function (e) {
            alert("Something wrong")
            console.log(e)
        }
    })
})

$('#refresh').click(function () {
    destroyChildren($('#my-wall')[0]);
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/post/all",
        success: function (data) {
           writePosts(data);

        }
    })
})

$('#button-apply-filters').click(function () {
    destroyChildren($('#my-wall')[0]);
    var dateFrom;
    var dateTo;
    var tag = $('#select-tag').val();
    if($('#form-date-match-filter').val()==="" ){
        dateFrom=new Date("2000-01-01");
    }else {
       dateFrom=new Date($('#form-date-match-filter').val());
    }

    if($('#to-date-match-filter').val()===""){
        dateTo=new Date("2050-01-01");
    }else{
        dateTo=new Date($('#to-date-match-filter').val());
    }
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/post/filtered",
        data: {'tag': tag,'fromDate' : dateFrom, 'toDate':dateTo},
        success: function (data){
           writePosts(data);
        }
    })
})

$('#button-add-form-post').click(function () {
    location.href="http://localhost:8080/post/"
})


$('#button-calendar-sort').click(function () {
    var dateFrom =new Date($('#form-date-match-filter').val());
    var dateTo = new Date($('#to-date-match-filter').val());

    $.ajax({
        type:"POST",
        url:"http://localhost:8080/post/dateFilter",
        data: {'fromDate': dateFrom, 'toDate': dateTo},
        success: function (data){
            destroyChildren($('#my-wall')[0]);
            writePosts(data);
        }
    })
})

$('#logout-link').click(function () {
    if(confirm("Are you sure you want log out?")){
        location.href="http://localhost:8080/logout";
    }else {
        console.log("stay")
    }
})

$('#link-get-my-posts').click(function () {
    destroyChildren($('#my-wall')[0]);
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/post/user",
        success: function (data) {
            writePosts(data);
        },
        error:function (e) {
            alert("Something wrong")
            console.log(e);
        }
    })
})

function destroyChildren(node)
{
    while (node.firstChild)
        node.removeChild(node.firstChild);
}

function writePosts(data) {
    var actuallyUser = $('#span-actually-user').data('value');
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
           var writeDeleteOrNot = false;
           var div = document.createElement("DIV");
           div.setAttribute("class","one-post")
           var h = document.createElement("H4");
           var p1 = document.createElement("P");
           var p2 = document.createElement("P");
           var p3 = document.createElement("P");
           var p4 = document.createElement("P");
           var p5 = document.createElement("P");
           var p6 = document.createElement("P");
           var p7 = document.createElement("P");
           var p8 = document.createElement("P");
           var divForMap = document.createElement("div");
           var buttonForMap = document.createElement("button");
           buttonForMap.setAttribute("onclick","printMap(\""+data[i].location+"\","+i+")");
           buttonForMap.innerText="Show on map!";
           divForMap.setAttribute("id","div-for-map-"+i);
           if(actuallyUser===data[i].user.login){
               writeDeleteOrNot=true;
               var aDelete = document.createElement("A");
               aDelete.setAttribute("id","aDelete-post-"+i);
               aDelete.setAttribute("class","aDelete-post");
               aDelete.setAttribute("onclick","deletePostFun("+data[i].id+")")
               aDelete.setAttribute("href","#");
               aDelete.innerText="delete X";
           }
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
           p7.setAttribute("class","location text-post");
           p8.setAttribute("class","location text-post");
           h.innerText=data[i].title;
           var dateWrite = new Date(data[i].matchDate);
           p1.innerText="Description: "+data[i].description;
           p2.innerText="Number for vacation: " + data[i].contactNumber;
           p3.innerText="Cost: " + data[i].price +" BYR";
           p4.innerText="Where: " + data[i].location;
           p5.innerText="Post id: " + data[i].id;
           p6.innerText="Post owner: " + data[i].user.login;
           p7.innerText=" When: "+ dateWrite
           p8.innerText= "Amount of people: " + data[i].amountOfPeople;
           aTag.innerText=data[i].tag.name;
           if(writeDeleteOrNot){
               div.append(h,br,p7,br,p8,br,img,p1,br,p2,br,p3,br,p4,br,aTag,br,p5,p6,divForMap,aDelete,buttonForMap);
           }else {
               div.append(h,br,p7,br,p8,br,img,p1,br,p2,br,p3,br,p4,br,aTag,br,p5,p6,divForMap,buttonForMap);
           }
           $('#my-wall').append(div);
       }
   }


}

function printMap(location,i) {
    var divForMap = document.getElementById('div-for-map-'+i);
    divForMap.setAttribute("style","height: 400px; width: 100%")
    divForMap.rend
    var Mapss = function geoadres(adress) {
        var resultlat = ''; var resultlng = '';
        $.ajax({
            async: false,
            dataType: "json",
            url: 'https://maps.googleapis.com/maps/api/geocode/json?address='+adress+'&sensor=false&key=AIzaSyAAsO9AatxituJSkZljKGpOMX6MDxdkbCw',
            success: function(data){
                for (var key in data.results) {
                    resultlat = data.results[key].geometry.location.lat;
                    resultlng = data.results[key].geometry.location.lng;
                } }
        });
        return { lat: resultlat, lng: resultlng}
    }
    var coordLat= new Mapss(location).lat;
    var coordLng= new Mapss(location).lng;
    var pos = {lat:coordLat,lng:coordLng};
    var opt = {
        center: pos,
        zoom:17,
    }
    var map = new google.maps.Map(document.getElementById('div-for-map-'+i),opt);
    var marker = new google.maps.Marker({
        position:pos,
        map: map,
    })
}


function deletePostFun(i) {
    $.ajax({
        type:"DELETE",
        url:"http://localhost:8080/post/post",
        data:{'id':i},
        success: function (data) {
            alert(data)
            destroyChildren($('#my-wall')[0]);
            $.ajax({
                type:"GET",
                url:"http://localhost:8080/post/all",
                success: function (data) {
                    writePosts(data);
                },
                error:function (e) {
                    alert("Something wrong")
                    console.log(e)
                }
            })
        },
        error:function (e) {
            alert("Something wrong")
            console.log(e);
        }
    })


}

