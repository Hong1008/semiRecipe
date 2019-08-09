<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/semiRecipe/js/myPage.js"></script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

#myPageBody {
	margin: 0 auto;
	width: 700px;
	height: 700px;
	border-top: 1px solid #e8e9ed;
	border-collapse: collapse;
}

#myPageBody caption {
	font-size: 30px;
	font-weight: bold;
}

#myPageBody td {
	border-bottom: 1px solid #e8e9ed;
}

#myPageBody .content {
	height: 25%;
}

.myPageList {
	font-weight: bold;
	width: 25%;
}

input {
	height: 23px;
}

#finish {
	text-align: center;
	padding: 15px;
}

span {
	width: 50px;
}

td p {
	height: 25px;
}

td p input {
	height : 20px;
}

#yy, #mm, #dd {
	width: 147px;
	height: 30px;
	float: left;
	margin-right: 10px;
}

td {
	height: 150px;
}
#userIcon > img{
	width: 100px;
	height: 100px;
}
</style>
</head>
<body>
	<c:set value="${requestScope.dto}" var="dto" />
	<table id="myPageBody">
		<caption>마이페이지</caption>
		<tr class="content">
			<td class="profile" rowspan="2">프로필</td>
			<td class="myPageList">아이디</td>
			<td id="loginId">${dto.user_id}</td>
		</tr>
		<tr class="content">
			<td class="myPageList">아이콘</td>
			<td id="userIcon"><img src="${dto.user_icon}"></td>
			<td>
				<input type="button" value="변경" id="iconChangeBtn" />
			</td>
		</tr>
		<tr class="content">
			<td class="myPageList">비밀번호</td>
			<td class="pwHide">
				<p>현재 비밀번호 :</p>
				<p>변경 후 비밀번호 :</p>
				<p>변경 후 비밀번호 확인 :</p>
			</td>
			<td class="pwHide">
				<p><input type="password" id="now_pw"></p>
				<p><input type="password" id="new_pw"></p>
				<p><input type="password" id="new_pw_confirm"></p>
			</td>
			<td>
				<input type="button" value="변경" id="pwChangeBtn" />
			</td>
		</tr>
		<tr class="content">
			<td class="myPageList">닉네임</td>
			<td id="nickname">
				<span id="nValue">${dto.user_nickname}</span>
				<input type="text" class="box" id="nBox" style="display: none;" value="${dto.user_nickname}"/>
				<input type="button" value="변경" id="nChangeBtn" />
				<input type="button" value="취소" id="nChangeCancleBtn" style="display: none;" />
			</td>     
		</tr>
		<tr class="content">
			<td class="myPageList">생년월일</td>
			<td class="newBirthday" style="display: none;">
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
			</td>
			<td>
				<span id="birthValue">${dto.user_birthday}</span>
				<input type="button" value="변경" id="birthChangeBtn" />
			</td>
		</tr>
	</table>

	<div id="finish">
		<input type="button" value="취소" id="modCancleBtn" />
		<input type="button" value="수정 완료" id="modFinishBtn" />
	</div>
	
	
	
	
	
</body>
</html>