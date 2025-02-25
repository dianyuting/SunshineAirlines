var seachObj = {};
$(document).ready(function(){
    getUser(0,"",1,10);
    $(".step-backward").click(function(){
        if(seachObj.startPage == 1){
            alert("已经是第一页");
        }else{
            getUser(seachObj.roleId,seachObj.name,1,seachObj.pageSize);
        }
    })
    $(".chevron-left").click(function(){
        if(seachObj.startPage == 1){
            alert("已经是第一页");
        }else{
            getUser(seachObj.roleId,seachObj.name,seachObj.startPage - 1,seachObj.pageSize);
        }
    })
    $(".pageSelect").change(function(){
        getUser(seachObj.roleId,seachObj.name,$(this).val(),seachObj.pageSize);
    })
    $(".chevron-right").click(function(){
        if(seachObj.startPage == seachObj.totalPages){
            alert("已经是最后一页");
        }else{
            getUser(seachObj.roleId,seachObj.name,seachObj.startPage + 1,seachObj.pageSize);
        }
    })
    $(".step-forward").click(function(){
        if(seachObj.startPage == seachObj.totalPages){
            alert("已经是最后一页");
        }else{
            getUser(seachObj.roleId,seachObj.name,seachObj.totalPages,seachObj.pageSize);
        }
    })
    $(".searchBtn").click(function(){
        getUser($(".roleId").val(),$(".userName").val(),1,10);
    })
})
function getUser(roleId,name,startPage,pageSize){
    var param = {};
    param.roleId = roleId;
    param.name = name;
    param.startPage = startPage;
    param.pageSize = pageSize;
    seachObj .roleId = roleId;
    seachObj.name = name;
    seachObj.startPage = startPage;
    seachObj.pageSize = pageSize;
    $.ajax({
        url:"http://localhost:8080/Sunshine03/userList",
        data:param,
        typt:"post",
        success:function(msg){
            var obj = JSON.parse(msg);
            if(obj.flag == "success"){
                var data = obj.data;
                var html = "";
                for(let i = 0; i < data.length; i++){
                    var gender = data[i].Gender == 'M' ? "Male" : "Female";
                    var role = data[i].RoleId == 1 ? "Office User" : "Administrator";
                    html += "<tr><td>" + data[i].Email + "</td>";
                    html += "<td>" + data[i].FirstName + " " + data[i].LastName + "</td>";
                    html += "<td>" + gender + "</td>";
                    html += "<td>" + data[i].DateOfBirth + "</td>";
                    html += "<td>" + data[i].Phone + "</td>";
                    html += "<td>" + role + "</td>";
                    html += "<td><input class='editUser' style='width: 80px;  font-size: 16px;' type='button' value='Edit' onclick='edit(" + data[i].UserId +")'/></td></tr>";
                }
                $(".resultList").html(html);
                $(".resultList tr:even").addClass("tdcolor");
                $(".resultList tr:odd").addClass("tdcolor1");
                var totalPages = Math.ceil(obj.page.total / obj.page.pageSize);
                seachObj.totalPages = totalPages;
                var optionStr = "";
                for(let i = 1; i <= totalPages; i++){
                    if(obj.page.startPage == i){
                        optionStr += "<option value='" + i + "' selected>"+ i +"</option>";
                    }else{
                        optionStr += "<option value='" + i + "'>"+ i +"</option>";
                    }
                }
                $(".pageSelect").html(optionStr);
                $(".pageNum").text(totalPages);
                $(".totals").text(obj.page.total);
            }
        }
    })
}
function edit(userId){
    location.href = "AddUser.html";
}