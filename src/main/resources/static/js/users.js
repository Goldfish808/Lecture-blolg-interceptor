let isUsernameSameCheck = false;

$("#btnJoin").click(() => {
	join();
});

$("#btnUsernameSameCheck").click(() => {
	checkUsername();
});

$("#btnLogin").click(() => {
	login();
});


$("#btnDelete").click(() => {
	resign();
});

$("#btnUpdate").click(() => {
	update();
});


function testCheck() {
	koreanCheck();
	capitalCheck();
	emailCheck();
	pwCheck();
}

function koreanCheck() {
	let input = $("#username").val();
	let korRule = /^[a-zA-Z]*$/;
	if (korRule.test(input)) { //영어만 허용
		alert("English OK" + input);
		return true;
	} else {
		alert("한글 또는 공백이 입력됨" + input);
		return false;
	}
}

function capitalCheck() {
	let input = $("#username").val();
	let cap = /[A-Z]/;
	if (cap.test(input)) {
		alert("OK" + input);
		return true;
	} else {
		alert("대문자 입력이 필요함" + input);
		return false;
	}
}

function emailCheck() {
	let input = $("#email").val();
	let mail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	if (mail.test(input)) {
		alert("OK" + input);
		return true;
	} else {
		alert("이메일 양식에 맞추어 입력 해주세요 ex) abcdef@hijk.com" + input);
		return false;
	}
}

function pwCheck() {
	let pw = $("#password").val();
	let pw2 = $("#passwordSame").val();
	if (pw == pw2) {
		alert("패스워드가 일치");
		return true;
	} else {
		alert("입력한 패스워드가 일치하지 않습니다");
		return false;
	}
}

function join() {
	if (isUsernameSameCheck == false) {
		alert("유저네임 중복 체크를 진행해주세요");
		return;
	}
	if (koreanCheck() == false){
		return;
	}
	if(capitalCheck() == false){
		return;
	}
	if(emailCheck() == false){
		return;
	}
	if(pwCheck() == false){
		return;
	}
	

	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		email: $("#email").val()
	};
	

	$.ajax("/api/join", {
		type: "POST",
		dataType: "json", // 응답 데이터
		data: JSON.stringify(data), // http body에 들고갈 요청 데이터
		headers: { // http header에 들고갈 요청 데이터
			"Content-Type": "application/json"
		}
	}).done((res) => {
		if (res.code == 1) {
			location.href = "/loginForm";
		}else{
			alert(res.msg);
			history.back;
		}
	});
}

function checkUsername() {
	let username = $("#username").val();

	$.ajax(`/users/usernameSameCheck?username=${username}`, {
		type: "GET",
		dataType: "json",
		async: true
	}).done((res) => {
		if (res.code == 1) { // 통신 성공
			if (res.data == false) {
				alert("아이디가 중복되지 않았습니다.");
				isUsernameSameCheck = true;
			} else {
				alert("아이디가 중복되었어요. 다른 아이디를 사용해주세요.");
				isUsernameSameCheck = false;
				$("#username").val("");
			}
		}
	});
}

function login() {
	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		remember: $("#remember").prop("checked")
	};

	$.ajax("/login", {
		type: "POST",
		dataType: "json", // 응답 데이터
		data: JSON.stringify(data), // http body에 들고갈 요청 데이터
		headers: { // http header에 들고갈 요청 데이터
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
			location.href = "/";
		} else {
			alert("로그인 실패, 아이디 패스워드를 확인해주세요");
		}
	});
}

function resign() {
	let id = $("#id").val();

	$.ajax("/s/api/users/" + id, {
		type: "DELETE",
		dataType: "json" // 응답 데이터
	}).done((res) => {
		if (res.code == 1) {
			alert("회원탈퇴 완료");
			location.href = "/";
		} else {
			alert("회원탈퇴 실패");
		}
	});
}

function update() {
	let data = {
		password: $("#password").val(),
		email: $("#email").val()
	};

	let id = $("#id").val();

	$.ajax("/s/api/users/" + id, {
		type: "PUT",
		dataType: "json", // 응답 데이터
		data: JSON.stringify(data), // http body에 들고갈 요청 데이터
		headers: { // http header에 들고갈 요청 데이터
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
			alert("회원 수정 완료");
			location.reload(); // f5
		} else {
			alert("업데이트에 실패하였습니다");
		}
	});
}
