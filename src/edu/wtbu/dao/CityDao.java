package edu.wtbu.dao;

import java.util.HashMap;
import java.util.List;

import edu.wtbu.Helper.Helper;

public class CityDao {
	public List<HashMap<String, Object>> getCityNames(){
		String sql = "select * from city";
		return Helper.executeQuery(sql, null);
	}
}
