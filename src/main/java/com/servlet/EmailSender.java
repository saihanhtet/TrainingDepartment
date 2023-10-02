package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Email;

/**
 * Servlet implementation class EmailSender
 */
@WebServlet("/EmailSender")
public class EmailSender extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmailSender() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");

		try {
			new Email().sendEmail(email, subject, message);
			response.sendRedirect("./admin_dashboard.jsp?msg=Sent the email to the " + email + " successfully..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
