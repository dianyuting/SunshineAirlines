$(document).ready(function(){
    try{
        var userStr = localStorage.getItem("user");
        var user = JSON.parse(userStr);
        var d = new Date().setDate(new Date(user.date).getDate() + 7);
        if(new Date() < d){
            jump(user.data.RoleId);
        }
    }catch{

    }
    $(".loginbutton").click(function(){
        var param = {};
        param.email = $(".email").val();
        param.password = $(".password").val();
        $.ajax({
            url:"http://localhost:8080/Sunshine03/login",
            data:param,
            type:"post",
            success:function(msg){
                var obj = JSON.parse(msg);
                if(obj.flag == "success"){
                    if($(".is7day").is(":checked")){
                        var user = {};
                        user.date = new Date();
                        user.data = obj.data;
                        localStorage.setItem("user",JSON.stringify(user));
                    }
                    jump(obj.data.RoleId);
                }else{
                    $(".alertInfo").text(obj.data);
                }
            }
        })
    })
})
function jump(roleId){
    if(roleId == 1){
        location.href = "ModifyPassword.html";
    }else if(roleId == 2){
        location.href = "FlightScheduleManagement.html";
    }
}