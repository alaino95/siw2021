$(document).ready(function() {
	$("#publisher").on('change', function() {
		var usernameForm = $('<div class="registra"><label>Username</label><br><input type="text" name="username" class="form-control" placeholder="Enter username" required></div>');
		$(".registra").remove();
		$("#regi").prepend(usernameForm);
	});
	$("#user").on('change', function() {
		var userForm = $('<div class="registra"><label>Email address</label><br><input type="text" name="email" class="form-control" placeholder="Enter email" required></div>');
		$(".registra").remove();
		$("#regi").prepend(userForm);
	});
});
