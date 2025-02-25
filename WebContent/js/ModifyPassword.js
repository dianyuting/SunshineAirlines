$(document).ready(function(){
    var userStr = localStorage.getItem("user");
    var user = JSON.parse(userStr);
    $(".email").val(user.data.Email);
    $(".name").val(user.data.FirstName + " " + user.data.LastName);
    $(".submit").click(function(){
        if($(".newPassword").val().length < 8 || $(".newPassword").val().length > 16){
            alert("请输入符合要求的密码");
            return;
        }
        if($(".newPassword").val() != $(".newPasswordAgain").val()){
            alert("两次输入密码不一致，请重新输入");
            return;
        }
        var param = {};
        param.userId = user.data.UserId;
        param.password = $(".newPassword").val();
        $.ajax({
            url:"http://localhost:8080/Sunshine03/updatePassword",
            data:param,
            type:"post",
            success:function(msg){
                var obj = JSON.parse(msg);
                if(obj.flag == "success"){
                    localStorage.setItem("user","");
                    location.href = "Login.html";
                }else{
                    alert(obj.data);
                }
            }
        })
    })
    $(".cancel").click(function(){
        $(".newPassword").val("");
        $(".newPasswordAgain").val("")
    })
})