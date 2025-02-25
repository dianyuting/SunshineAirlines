package edu.wtbu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import edu.wtbu.service.UserService;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String email = request.getParameter("email").toString();
		String firstName = request.getParameter("firstName").toString();
		String lastName = request.getParameter("lastName").toString();
		String gender = request.getParameter("gender").toString();
		String dateOfBirth = request.getParameter("dateOfBirth").toString();
		String phone = request.getParameter("phone").toString();
		String photo = request.getParameter("photo").toString();
		String address = request.getParameter("address").toString();
		int roleId = 1;
		try {
			roleId = Integer.parseInt(request.getParameter("roleId").toString());
		} catch (Exception e) {
			// TODO: handle exception
			roleId = 1;
		}
		response.getWriter().append(JSON.toJSONString(UserService.addUser(email, firstName, lastName, gender, dateOfBirth, phone, photo, address, roleId)));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
