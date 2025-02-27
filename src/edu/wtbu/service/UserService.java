package edu.wtbu.service;

import java.util.HashMap;
import java.util.List;
import edu.wtbu.dao.UserDao;
import edu.wtbu.pojo.Page;
import edu.wtbu.pojo.Result;

public class UserService {
	private static UserDao dao = new UserDao();
	public static Result login(String email, String password) {
		Result result = new Result("fail");
		if(dao.isUserByEmail(email)) {
			HashMap<String, Object> map = dao.findUserByEmailAndPassword(email, password);
			if(map != null && map.size() > 0) {
				result.setFlag("success");
				result.setData(map);
			}else {
				result.setData("密码错误");
			}
		}else {
			result.setData("邮箱不存在");
		}
		return result;
	}
	
	public static Result userList(int roleId, String name, int startPage, int pageSize) {
		Result result = new Result("fail");
		List<HashMap<String, Object>> list = null;
		int total = 0;
		if(roleId > 0) {
			list = dao.findUserListByRoleId(roleId, name, startPage, pageSize);
			total = dao.findUserListCountByRoleId(roleId, name, startPage, pageSize);
		}else {
			list = dao.findUserList(name, startPage, pageSize);
			total = dao.findUserCountList(name, startPage, pageSize);
		}
		Page page = new Page(startPage, pageSize, total);
		result.setFlag("success");
		result.setData(list);
		result.setPage(page);
		return result;
	}
	
	public static Result addUser(String email, String firstName, String lastName, String gender, String dateOfBirth, String phone, String photo, String address, int roleId) {
		Result result = new Result("fail");
		String password = email.split("@")[0];
		password = password.length() > 6 ? password.substring(0, 6) : password;
		if(dao.isUserByEmail(email)) {
			result.setData("邮箱重复");
		}else if(dao.addUser(email, firstName, lastName, password, gender, dateOfBirth, phone, photo, address, roleId) > 0) {
			result.setFlag("success");
		}
		return result;
	}
	
	public static Result updatePasswor(int userId, String password) {
		Result result = new Result("fail");
		if(dao.isUserById(userId)) {
			if(dao.updatePassword(userId, password) > 0) {
				result.setFlag("success");
			}
		}else {
			result.setData("用户信息不存在");
		}
		return result;
	}
	
	public static Result findUserId(int userId) {
		Result result=new Result("fail",null,null);
		HashMap<String, Object> user=dao.findUserId(userId);
		if(user!=null) {
			result.setFlag("success");
			result.setData(user);
		}else{
			result.setData("用户信息不存在");
		}
		return result;
	}
}
