<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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

.myPageList {
	font-weight: bold;
	width: 30%;
}
</style>
<script>
	$(document).ready(function() {
		$('#nChangeBtn').on('click',function() {
			$('#nValue').css('display','none');
			$('#nBox').css('display','inline');
			$('#nChangeCancleBtn').css('display','inline');
		});
		
		$('#nChangeCancleBtn').on('click', function() {
			$('#nBox').css('display','none');
			$('#nChangeCancleBtn').css('display','none');
		});
		
	});
</script>
</head>
<body>
	<c:set value="${requestScope.dto}" var="dto" />
	<table id="myPageBody">
		<caption>마이페이지</caption>
		<tr>
			<td class="myPageList">아이디</td>
			<td>${dto.id}</td>
		</tr>
		<tr>
			<td class="myPageList">비밀d번호</td>
			<td ><a href="#" type="button">변경</a></td>
		</tr>
		<tr>
			<td class="myPageList">닉네임</td>
			<td id="nickname">
				<span id="nValue">${dto.nickname}</span>
				<input type="text" id="nBox" style="display: none;" value="${dto.nickname}"/>
				<input type="button" value="변경" id="nChangeBtn" />
				<input type="button" value="취소" id="nChangeCancleBtn" style="display: none;" />
			</td>
		</tr>
		<tr>
			<td class="myPageList">생년월일</td>
			<td>${dto.birthday}</td>
		</tr>
	</table>
	
</body>
</html>