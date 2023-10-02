document.querySelectorAll('#registerLink').forEach((element) => {
	element.addEventListener('click', (e) => {
		e.preventDefault();
		$('#LoginModel').modal('hide');
		$('#RegisterModel').modal('show');
		window.history.pushState({}, '', '#register');
	});
});
document.querySelectorAll('#loginLink').forEach((element) => {
	element.addEventListener('click', (e) => {
		e.preventDefault();
		$('#RegisterModel').modal('hide');
		$('#LoginModel').modal('show');
		window.history.pushState({}, '', '#login');
	});
});

document.querySelector('.modal .btn-close').addEventListener('click',
	() => {
		window.history.pushState({}, '', window.location.pathname);
	});

document.addEventListener("DOMContentLoaded", () => {
	const toastElement = document.querySelector('.toast');
	if (toastElement) {
		const toast = new bootstrap.Toast(toastElement);
		toast.show();
	}
});

$(document).ready(function() {
	$('#CourseDeleteModel').on('show.bs.modal', function() {
		$.ajax({
			url: '',
			method: 'GET',
			dataType: 'json',
			success: function(data) {
				var select = $('#courseChoiceSelect');
				select.empty();
				select.append($('<option>', {
					value: '0',
					text: 'Select a course'
				}));

				$.each(data, function(index, course) {
					select.append($('<option>', {
						value: course.id,
						text: course.name
					}));
				});
			},
			error: function(error) {
				console.error('Error fetching course data:', error);
			}
		});
	});
});
