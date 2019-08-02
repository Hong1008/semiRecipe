<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	width: 30%;
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

</style>
</head>
<body>
	<c:set value="${requestScope.dto}" var="dto" />
	<table id="myPageBody">
		<caption>마이페이지</caption>
		<tr class="content">
			<td class="myPageList">아이디</td>
			<td colspan="4">${dto.id}</td>
		</tr>
		<tr class="content">
			<td class="myPageList">비밀번호</td>
			<td><a href="#" type="button">변경</a></td>
			<td>
				<p>현재 비밀번호 :</p>
				<p>변경 비밀번호 :</p>
			</td>
			<td>
				<p><input type="password"></p>
				<p><input type="password"></p>
			</td>
			<td><input type="button" value="변경" id="pwChangeFinish" /></td>
		</tr>
		<tr class="content">
			<td class="myPageList">닉네임</td>
			<td id="nickname">
				<form action="checkNickname" id="nicknameChange">
					<span id="nValue">${dto.nickname}</span>
					<input type="text" class="box" id="nBox" style="display: none;" value="${dto.nickname}"/>
					<input type="button" value="변경" id="nChangeBtn" />
					<input type="button" value="취소" id="nChangeCancleBtn" style="display: none;" />
				</form>
			</td>
		</tr>
		<tr class="content">
			<td class="myPageList">생년월일</td>
			<td>${dto.birthday}</td>
		</tr>
	</table>

	<div id="finish"><input type="button" value="수정 완료" id="finishBtn" /></div>
	
</body>
</html>