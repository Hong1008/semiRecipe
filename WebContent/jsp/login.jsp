<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#login').on('click', login);

		$("input").keypress(function(e) {
			if (e.which == 13) { // 엔터키를 누르면
				login();
			}
		});
		
		$('.logo img').on('click', function(){
			location.href = '/semiRecipe/recipe/home'; 
		});
	});

	function login() {
		if ($('#user_id').val() != "" && $('#user_pw').val() != "") {
			$.ajax({
				type : 'POST',
				dataType : 'text',
				url : '/semiRecipe/recipe/login',
				data : 'user_id=' + $('#user_id').val() + '&user_pw='
						+ $('#user_pw').val(),
				success : function(res) {
					if (res != 1) {
						alert("회원정보를 다시 확인해주세요.");
					} else {
						location.href = '/semiRecipe/recipe/home';
					}
				}
			});
			return;
		} else {
			alert("아이디와 비밀번호를 모두 입력해주세요.");
		}
	}
</script>
<style type="text/css">
body {
	line-height: 1;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
}

html, body {
	height: 100%;
	margin: 0px;
	padding: 0px;
}

.wrapper {
	width: 100%;
	height: 100%;
	background-image: url(/semiRecipe/img/food_test.jpg);
	background-repeat: no-repeat;
	background-position: center;
	font-family: '돋움';
}

.main {
	padding-top: 120px;
}

.logo {
	width: 250px;
	margin: 0 auto 50px;
	cursor: pointer;
}

.logo img {
	width: 250px;
	height: 75px;
}

.login_box {
	width: 337px;
	height: 400px;
	padding: 45px 44px 38px 44px;
	margin: 0 auto;
	background-color: #fff;
	opacity: 0.8;
	border-radius: 5px;
	border: 1px solid #ebebeb;
}

.login_box .input_login {
	border-bottom: 2px solid #c4c5c4;
	padding: 20px 0 9px 0;
}

.login_box .input_login input {
	width: 100%;
	color: #828180;
	font-size: 15px;
	border: none;
	font-family: inherit;
}

.login_box .check {
	color: #000;
	font-size: 14px;
	padding: 19px 0 15px 0;
	font-family: '돋움';
}

.login_box #login {
	width: 100%;
	height: 55px;
	background-color: #00a6de;
	color: #fff;
	font-size: 16px;
	padding: 17px 0 16px;
	border: 0;
	cursor: pointer;
	font-family: '돋움';
}

.login_box #kakao_login {
	width: 100%;
	height: 55px;
	background-color: #b2ae40;
	color: #fff;
	font-size: 16px;
	padding: 17px 0 16px;
	border: 0;
	margin-top: 10px;
	cursor: pointer;
	font-family: '돋움';
}

.login_box .last {
	width: 100%;
	margin-top: 10px;
	border-top: 1px solid #ebebeb;
	padding-top: 20px;
}

.login_box .last #join {
	float: left;
	color: #00a6de;
}

.login_box .last #search {
	float: right;
	color: #626262;
}
</style>

</head>

<body>
	<div class="wrapper">
		<div class="main">
			<div class="logo">
				<img src="/semiRecipe/img/wellcome_logo2.png">
			</div>
			<div class="login_box">
				<div class="input_login">
					<input type="text" id="user_id" name="user_id"
						placeholder="아이디를 입력하세요">
				</div>
				<div class="input_login">
					<input type="password" id="user_pw" name="user_pw"
						placeholder="비밀번호를 입력하세요">
				</div>
				<div class="check">
					<label><input type="checkbox">아이디 저장</label>
				</div>
				<div>
					<input id="login" type="submit" value="로그인">
				</div>
				<div>
					<input id="kakao_login" type="submit" value="카카오톡 로그인">
				</div>


				<div class="last">
					<div>
						<a href="/semiRecipe/recipe/registerForm" id="join">회원가입</a>
					</div>
					<div class="search">
						<a href="" id="search">비밀번호 찾기</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type='text/javascript'>
		//<![CDATA[
		// 사용할 앱의 JavaScript 키를 설정해 주세요.
		Kakao.init('c8ee897a0db2b7e1891baf231ece5129');

		// 카카오 로그인 버튼을 생성합니다.
		Kakao.Auth.createLoginButton({
			container : '#kakao_login',
			success : function(authObj) {

				// 로그인 성공시, API를 호출합니다.
				Kakao.API.request({
					url : '/v1/user/me',
					success : function(res) {
						console.log(res);
						var userId = res.kaccount_email; //유저가 등록한 계정
						var userNickName = res.properties.nickname; //유저가 등록한 별명
					},
					fail : function(error) {
						alert(JSON.stringify(error));
					}
				});
			},
			fail : function(err) {
				alert(JSON.stringify(err));
			}
		});
		//]]>
	</script>
</body>
</html>