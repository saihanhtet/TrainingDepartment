<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="custom">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Training Department | Home Page</title>
<link rel="stylesheet" href="./static/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet" href="./static/style.css">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container-fluid d-flex flex-column align-items-center">
		<div class="row d-flex align-items-center">
			<div class="col-12 col-md-6 text-start text-md-end">
				<p class="lh-lg">
					Welcome to the Training Department,<br />your premier destination
					for professional development and training.<br>We are dedicated
					to empowering individuals and organizations<br>though
					high-quality training programs and educational resources.
				</p>
			</div>
			<div class="col-12 col-md-6">
				<img src="./static/training.avif" alt="training image"
					class="card-img-top intro-img">
			</div>
		</div>
		<div
			class="container d-flex p-3 justify-content-evenly align-items-center">



		</div>
		<a href="#" class="btn btn-danger text-white m-3 mb-3"
			id="registerLink">Register Now!</a>
	</div>
	<jsp:include page="footer.jsp" />
	<jsp:include page="modal.jsp" />

	<script src="./static/bootstrap.bundle.min.js"></script>
	<script src="./static/jquery.min.js"></script>
	<script src="./static/script.js"></script>
</body>
</html>


