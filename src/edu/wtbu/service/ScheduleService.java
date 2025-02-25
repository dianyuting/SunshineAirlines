package edu.wtbu.service;

import edu.wtbu.dao.ScheduleDao;
import edu.wtbu.pojo.Result;

public class ScheduleService {
	private static ScheduleDao dao = new ScheduleDao();
	public static Result getSchedule(String fromCity, String toCity, String startDate, String endDate) {
		Result result = new Result("fail");
		startDate += " 00:00:00";
		endDate += " 23:59:59";
		result.setFlag("success");
		result.setData(dao.getSchedule(fromCity, toCity, startDate, endDate));
		return result;
	}
	
	public static Result updateSchedule(String status, int scheduleId) {
		Result result = new Result("fail");
		if(dao.isScheduleById(scheduleId)) {
			if(dao.updateSchedule(status, scheduleId) > 0) {
				result.setFlag("success");
			}
		}else {
			result.setData("航班计划不存在");
		}
		return result;
	}
	
	public static Result getTicketStatistics(String startDate, String endDate) {
		startDate += "-01 00:00:00";
		int year = Integer.parseInt(endDate.split("-")[0]);
		int month = Integer.parseInt(endDate.split("-")[1]);
		if(month >= 12) {
			endDate = (year + 1) + "-01-01 00:00:00";
		}else {
			endDate = year + "-" + (month + 1) + "-01 00:00:00";
		}
		Result result = new Result("success", dao.getTicketStatistics(startDate, endDate));
		return result;
	}
}
