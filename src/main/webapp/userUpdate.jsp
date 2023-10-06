<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="custom">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Training Department | Admin Dashboard</title>
<link rel="stylesheet" href="../../static/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet" href="../../static/style.css">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container mt-3">
		<form class="card w-100" action="../../UserController" method="post">
			<div class="card-title">
				<a href="../../AdminDashboard" class="btn"><i
					class="bi bi-caret-left-fill"></i></a> <strong>User Update</strong>
			</div>
			<div class="card-body">
				<%
				com.entity.User user = (com.entity.User) request.getAttribute("UserDetails");
				%>
				<input type="hidden" value="<%=user.getId()%>" name="id"> <label
					for="username">Enter the Username:</label> <input type="text"
					class="form-control" value="<%=user.getUsername()%>"
					name="username"> <label for="email">Enter the
					Email:</label> <input type="email" class="form-control"
					value="<%=user.getEmail()%>" name="email"> <label
					for="password">Enter the Password:</label> <input type="text"
					class="form-control" value="<%=user.getPassword()%>"
					name="password" readonly> <label for="status">Enter
					the User Status:</label> <select class="form-select" name="status">
					<option value="0" <%=(!user.isAdmin()) ? "selected" : ""%>>User</option>
					<option value="1" <%=(user.isAdmin()) ? "selected" : ""%>>Admin</option>
				</select>
			</div>
			<div class="card-footer">
				<button type="submit" class="btn btn-info w-100">Update</button>
			</div>
		</form>
	</div>
	<jsp:include page="footer.jsp" />

	<script src="../../static/bootstrap.bundle.min.js"></script>
	<script src="../../static/jquery.min.js"></script>
	<script src="../../static/script.js"></script>
</body>
</html>