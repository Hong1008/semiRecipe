<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/semiRecipe/js/user.js"></script>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

.wrap input {
	width: 460px;
	height: 30px;
}

.wrap {
	display: block;
	margin: 0 auto;
	width: 500px;
}

#yy, #mm, #dd {
	width: 147px;
	height: 30px;
	float: left;
	margin-right: 10px;
}

input.result {
	width: 220px;
	margin-top: 25px;
	margin-right: 18px;
	display: inline-block;
}

.error_next_box {
    display: inline-block;
    font-size: 12px;
    line-height: 14px;
    color: red;
}

</style>
</head>
<body id="register">
<div class="wrap">
	<form id="registerForm" method="post">
		<input type="text" id="kakao_id" name="kakao_id" value="${param.kakao_id}" />
		<h3><label for="id">아이디</label></h3>
		<input type="text" id="id" name="id" maxlength="20" required="required" value="${param.id }" />
	    <p><span class="error_next_box" id="idMsg" role="alert"></span></p>
		<h3><label for="pw">비밀번호</label></h3>
		<input type="password" id="pw" name="pw" maxlength="20" required="required" />
		<p><span class="error_next_box" id="pwMsg" role="alert"></span></p>
		<h3><label for="pwConfirm">비밀번호 확인</label></h3>
		<input type="password" id="pwConfirm" name="pwConfirm" maxlength="20" required="required" />
		<p><span class="error_next_box" id="pwConfirmMsg" role="alert"></span></p>
		<h3><label for="nickname">닉네임</label></h3>
		<input type="text" id="nickname" name="nickname" maxlength="20" required="required" value="${param.nickname }" />
		<p><span class="error_next_box" id="nicknameMsg" role="alert"></span></p>
		<div class="join_row join_birthday">
			<h3 class="join_title"><label for="yy">생년월일</label></h3>
		    <div class="bir_wrap">
		    	<div class="bir_yy">
					<span class="ps_box">
						<select id="yy" name="yy" class="sel" aria-label="년" required="required" >
							<option value="">년</option>
						</select>
					</span>
				</div>
				
				<div class="bir_mm">
					<span class="ps_box">
						<select id="mm" name="mm" class="sel" aria-label="월" required="required" >
							<option value="">월</option>
						</select>
					</span>
				</div>
				
				<div class=" bir_dd">
					<span class="ps_box">
						<select id="dd" name="dd" class="sel" aria-label="일" required="required" >
							<option value="">일</option>
						</select>
					</span>
				</div>
			</div>
			<p><span class="error_next_box" id="birthdayMsg" role="alert"></span></p>
		</div>
		<p>
			<input class="result" type="reset" value="취소" />
			<input class="result" type="button" value="회원가입" />
		</p>
	</form>
</div>
</body>
</html>