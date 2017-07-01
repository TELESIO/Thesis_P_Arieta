package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.PointOnMap;
import persistence.CriticalPointOnMapJDBC;
import persistence.Db_connection;
import persistence.IRIPointOnMapJDBC;
import persistence.PointOnMapDAO_JDBC;
import persistence.dao.DAOPointOnMap;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/map")
public class PointController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PointController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Gson json;
		List<PointOnMap> point;
		DAOPointOnMap point_retrive;

		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "init":
				System.out.println("Punti Iniziali");
				json = new Gson();
				point_retrive = new PointOnMapDAO_JDBC(new Db_connection());
				point = point_retrive.getPointOnMap();
				request.setAttribute("result", json.toJson(point));
				request.getRequestDispatcher("jsp/home.jsp").forward(request, response);
				break;
			case "SimplePoints":
				System.out.println("Punti Semplici");
				json = new Gson();
				point_retrive = new PointOnMapDAO_JDBC(new Db_connection());
				point = point_retrive.getPointOnMap();
				response.getWriter().println(json.toJson(point));
				break;
			case "CriticalPoints":
				System.out.println("Punti Critici");
				json = new Gson();
				point_retrive = new CriticalPointOnMapJDBC(new Db_connection());
				point = point_retrive.getPointOnMap();
				response.getWriter().println(json.toJson(point));
				break;

			case "IRI":
				json = new Gson();
				point_retrive = new IRIPointOnMapJDBC(new Db_connection());
				point = point_retrive.getPointOnMap();
				response.getWriter().println(json.toJson(point));
				break;

			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
