
<%
String errorMsg = request.getParameter("msg");
if (errorMsg != null && !errorMsg.isEmpty()) {
%>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
	<div id="liveToast" class="toast" role="alert" aria-live="assertive"
		aria-atomic="true">
		<div class="toast-header bg-light">
			<strong class="me-auto">Training Department</strong> <small>Now</small>
			<button type="button" class="btn-close" data-bs-dismiss="toast"
				aria-label="Close"></button>
		</div>
		<div class="toast-body bg-light">
			<%=errorMsg%>
		</div>
	</div>
</div>
<%
}
%>
<nav class="navbar navbar-expand-lg bg-info">
	<div
		class="container-fluid d-flex justify-content-between align-items-center">
		<a class="navbar-brand" href="./index.jsp">Training Department</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse justify-content-end"
			id="navbarNav">
			<ul class="navbar-nav mx-0 p-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="./index.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link"
					href="CourseController">Course Register</a></li>
				<%
				// Check if user session exists and user is admin
				Object userSession = session.getAttribute("user");
				if (userSession != null && ((com.entity.User) userSession).isAdmin()) {
				%>
				<li class="nav-item"><a class="nav-link"
					href="AdminDashboard">Admin Dasboard</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>
