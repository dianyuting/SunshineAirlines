package edu.wtbu.dao;

import java.util.HashMap;
import java.util.List;

import edu.wtbu.Helper.Helper;

public class ScheduleDao {
	public List<HashMap<String, Object>> getSchedule(String fromCity, String toCity, String startDate, String endDate){
		String sql = "SELECT ScheduleId,Date,Time,DepartureAirport.IATACode AS DepartureAirportIATA,\r\n" + 
				"DepartureCity.CityName AS DepartCityName,\r\n" + 
				"ArrivalAirport.IATACode AS ArrivalAirportIATA,\r\n" + 
				"ArrivalCity.CityName AS ArriveCityName,\r\n" + 
				"aircraft.`Name`,\r\n" + 
				"EconomyPrice,\r\n" + 
				"FlightNumber,\r\n" + 
				"Gate,\r\n" + 
				"`Status` \r\n" + 
				"FROM `schedule`\r\n" + 
				"LEFT JOIN route ON `schedule`.RouteId = route.RouteId \r\n" + 
				"LEFT JOIN airport AS DepartureAirport ON DepartureAirport.IATACode = route.DepartureAirportIATA\r\n" + 
				"LEFT JOIN city AS DepartureCity ON DepartureCity.CityCode = DepartureAirport.CityCode\r\n" + 
				"LEFT JOIN airport AS ArrivalAirport ON ArrivalAirport.IATACode = route.ArrivalAirportIATA\r\n" + 
				"LEFT JOIN city AS ArrivalCity ON ArrivalCity.CityCode = ArrivalAirport.CityCode\r\n" + 
				"LEFT JOIN aircraft ON aircraft.AircraftId = `schedule`.AircraftId\r\n" + 
				"WHERE DepartureCity.CityName = ? AND ArrivalCity.CityName = ? AND Date BETWEEN ? AND ?";
		return Helper.executeQuery(sql, new Object[] {fromCity, toCity, startDate, endDate});
	}
	
	public boolean isScheduleById(int scheduleId) {
		String sql = "SELECT * FROM `schedule` WHERE ScheduleId = ?";
		List<HashMap<String, Object>> list = Helper.executeQuery(sql, new Object[] {scheduleId});
		if(list != null && list.size() > 0) return true;
		return false;
	}
	public int updateSchedule(String status, int scheduleId) {
		String sql = "UPDATE `schedule` SET `Status` = ? WHERE ScheduleId = ?";
		return Helper.executeUpdate(sql, new Object[] {status, scheduleId});
	}
	
	public List<HashMap<String, Object>> getTicketStatistics(String startDate, String endDate){
		String sql = "SELECT DATE_FORMAT(Date,'%Y-%m') AS Month,\r\n" + 
				"COUNT(ReservationId) AS FlightsAmount,\r\n" + 
				"COUNT(DISTINCT `schedule`.ScheduleId) AS TicketsAmount,\r\n" + 
				"SUM(Payment) AS TicketsRevenue\r\n" + 
				"FROM `schedule`\r\n" + 
				"LEFT JOIN flightreservation ON `schedule`.ScheduleId = flightreservation.ScheduleId\r\n" + 
				"WHERE Date BETWEEN ? AND ? AND `Status`= 'Confirmed'\r\n" + 
				"GROUP BY YEAR(Date),MONTH(Date)\r\n" + 
				"ORDER BY YEAR(Date),MONTH(Date)";
		return Helper.executeQuery(sql, new Object[] {startDate, endDate});
	}
}
