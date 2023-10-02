<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="custom">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Training Department | Admin Dashboard</title>
<link rel="stylesheet" href="./static/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet" href="./static/style.css">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container mt-3">
		<div class="row">
			<div class="col-12 col-md-4 col-lg-4 col-xl-4">
				<div class="card h-100 shadow-sm border-0 p-3">
					<div class="card-title text-center">Course Management</div>
					<div class="card-body d-flex flex-column gap-2">
						<button class="btn btn-outline-primary  w-100 text-capitalize"
							data-bs-toggle="modal" data-bs-target="#CourseRegisterModel">Course
							Add (manual)</button>
						<button class="btn btn-outline-success  w-100 text-capitalize"
							data-bs-toggle="modal" data-bs-target="#CourseRegisterExcelModel">Course
							Add (excel)</button>
						<button class="btn btn-outline-secondary  w-100 text-capitalize"
							data-bs-toggle="modal" data-bs-target="#CourseViewModel">Course
							View</button>
						<button class="btn btn-outline-secondary  w-100 text-capitalize"
							data-bs-toggle="modal" data-bs-target="#CourseUpdateModel">Course
							Update</button>
					</div>
				</div>
			</div>

			<div class="col-12 col-md-4 col-lg-4 col-xl-4">
				<div class="card h-100 shadow-sm border-0 p-3">
					<div class="card-title text-center">User Management</div>
					<div class="card-body d-flex flex-column gap-2">
						<button class="btn btn-outline-primary  w-100 text-capitalize"
							data-bs-toggle="modal" data-bs-target="#UserViewModel">Registered
							Users</button>
						<button class="btn btn-outline-success  w-100 text-capitalize"
							data-bs-toggle="modal" data-bs-target="#UserCourseModel">Course
							Register Records</button>
						<button class="btn btn-outline-primary  w-100 text-capitalize"
							data-bs-toggle="modal" data-bs-target="#EmailSendModel">Send
							Mail</button>
					</div>
				</div>
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