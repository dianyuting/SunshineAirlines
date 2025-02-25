$(document).ready(function(){
    $.ajax({
        url:"http://localhost:8080/Sunshine03/getCityNames",
        data:"",
        type:"post",
        success:function(msg){
            var obj = JSON.parse(msg);
            if(obj.flag == "success"){
                var data = obj.data;
                var optionStr = "";
                for(let i = 0; i <data.length; i++){
                    optionStr += "<option value='" + data[i].CityName +"'>" + data[i].CityName +"</option>";
                }
                $(".fromCity").html(optionStr);
                $(".toCity").html(optionStr);
            }
        }
    })
    $(".changeicon").click(function(){
        var str = $(".fromCity").val();
        $(".fromCity").val($(".toCity").val());
        $(".toCity").val(str);
    })
    $(".searchBtn").click(function(){
        getSchedule();
    })
})

function getSchedule(){
    var param = {};
        param.fromCity = $(".fromCity").val();
        param.toCity = $(".toCity").val();
        param.startDate = $(".startDate").val();
        param.endDate = $(".endDate").val();
        $.ajax({
            url:"http://localhost:8080/Sunshine03/getSchedule",
            data:param,
            type:"post",
            success:function(msg){
                var obj = JSON.parse(msg);
                if(obj.flag == "success"){
                    var data = obj.data;
                    var html = "";
                    for(let i = 0; i < data.length; i ++){
                        var str = data[i].Status == "Confirmed" ? "Cancel" : "Confirm";
                        html += "<tr><td>" + data[i].Date.substring(0,10) + "</td>";
                        html += "<td>" + data[i].Time.substring(0,5) + "</td>";
                        html += "<td>" + data[i].DepartCityName + "/" + data[i].DepartureAirportIATA + "</td>";
                        html += "<td>" + data[i].ArriveCityName + "/" + data[i].ArrivalAirportIATA + "</td>";
                        html += "<td>" + data[i].Name + "</td>";
                        html += "<td>" + data[i].EconomyPrice + "</td>";
                        html += "<td>" + data[i].FlightNumber + "</td>";
                        html += "<td>" + data[i].Gate + "</td>";
                        html += "<td>" + data[i].Status + "</td>";
                        html += "<td><input type='button' value='" + str +"' onclick='update("+ data[i].ScheduleId +",\"" + str + "ed" + "\")'/></td></tr>";
                    }
                    $(".resultList").html(html);
                    $(".resultList tr:even").addClass("tdcolor");
                    $(".resultList tr:odd").addClass("tdcolor1");
                }
            }
        })
}

function update(scheduleId,status){
    var param = {};
    param.scheduleId = scheduleId;
    param.status = status;
    $.ajax({
        url:"http://localhost:8080/Sunshine03/updateSchedule",
        data:param,
        type:"post",
        success:function(msg){
            var obj = JSON.parse(msg);
            if(obj.flag == "success"){
                getSchedule();
            }
        }
    })
}