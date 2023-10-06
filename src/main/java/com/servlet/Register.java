package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.DBmanager;
import com.database.HashKeyUtil;
import com.entity.*;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user = new User();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String username = request.getParameter("username");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String password_con = request.getParameter("password_con");
	    String secret_key = request.getParameter("secret_key");

	    // Check if passwords match
	    if (!password.equals(password_con)) {
	        try (PrintWriter out = response.getWriter()) {
	            response.sendRedirect("index.jsp?msg=Passwords do not match. Please try again.");
	        }
	        return;
	    }

	    String hashedPassword = HashKeyUtil.hashSecretKey(password);
	    System.out.println(hashedPassword);
	    user.setUsername(username);
	    user.setPassword(hashedPassword);
	    user.setEmail(email);

	    DBmanager dbMgr = new DBmanager();
	    Connection conn;

	    try {
	        conn = dbMgr.getConnection();

	        // Check if the secret_key exists in the database
	        String adminCheckQuery = "SELECT 1 FROM `TrainingDB`.`secret_keys` WHERE key_name=?";
	        PreparedStatement prepareAdminCheck = conn.prepareStatement(adminCheckQuery);
	        prepareAdminCheck.setString(1, secret_key);
	        ResultSet adminCheckResult = prepareAdminCheck.executeQuery();

	        if (adminCheckResult.next()) {
	            user.setAdmin(true);
	        } else {
	            user.setAdmin(false);
	        }
	        System.out.println(user.isAdmin());
	        // Insert the user into the database
	        String insertUserQuery = "INSERT INTO `TrainingDB`.`users` (name, email, password, is_admin) VALUES (?,?,?,?)";
	        PreparedStatement prepareStatement = conn.prepareStatement(insertUserQuery);
	        prepareStatement.setString(1, user.getUsername());
	        prepareStatement.setString(2, user.getEmail());
	        prepareStatement.setString(3, user.getPassword());
	        prepareStatement.setBoolean(4, user.isAdmin());

	        int result = prepareStatement.executeUpdate();

	        if (result == 1) {
	            HttpSession session = request.getSession();
	            user.setLoggedIn(true);
	            session.setAttribute("user", user);
	            request.setAttribute("Message", "Successfully Added to the Database!");
	            response.sendRedirect("CourseController?msg=Welcome to Training Department");
	        }

	        prepareStatement.close();
	        conn.close();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Something went wrong with creating user!");
	        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
	        rd.forward(request, response);
	    }
	}


}
