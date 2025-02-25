package edu.wtbu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import edu.wtbu.service.UserService;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/userList")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		int startPage = 1;
		int pageSize = 10;
		int roleId = 0;
		String name = request.getParameter("name").toString();
		try {
			startPage = Integer.parseInt(request.getParameter("startPage").toString());
		} catch (Exception e) {
			// TODO: handle exception
			startPage = 1;
		}
		try {
			pageSize = Integer.parseInt(request.getParameter("pageSize").toString());
		} catch (Exception e) {
			// TODO: handle exception
			pageSize = 10;
		}
		try {
			roleId = Integer.parseInt(request.getParameter("roleId").toString());
		} catch (Exception e) {
			// TODO: handle exception
			roleId = 0;
		}
		response.getWriter().append(JSON.toJSONString(UserService.userList(roleId, name, startPage, pageSize),SerializerFeature.WriteDateUseDateFormat));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
