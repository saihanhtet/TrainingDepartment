package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.*;
import com.entity.Course;
import com.entity.CourseRegister;
import com.entity.User;

@WebServlet("/AdminDashboard")
public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		DBFunctions dbFunctions = new DBFunctions();

		if (userSession != null && userSession.isLoggedIn()) {
			try {
				List<Course> Courses = dbFunctions.getCoursesFromDatabase();
				List<User> Users = dbFunctions.getUsersFromDatabase();
				List<CourseRegister> course_register = dbFunctions.getRegistrationRecord();
				request.setAttribute("List", Courses);
				request.setAttribute("UserList", Users);
				request.setAttribute("UserRegisterList", course_register);
				RequestDispatcher rd = request.getRequestDispatcher("/admin_dashboard.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				System.out.println("Something went wrong: " + e);
			}

		} else

		{
			System.out.println("User not in session or not logged in. Redirecting to index.jsp from admin dashboard");
			response.sendRedirect("./index.jsp?msg=Sorry, you are not a registered user! Please sign up first");
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String course_id = request.getParameter("course_id");
		String course_name = request.getParameter("course_name");
		String course_price = request.getParameter("course_price");
		DBFunctions dbFunctions = new DBFunctions();
		System.out.println(course_id);
		System.out.println(course_name);
		System.out.println(course_price);
		boolean result = dbFunctions.updateCourse(Integer.parseInt(course_id), course_name, course_price);
		if (result) {
			List<Course> Courses = dbFunctions.getCoursesFromDatabase();
			List<User> Users = dbFunctions.getUsersFromDatabase();
			List<CourseRegister> course_register = dbFunctions.getRegistrationRecord();
			request.setAttribute("List", Courses);
			request.setAttribute("UserList", Users);
			request.setAttribute("UserRegisterList", course_register);
			RequestDispatcher rd = request.getRequestDispatcher("/admin_dashboard.jsp?msg=Successfully updated course.");
			rd.forward(request, response);
		}
		else {
			response.sendRedirect("./admin_dashboard.jsp?msg=Error at updating the course");
		}
	}

}
