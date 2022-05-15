package com;

import com.Complaint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProjectAPI
 */
@WebServlet("/ComplaintAPI")
public class ComplaintAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Complaint projectObj = new Complaint();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ComplaintAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String output = projectObj.insertItem
				(request.getParameter("i_type"), 
				request.getParameter("message"),
				request.getParameter("status"));
				

		response.getWriter().write(output);

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*Map paras = getParasMap(request);

		String output = projectObj.updateProject(paras.get("hidProjectIDSave").toString(),
				paras.get("i_type").toString(), 
				paras.get("message").toString(),
				paras.get("status").toString()
				

		);
		
		response.getWriter().write(output);*/
		
		int id = Integer.parseInt(request.getParameter("hidProjectIDSave"));
		String type = request.getParameter("i_type");
		String message = request.getParameter("message");
		String status = request.getParameter("status");
		
		String output = projectObj.updateProject(id, type, message, status);
		response.getWriter().write(output);


		
	}


	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map paras = getParasMap(request);

		String output = projectObj.deleteProject(paras.get("c_id").toString());

		response.getWriter().write(output);
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {

		}
		return map;

	}
}
