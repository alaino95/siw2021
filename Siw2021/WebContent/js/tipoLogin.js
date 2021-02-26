
$(document).ready(function() {
	$("#publisher").on('change', function() {
		var usernameForm = $('<div id="form-group1"><label>Username</label><br><input type="text" name="username" class="form-control" placeholder="Enter username" required></div>');
		$("#form-group1").remove();
		$(".login").prepend(usernameForm);
	});
	$("#user").on('change', function() {
		var userForm = $('<div id="form-group1"><label>Email address</label><br><input type="text" name="email" class="form-control" placeholder="Enter email" required></div>');
		$("#form-group1").remove();
		$(".login").prepend(userForm);
	});
});

