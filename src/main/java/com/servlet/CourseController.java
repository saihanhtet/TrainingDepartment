package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

import com.database.ExcelReader;
import com.database.DBFunctions;
import com.entity.Course;
import com.entity.User;

/**
 * Servlet implementation class CourseController
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB
		maxFileSize = 1024 * 1024 * 5, // 5 MB
		maxRequestSize = 1024 * 1024 * 5 * 5 // 25 MB
)
@WebServlet("/CourseController/*")
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("Session before invalidate: " + request.getSession(false));
			HttpSession session = request.getSession();
			DBFunctions dbFunctions = new DBFunctions();
			User userSession = (User) session.getAttribute("user");
			String pathInfo = request.getPathInfo();
			System.out.println(pathInfo);

			if (userSession != null && userSession.isLoggedIn()) {
				String username = userSession.getUsername();
				String email = userSession.getEmail();
				System.out.println("Current username: " + username);
				System.out.println("Current useremail: " + email);

				// do the path action if the user is login
				if (pathInfo != null && pathInfo.startsWith("/delete")) {
					String[] pathParts = pathInfo.split("/");
					if (pathParts.length == 3) {
						int courseId = Integer.parseInt(pathParts[2]);
						boolean updateResult = dbFunctions.deleteCourse(courseId);
						if (updateResult) {
							response.sendRedirect(
									request.getContextPath() + "/AdminDashboard?msg=Successfully deleted the course!");
						} else {
							response.sendRedirect(
									request.getContextPath() + "/AdminDashboard?msg=Error at deleting the course!");
						}
						return;
					}
				} else if (pathInfo != null && pathInfo.startsWith("/update")) {
					String[] pathParts = pathInfo.split("/");
					if (pathParts.length == 3) {
						int courseId = Integer.parseInt(pathParts[2]);
						String newName = request.getParameter("newName");
						String newPrice = request.getParameter("newPrice");
						boolean updateResult = dbFunctions.updateCourse(courseId, newName, newPrice);
						if (updateResult) {
							response.sendRedirect(
									request.getContextPath() + "/AdminDashboard?msg=Successfully updated the course!");
						} else {
							response.sendRedirect(
									request.getContextPath() + "/AdminDashboard?msg=Error at updating the course!");
						}
						return;

					}
				} else {
					try {
						List<Course> Courses = dbFunctions.getCoursesFromDatabase();
						request.setAttribute("user_email", (String) userSession.getEmail());
						request.setAttribute("user_name", (String) userSession.getUsername());
						request.setAttribute("List", Courses);
						RequestDispatcher rd = request.getRequestDispatcher("/course_view.jsp");
						rd.forward(request, response);
					} catch (Exception e) {
						System.out.println("Something went wrong: " + e);
					}
				}

			} else {
				System.out.println("User not in session or not logged in. Redirecting to index.jsp");
				response.sendRedirect("./index.jsp?msg=Sorry, you are not a registered user! Please sign up first");
				return;
			}
		} catch (

		Exception ex) {
			throw new ServletException(ex);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String form = request.getParameter("form");
		DBFunctions dbFunctions = new DBFunctions();

		if (form.equals("course_excel_form")) {
			Part filePart = request.getPart("course_file");
			if (filePart != null) {
				try (InputStream fileStream = filePart.getInputStream()) {
					List<Course> courseList = ExcelReader.readCoursesFromExcel(fileStream);
					//List<> errorCourseList  = new ArrayList<>();
					for (Course course : courseList) {
						try {
							dbFunctions.insertCourses(course.getName(), course.getPrice());
							System.out.println(course.getName() + " : " + course.getPrice());
						} catch(Exception e) {
							System.out.println(course.getName() + " : is duplicate.");
						}
					}
					response.sendRedirect("AdminDashboard?msg=Successfully added the Excel data!");
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("AdminDashboard?msg=Please check your excel file");
				}
			}
		} else {
			String course_name = request.getParameter("course_name");
			String course_price = request.getParameter("course_price");
			Boolean result = dbFunctions.insertCourses(course_name, course_price);
			if (result.equals(true)) {
				response.sendRedirect("AdminDashboard?msg=Successfully added the course.");
			} else {
				response.sendRedirect("AdminDashboard?msg=Something went wrong.");
			}
		}

		doGet(request, response);
	}

}
