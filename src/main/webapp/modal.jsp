<%@ page import="java.util.List"%>
<!-- Register -->

<div class="modal fade" id="RegisterModel" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="RegisterModelLabel" aria-hidden="true">
	<div class="modal-dialog">
		<form method="post" action="Register">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h1 class="modal-title fs-5 text-center w-100"
						id="RegisterModelLabel">Register</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<label for="username">Enter Your Name:</label> <input type="text"
						class="form-control" name="username" /> <label for="email">Enter
						Your Email:</label> <input type="email" class="form-control" name="email" />
					<label for="password">Enter Your Password:</label> <input
						type="password" class="form-control" name="password" /> <label
						for="password_con">Enter Your Password Again:</label> <input
						type="password" class="form-control" name="password_con" /> <label
						for="secret_key" hidden="hidden">Enter the SHA Key:</label> <input
						type="hidden" class="form-control" name="secret_key" value=""
						placeholder="Memeber key (not need for users)" />
				</div>
				<div
					class="modal-footer d-flex flex-column justify-content-center align-items-center gap-2">
					<a href="#" class="text-center" id="loginLink">Already User?</a>
					<button type="submit" class="btn btn-primary w-100">Register</button>
				</div>
			</div>
		</form>
	</div>
</div>
<!-- login -->
<div class="modal fade" id="LoginModel" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="LoginModelLabel" aria-hidden="true">
	<div class="modal-dialog">
		<form method="post" action="Login">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h1 class="modal-title fs-5 text-center w-100" id="LoginModelLabel">Register</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<label for="email">Enter Your Email:</label> <input type="email"
						class="form-control" name="email"
						placeholder="admin@training.com (admin access level)" /> <label
						for="password">Enter Your Password:</label> <input type="password"
						class="form-control" name="password"
						placeholder="admin@2023 (admin access level)" />
				</div>
				<div
					class="modal-footer d-flex flex-column justify-content-center align-items-center gap-2">
					<p class="text-center">
						<a href="./forgotPassword.jsp">Forgot Password?</a>| <a href="#"
							id="registerLink">Not user?</a>
					</p>
					<button type="submit" class="btn btn-primary w-100">Login</button>
				</div>
			</div>
		</form>
	</div>
</div>
<!-- course manual register -->
<div class="modal fade" id="CourseRegisterModel"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="CourseRegisterModelLabel" aria-hidden="true">
	<div class="modal-dialog">
		<form method="post" action="CourseController">
			<input type="hidden" value="course_manual_form" name="form">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h1 class="modal-title fs-5 text-center w-100"
						id="CourseRegisterModelLabel">Course Register</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<label for="course_name">Enter Course Name:</label> <input
						type="text" class="form-control" name="course_name" /> <label
						for="course_price">Enter Course Price:</label> <input
						type="number" class="form-control" name="course_price" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Cancel</button>
					<button type="submit" class="btn btn-primary">ADD</button>
				</div>
			</div>
		</form>
	</div>
</div>

<!-- course excel register -->
<div class="modal fade" id="CourseRegisterExcelModel"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="CourseRegisterExcelModelLabel" aria-hidden="true">
	<div class="modal-dialog">
		<form method="post" action="CourseController"
			enctype="multipart/form-data">
			<input type="hidden" value="course_excel_form" name="form">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h1 class="modal-title fs-5 text-center w-100"
						id="CourseRegisterExcelModelLabel">Course Register</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<label for="course_name">Select the excel file:</label> <input
						type="file" class="form-control" name="course_file"
						accept=".xlsx, .xls" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Cancel</button>
					<button type="submit" class="btn btn-primary">ADD</button>
				</div>
			</div>
		</form>
	</div>
</div>
<!-- course update form -->
<div class="modal fade" id="CourseUpdateModel" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="CourseUpdateModelLabel" aria-hidden="true">
	<div class="modal-dialog">
		<form method="post" action="AdminDashboard">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h1 class="modal-title fs-5 text-center w-100"
						id="CourseUpdateModelLabel">Course Update</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<select name="course_id" class="form-select">
						<%
						@SuppressWarnings("unchecked") // this is to supress the warning bcz i don't like to see the warning in my project ;) 
						List<com.entity.Course> courses_update = (List<com.entity.Course>) request.getAttribute("List");
						if (courses_update != null && !courses_update.isEmpty()) {
							for (com.entity.Course course_upg : courses_update) {
						%>

						<option value="<%=course_upg.getId()%>"><%=course_upg.getName()%></option>

						<%
						}
						} else {
						%>
						<option value="0">No course</option>
						<%
						}
						%>
					</select> <label for="course_name">Enter the new Course Name:</label> <input
						type="text" class="form-control" name="course_name"> <label
						for="course_price">Enter the new Course Price:</label> <input
						type="number" class="form-control" name="course_price">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Cancel</button>
					<button type="submit" class="btn btn-primary">Update</button>
				</div>
			</div>
		</form>
	</div>
</div>
<!-- course view  -->
<div class="modal fade" id="CourseViewModel" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="CourseViewModelLabel" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<div class="modal-header text-center">
				<h1 class="modal-title fs-5 text-center w-100"
					id="CourseViewModelLabel">Course View</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<table class="table table-responsive table-striped ">
					<thead>
						<tr>
							<th>Id</th>
							<th>Course Name</th>
							<th>Price</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
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
							<td><a href="CourseController/delete/<%=course.getId()%>"
								class="ms-2"><i class="bi bi-trash3"></i></a></td>
						</tr>
						<%
						}
						} else {
						%>
						<tr>
							<td colspan="5" class="text-center">No Courses Available</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- user view -->
<div class="modal fade" id="UserViewModel" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="UserViewModelLabel" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<div class="modal-header text-center">
				<h1 class="modal-title fs-5 text-center w-100"
					id="UserViewModelLabel">Register User View</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<table class="table table-responsive table-striped ">
					<thead>
						<tr>
							<th>Id</th>
							<th>Username</th>
							<th class="d-none d-md-block d-lg-block d-xl-block">Email</th>
							<th>IsAdmin</th>
							<th>Admin</th>
						</tr>
					</thead>
					<tbody>
						<%
						@SuppressWarnings("unchecked") // this is to supress the warning bcz i don't like to see the warning in my project ;) 
						List<com.entity.User> users = (List<com.entity.User>) request.getAttribute("UserList");
						if (users != null && !users.isEmpty()) {
							for (com.entity.User user : users) {
						%>
						<tr>
							<td><%=user.getId()%></td>
							<td><%=user.getUsername()%></td>
							<td class="d-none d-md-block d-lg-block d-xl-block"><%=user.getEmail()%></td>
							<td><%=user.isAdmin()%></td>
							<td><a href="UserController/update/<%=user.getId()%>"
								class="ms-2"><i class="bi bi-pen-fill"></i></a><a
								href="UserController/delete/<%=user.getId()%>" class="ms-2"><i
									class="bi bi-trash3"></i></a></td>
						</tr>
						<%
						}
						} else {
						%>
						<tr>
							<td colspan="5" class="text-center">No Users Available</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- records -->
<div class="modal fade" id="UserCourseModel" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="UserCourseModelLabel" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<div class="modal-header text-center">
				<h1 class="modal-title fs-5 text-center w-100"
					id="UserCourseModelLabel">Course Register Record View</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<table class="table table-responsive table-striped ">
					<thead>
						<tr>
							<th>Username</th>
							<th class="d-none d-md-block d-lg-block d-xl-block">Email</th>
							<th>Course Name</th>
							<th>Price</th>
						</tr>
					</thead>
					<tbody>
						<%
						@SuppressWarnings("unchecked") // this is to supress the warning bcz i don't like to see the warning in my project ;) 
						List<com.entity.CourseRegister> users_reg = (List<com.entity.CourseRegister>) request.getAttribute("UserRegisterList");
						if (users_reg != null && !users_reg.isEmpty()) {
							for (com.entity.CourseRegister user_reg : users_reg) {
						%>
						<tr>
							<td><%=user_reg.getUsername()%></td>
							<td class="d-none d-md-block d-lg-block d-xl-block"><%=user_reg.getEmail()%></td>
							<td><%=user_reg.getCourse_name()%></td>
							<td><%=user_reg.getCourse_price()%></td>
						</tr>
						<%
						}
						} else {
						%>
						<tr>
							<td colspan="5" class="text-center">No Records Available</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- email sender -->
<div class="modal fade" id="EmailSendModel" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="EmailSendModelLabel" aria-hidden="true">
	<div class="modal-dialog">
		<form method="post" action="EmailSender">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h1 class="modal-title fs-5 text-center w-100"
						id="EmailSendModelLabel">Email Sender</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<select name="email" class="form-select">
						<%
						@SuppressWarnings("unchecked") // this is to supress the warning bcz i don't like to see the warning in my project ;) 
						List<com.entity.User> users_view = (List<com.entity.User>) request.getAttribute("UserList");
						if (users_view != null && !users_view.isEmpty()) {
							for (com.entity.User user_view : users_view) {
						%>

						<option value="<%=user_view.getEmail()%>"><%=user_view.getEmail()%></option>

						<%
						}
						} else {
						%>
						<option value="0">No User to send mail</option>
						<%
						}
						%>
					</select> <label for="subject">Enter the Subject:</label> <input type="text"
						class="form-control" name="subject"> <label for="message">Enter
						the Message:</label>
					<textarea class="form-control" name="message" rows="4" cols="50"></textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Cancel</button>
					<button type="submit" class="btn btn-primary">Send</button>
				</div>
			</div>
		</form>
	</div>
</div>
