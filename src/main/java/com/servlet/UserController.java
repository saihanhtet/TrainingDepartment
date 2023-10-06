package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.DBFunctions;
import com.entity.User;

@WebServlet("/UserController/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			DBFunctions dbFunctions = new DBFunctions();
			User userSession = (User) session.getAttribute("user");
			String pathInfo = request.getPathInfo();
			if (userSession != null && userSession.isLoggedIn()) {
				// if delete is in url then delete the user
				if (pathInfo != null && pathInfo.startsWith("/delete/")) {
					String[] pathParts = pathInfo.split("/");
					int userId = Integer.parseInt(pathParts[2]);
					boolean updateResult = dbFunctions.deleteUser(userId);
					if (updateResult) {
						response.sendRedirect(
								"AdminDashboard?msg=Successfully deleted the User Id: " + Integer.toString(userId) + "!");
					} else {
						response.sendRedirect("AdminDashboard?msg=Error at deleting the user!");
					}
				} else {
					String[] pathParts = pathInfo.split("/");
					int userId = Integer.parseInt(pathParts[2]);
					User user = dbFunctions.getUser(userId);
					if (!user.getUsername().equals("")) {
						request.setAttribute("UserDetails", user);
						RequestDispatcher rd = request.getRequestDispatcher("/userUpdate.jsp");
						rd.forward(request, response);
					}
				}
			} else {
				response.sendRedirect("index.jsp?msg=Please login first!");
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBFunctions dbFunctions = new DBFunctions();
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String is_admin = request.getParameter("status");
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		if (Integer.parseInt(is_admin) == 1) {
			user.setAdmin(true);
		} else {
			user.setAdmin(false);
		}
		boolean result = dbFunctions.updateUser(user);
		if (result) {
			response.sendRedirect("AdminDashboard?msg=Successfully updated the User Id:" + user.getId() + "!");
		} else {
			response.sendRedirect("AdminDashboard?msg=Failed to update the user.");
		}

	}

}
