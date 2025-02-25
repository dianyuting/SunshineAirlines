$(document).ready(function(){
    if(new Date().getMonth() < 9){
        var s = new Date().getFullYear() + "-0" + (new Date().getMonth() + 1);
    }else{
        var s = new Date().getFullYear() + "-" + (new Date().getMonth() + 1);
    }
    $(".endDate").prop("max",s);
    $(".startDate").change(function(){
        var endDate = $(".endDate").val();
        if(endDate == ""){
            return;
        }else if($(".startDate").val() > endDate){
            $(".startDate").val(endDate);
            alert("开始时间必须早于结束时间")
        }
    })
    $(".endDate").change(function(){
        var startDate = $(".startDate").val();
        if(startDate == ""){
            return;
        }else if(startDate > $(".endDate").val()){
            $(".endDate").val(startDate);
            alert("开始时间必须早于结束时间")
        }
    })
    $(".stat").click(function(){
        var param = {};
        param.startDate = $(".startDate").val();
        param.endDate = $(".endDate").val();
        $.ajax({
            url:"http://localhost:8080/Sunshine03/getTicketStatistics",
            data:param,
            type:"post",
            success:function(msg){
                var obj = JSON.parse(msg);
                if(obj.flag == "success"){
                    var data = obj.data;
                    var html = "";
                    for(let i = 0; i < data.length; i++){
                        html += "<tr><td>" + data[i].Month + "</td><td>" 
                        + data[i].FlightsAmount + "</td><td>" 
                        + data[i].TicketsAmount + "</td><td>" 
                        + data[i].TicketsRevenue + "</td></tr>"
                    }
                    $(".resultList").html(html);
                    $(".resultList tr:even").addClass("tdcolor");
                    $(".resultList tr:odd").addClass("tdcolor1");
                }
            }
        })
    })
})