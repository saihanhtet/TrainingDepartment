<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="custom">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Training Development | Forgot Password Page</title>
<link rel="stylesheet" href="./static/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet" href="./static/style.css">
</head>
<body>
	<jsp:include page="navbar.jsp" />

	<div class="container mt-3">
		<form class="card w-100" action="Login" method="get">
			<div class="card-title">
				<a href="./index.jsp" class="btn"><i
					class="bi bi-caret-left-fill"></i></a> <strong>Forgot Password</strong>
			</div>
			<div class="card-body">
				<label for="email">Enter the Email:</label> <input type="email"
					class="form-control" value="" name="email">
			</div>
			<div class="card-footer">
				<button type="submit" class="btn btn-info w-100">Get Password</button>
			</div>
		</form>
	</div>

	<jsp:include page="footer.jsp" />
	<jsp:include page="modal.jsp" />
	<script src="./static/bootstrap.bundle.min.js"></script>
	<script src="./static/jquery.min.js"></script>
	<script src="./static/script.js"></script>
</body>
</html>