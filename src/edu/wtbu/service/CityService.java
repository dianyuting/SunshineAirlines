package edu.wtbu.service;

import java.util.HashMap;
import java.util.List;

import edu.wtbu.dao.CityDao;
import edu.wtbu.pojo.Result;

public class CityService {
	private static CityDao dao = new CityDao();
	public static Result getCityNames() {
		Result result = new Result("fail");
		List<HashMap<String, Object>> list = dao.getCityNames();
		if(list != null && list.size() > 0) {
			result.setFlag("success");
			result.setData(list);
		}
		return result;
	}
}
