$(document).ready(function() {
	//Hien thi thong tin nguoi dung dang nhap thanh cong
	$.ajax({
		type: 'GET',
		url: '/users/me',
		dataType: 'json',
		contentType: "application/json",
		beforeSend: function(xhr) {
			if (localStorage.token) {
				xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
			}
		},
		success: function(data) {
			var json = JSON.stringify(data, null, 4);
			// $('#profile').html(json);
			$('#profile').html(data.fullName);
			$('#images').html(document.getElementById("images").src = data.images);
			//$('#images').attr('src', '/images/' + data.images);
			console.log("SUCCESS : ", data);
		},
		error: function(xhr) {
			var json = xhr.responseText;
			$("#feedback").html(json);
			alert("Sorry, you are not logged in");
		}

	});
	
	//Ham dang xuat
	$('#logout').click(function(){
		localStorage.clear();
		window.location.href = "/login";
	})
	
	//Ham login
	$('#login').click(function(){
		var email = document.getElementById('email').value;
		var password = document.getElementById('password').value;
		var basicInfo = JSON.stringify({
			email: email,
			password: password,
		});
		$.ajax({
			type: 'POST',
			url: '/auth/login',
			dataType: 'json',
			contentType: "application/json",
			data: basicInfo,
			success: function(data){
				localStorage.token = data.token;
				window.location.href = "/user/profile";
			},
			error: function(){
				alert("Login Failed");
			}
		})
	})
	
	
	
}
)