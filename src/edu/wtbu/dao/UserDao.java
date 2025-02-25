package edu.wtbu.dao;

import java.util.HashMap;
import java.util.List;

import edu.wtbu.Helper.Helper;

public class UserDao {
	public boolean isUserByEmail(String email){
		String sql = "SELECT * FROM users WHERE Email = ?";
		List<HashMap<String, Object>> list = Helper.executeQuery(sql, new Object[] {email});
		if(list != null && list.size() > 0) return true;
		return false;
	}
	
	public HashMap<String, Object> findUserByEmailAndPassword(String email, String password){
		String sql = "SELECT UserId,Email,FirstName,LastName,RoleId FROM users WHERE Email = ? AND `Password` = ?";
		List<HashMap<String, Object>> list = Helper.executeQuery(sql, new Object[] {email, password});
		if(list != null && list.size() > 0) return list.get(0);
		return null;
	}
	
	public List<HashMap<String, Object>> findUserList(String name, int startPage, int pageSize){
		String sql = "SELECT * FROM users WHERE FirstName LIKE ? OR LastName LIKE ? LIMIT ?,?";
		return Helper.executeQuery(sql, new Object[] { "%" + name + "%", "%" + name + "%", (startPage - 1) * pageSize, pageSize});
	}
	public List<HashMap<String, Object>> findUserListByRoleId(int roleId, String name, int startPage, int pageSize){
		String sql = "SELECT * FROM users WHERE RoleId = ? AND (FirstName LIKE ? OR LastName LIKE ?) LIMIT ?,?";
		return Helper.executeQuery(sql, new Object[] {roleId, "%" + name + "%", "%" + name + "%", (startPage - 1) * pageSize, pageSize});
	}
	public int findUserCountList(String name, int startPage, int pageSize){
		String sql = "SELECT COUNT(*) AS total FROM users WHERE FirstName LIKE ? OR LastName LIKE ?";
		return Integer.parseInt(Helper.executeQuery(sql, new Object[] { "%" + name + "%", "%" + name + "%"}).get(0).get("total").toString());
	}
	public int findUserListCountByRoleId(int roleId, String name, int startPage, int pageSize){
		String sql = "SELECT COUNT(*) AS total FROM users WHERE RoleId = ? AND (FirstName LIKE ? OR LastName LIKE ?)";
		return Integer.parseInt(Helper.executeQuery(sql, new Object[] {roleId, "%" + name + "%", "%" + name + "%"}).get(0).get("total").toString());
	}
	
	public int addUser(String email, String firstName, String lastName, String password, String gender, String dateOfBirth, String phone, String photo, String address, int roleId) {
		String sql = "INSERT INTO users(email,firstName,lastName,password,gender,dateOfBirth,phone,photo,address,roleId) \r\n" + 
				"VALUES(?,?,?,?,?,?,?,?,?,?)";
		return Helper.executeUpdate(sql, new Object[] {email, firstName, lastName, password, gender, dateOfBirth, phone, photo, address, roleId});
	}
	
	public boolean isUserById(int userId) {
		String sql = "SELECT * FROM users WHERE UserId = ?";
		List<HashMap<String, Object>> list = Helper.executeQuery(sql, new Object[] {userId});
		if(list != null && list.size() > 0) return true;
		return false;
	}
	public int updatePassword(int userId, String password) {
		String sql = "UPDATE users SET `Password` = ? WHERE UserId = ?";
		return Helper.executeUpdate(sql, new Object[] {password, userId});
	}
}
