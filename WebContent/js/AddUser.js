$(document).ready(function(){
    var userId = localStorage.getItem("userId");
    if(userId == 0){
        $('.headtitle').text("Add User");
    }else{
        $(".headtitle").text("Edit User");
        editUserShow(userId);
    }

    $(".upload-input").change(function(){
        var file = this.files[0];
        var read = new FileReader();
        read.onload = function(ever){
            $(".photo").attr("src",ever.target.result);
        }
        read.readAsDataURL(file);
    })
    $(".submit").click(function(){
        var param = {};
        param.email = $(".email").val();
        param.firstName = $(".firstName").val();
        param.lastName = $(".lastName").val();
        param.gender = $(".genderMale").prop("checked") ? "M" : "F";
        param.dateOfBirth = $(".dateOfBirth").val();
        param.phone = $(".phone").val();
        param.photo = $(".photo").attr("src");
        param.photo = encodeURIComponent(param.photo);
        param.address = $(".address").val();
        param.roleId = $(".roleUser").prop("checked") ? 1 : 2;
        $.ajax({
            url:"http://localhost:8080/Sunshine03/addUser",
            data:param,
            type:"post",
            success:function(msg){
                var obj = JSON.parse(msg);
                if(obj.flag == "success"){
                    location.href = "UserManagement.html"
                }else{
                    alert(obj.data);
                }
            }
        })
    })
    $(".cancel").click(function(){
        location.href = "UserManagement.html";
    })
})
function editUserShow(userId){
    $.ajax({
        url:"http://localhost:8080/Sunshine03/getUserInfo",
        data:"userId=" +userId,
        type:"post",
        success:function(msg){
            var obj = JSON.parse(msg);
            if(obj.flag == "success"){
                $(".email").val(obj.data.Email);
                $(".firstName").val(obj.data.FirstName);
                $(".lastName").val(obj.data.LastName);
                $(".dateOfBirth").val(obj.data.DateOfBirth);
                $(".phone").val(obj.data.Phone);
                $(".address").val(obj.data.Address);
                $(".photo").attr("src",obj.data.Photo);
                obj.data.RoleId == 1 ? $(".roleUser").prop("checked",true):$(".roleAdministrator").prop("checked",true);
                obj.data.Gender == "M" ? $(".genderMale").prop("checked",true):$(".genderFemale").prop("checked",true);
            }
        }
    })
}