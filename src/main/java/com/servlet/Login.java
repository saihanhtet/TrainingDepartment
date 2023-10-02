package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.DBmanager;
import com.database.HashKeyUtil;
import com.entity.User;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "add, update, delete, view", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get user input from the form
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String hashedPassword = HashKeyUtil.hashSecretKey(password);
		// Create a User object to store user information
		User user = new User();
		user.setEmail(email);
		user.setPassword(hashedPassword);
		// Initialize database connection
		DBmanager dbMgr = new DBmanager();
		Connection conn = null;
		PreparedStatement prepare_statement = null;
		ResultSet resultSet = null;

		try {
			conn = dbMgr.getConnection();
			String query = "SELECT * FROM TrainingDB.users WHERE email=? AND password=?;";
			prepare_statement = conn.prepareStatement(query);
			prepare_statement.setString(1, user.getEmail());
			prepare_statement.setString(2, user.getPassword());
			resultSet = prepare_statement.executeQuery();
			// session to save the user
			HttpSession session = request.getSession();
			if (resultSet.next()) {
				String username = resultSet.getString("name");
				Boolean is_admin = resultSet.getBoolean("is_admin");
				user.setUsername(username);
				user.setAdmin(is_admin);
				user.setLoggedIn(true);
				session.setAttribute("user", user);
				if (user.isAdmin()) {
					response.sendRedirect("AdminDashboard?msg=Welcome back Admin user.");
				} else {
					response.sendRedirect("CourseController?msg=Welcome to Training Department");
				}

			} else {
				response.sendRedirect("./index.jsp?msg=Sorry, you are not a registered user! Please sign up first");
			}
		} catch (

		ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (prepare_statement != null)
					prepare_statement.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
