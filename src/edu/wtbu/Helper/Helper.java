package edu.wtbu.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Helper {
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void setConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/session1?serverTimezone=GMT%2B8&useOldAliasMetadataBehavior=true","root","123456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<HashMap<String, Object>> executeQuery(String sql, Object[] param){
		List<HashMap<String, Object>> list =null;
		try {
			setConnection();
			pstmt = conn.prepareStatement(sql);
			if(param !=null) {
				for(int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			list = new ArrayList<HashMap<String,Object>>();
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				for(int i = 0; i < rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnName(i + 1), rs.getObject(i + 1));
				}
				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close();
		}
		return list;
	}
	
	public static int executeUpdate(String sql, Object[] param){
		int result = 0;
		try {
			setConnection();
			pstmt = conn.prepareStatement(sql);
			if(param !=null) {
				for(int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close();
		}
		return result;
	}

	private static void close() {
		// TODO Auto-generated method stub
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
