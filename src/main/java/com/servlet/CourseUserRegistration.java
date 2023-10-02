package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.DBFunctions;
import com.database.DBmanager;
import com.database.Email;
import com.entity.CourseRegister;
import com.entity.User;

@WebServlet("/CourseUserRegistration")
public class CourseUserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CourseUserRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// String username = request.getParameter("username");
		String email = request.getParameter("email"); // this is the email i will sent to user (different optional)
		String course_select = request.getParameter("course_selection");
		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user"); // i will use this to store the summary
		// should i sent to the email that user enter or the user session account email?
//		if (!email.equals(userSession.getEmail())) {
//			System.out.println("User email is not same with the session user.");
//			response.sendRedirect("./index.jsp?error=Your email are not same!");
//		}

		if (userSession == null) {
			System.out.println("User not in session or not logged in. Redirecting to index.jsp from admin dashboard");
			response.sendRedirect("./index.jsp?error=Sorry, you are not a registered user! Please sign up first");
			return;
		}

		DBmanager dbMgr = new DBmanager();
		DBFunctions dbFunctions = new DBFunctions();
		Connection conn = null;

		try {
			conn = dbMgr.getConnection();
			if (conn != null) {
				// First, retrieve the user's ID based on their email
				String query_select = "SELECT id FROM TrainingDB.users WHERE email=?";
				String query_course = "SELECT * FROM TrainingDB.courses WHERE id=?";
				PreparedStatement prepare_statement_select = conn.prepareStatement(query_select);
				PreparedStatement prepare_statement_course = conn.prepareStatement(query_course);
				prepare_statement_course.setInt(1, Integer.parseInt(course_select));

				prepare_statement_select.setString(1, userSession.getEmail());
				ResultSet resultSet = prepare_statement_select.executeQuery();
				ResultSet resultSet_course = prepare_statement_course.executeQuery();

				if (resultSet.next() && resultSet_course.next()) {
					int userId = resultSet.getInt("id");
					String courseName = resultSet_course.getString("course_name");
					resultSet.close();
					resultSet_course.close();
					prepare_statement_select.close();
					prepare_statement_course.close();

					String query = "INSERT INTO TrainingDB.user_register_course (user_id, course_id) VALUES (?, ?)";
					PreparedStatement prepare_statement = conn.prepareStatement(query);
					prepare_statement.setInt(1, userId);
					prepare_statement.setInt(2, Integer.parseInt(course_select));
					int result = prepare_statement.executeUpdate();
					prepare_statement.close();

					if (result == 1) {
						try {
							List<CourseRegister> Courses = dbFunctions
									.getRegistrationRecordPerEmail(userSession.getEmail());
							new Email().sendEmailCourse(email, courseName);
							request.setAttribute("UserRegisterCourseList", Courses);
							System.out.println(Courses);
							RequestDispatcher rd = request
									.getRequestDispatcher("./thanks_you.jsp?msg=Registration was successful.");
							rd.forward(request, response);
						} catch (Exception e) {
							System.out.println("Something went wrong: " + e);
						}
					} else {
						response.sendRedirect("./index.jsp?msg=Registration Failed!");
					}
				} else {
					response.sendRedirect(
							"./AdminDashboard?msg=Sorry, you are not a registered user! Please sign up first");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		doGet(request, response);
	}

}
