<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="custom">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Training Development | Thanks Page</title>
<link rel="stylesheet" href="./static/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet" href="./static/style.css">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div
		class="container my-3 d-flex flex-column justify-content-center align-items-center" style="height:80vh;">
		<p class="text-center">
			<span class="text-capitalize">Your registration is completed</span> <br>Thanks
			you for registering with <span class="text-capitalize">training
				department.</span><br> We appreciate your interest in our training
			programs, and we look forward to helping you<br> achieve goals
		</p>
		<table class="table table-responsive table-striped">
			<thead>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Course ID</th>
					<th>Course Name</th>
					<th>Cost</th>
				</tr>
			</thead>
			<tbody style="overflow-y: scroll;">
				<%
				@SuppressWarnings("unchecked") // this is to supress the warning bcz i don't like to see the warning in my project ;) 
				List<com.entity.CourseRegister> courses_reg = (List<com.entity.CourseRegister>) request.getAttribute("UserRegisterCourseList");
				if (courses_reg != null && !courses_reg.isEmpty()) {
					for (com.entity.CourseRegister course_reg : courses_reg) {
				%>
				<tr>
					<td><%=course_reg.getUsername()%></td>
					<td><%=course_reg.getEmail()%></td>
					<td><%=course_reg.getCourse_id()%></td>
					<td><%=course_reg.getCourse_name()%></td>
					<td><%=course_reg.getCourse_price()%></td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="5" class="text-center">No Course Register Available</td>
				</tr>
				<%
				}
				%>
			</tbody>

		</table>
		<div class="d-flex gap-3">
			<a href="./index.jsp" class="btn btn-info">Return</a> <a href="http://gmail.com/"
				class="btn btn-info">Check Email</a>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	<jsp:include page="modal.jsp" />
	<script src="./static/bootstrap.bundle.min.js"></script>
	<script src="./static/jquery.min.js"></script>
	<script src="./static/script.js"></script>
</body>
</html>