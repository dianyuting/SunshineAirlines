package edu.wtbu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import edu.wtbu.service.ScheduleService;

/**
 * Servlet implementation class GetScheduleServlet
 */
@WebServlet("/getSchedule")
public class GetScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String fromCity = request.getParameter("fromCity").toString();
		String toCity = request.getParameter("toCity").toString();
		String startDate = request.getParameter("startDate").toString();
		String endDate = request.getParameter("endDate").toString();
		response.getWriter().append(JSON.toJSONString(ScheduleService.getSchedule(fromCity, toCity, startDate, endDate),SerializerFeature.WriteDateUseDateFormat));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
