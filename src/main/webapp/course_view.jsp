<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="custom">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Training Development | Course Register Page</title>
<link rel="stylesheet" href="./static/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet" href="./static/style.css">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container my-3 h-100">
		<div class="row">
			<div class="col-12 col-md-6 col-lg-6">
				<div class="card shadow bg-light rounded border-0 p-2">
					<div class="bg-light text-center text-capitalize p-2"
						style="font-size: 20px;">
						<strong>Course list</strong>
					</div>
					<table class="table table-responsive table-striped course_view">
						<thead>
							<tr>
								<th>Id</th>
								<th>Name</th>
								<th>Price</th>
							</tr>
						</thead>
						<tbody style="overflow-y: scroll;">
							<%
							@SuppressWarnings("unchecked") // this is to supress the warning bcz i don't like to see the warning in my project ;) 
							List<com.entity.Course> courses = (List<com.entity.Course>) request.getAttribute("List");
							if (courses != null && !courses.isEmpty()) {
								for (com.entity.Course course : courses) {
							%>
							<tr>
								<td><%=course.getId()%></td>
								<td><%=course.getName()%></td>
								<td><%=course.getPrice()%></td>
							</tr>
							<%
							}
							} else {
							%>
							<tr>
								<td colspan="3" class="text-center">No Courses Available</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-12 col-md-6 col-lg-6">
				<form method="post" action="CourseUserRegistration"
					class="card p-3 shadow border-0">
					<div class="card-title w-100 text-center">Course Register</div>
					<div class="card-body">
						<label for="email">Check your Email:</label> <input
							class="form-control" type="email" value="${user_email}"
							name="email"> <label for="username">Enter Your
							Name:</label> <input class="form-control" type="text"
							value="${user_name}" name="username"><label
							for="course_selection">Select the Course:</label> <select
							name="course_selection" class="form-select">

							<%
							if (courses != null && !courses.isEmpty()) {
								for (com.entity.Course course : courses) {
							%>
							<option value="<%=course.getId()%>"><%=course.getName()%></option>
							<%
							}
							} else {
							%>
							<option value="0">No course</option>
							<%
							}
							%>
						</select>
					</div>
					<div class="card-footer border-0 mt-3">
						<button type="submit" class="btn btn-primary w-100">Course
							Register</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	<jsp:include page="modal.jsp" />
	<script src="./static/bootstrap.bundle.min.js"></script>
	<script src="./static/jquery.min.js"></script>
	<script src="./static/script.js"></script>
</body>
</html>