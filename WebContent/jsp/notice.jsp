
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../jsp/menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title></title>
<style type="text/css">
body {
	height: 3000px;
}
td{
	t
</style>
</head>
<body>
<h1>공지사항</h1>
<div class="list_header">
	<span>몇개의 글</span>
	<select name="blockCount">
		<option value="5">5개</option>
		<option value="10">10개</option>
		<option value="15">15개</option>
	</select> 
</div>
<hr>
<table>
	<colgroup>
		<col style="width:88px">
    	<col>
    	<col style="width:118px">
    	<col style="width:80px">
    	<col style="width:68px">
	</colgroup>
	<thead>
		<tr>
		<th> </th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회</th>
		</tr>
	</thead>
	<tbody>
		<tr>
		<td>공지</td>
		<td>제목입니다@@@@@@@@@@@@@@@@@@@@@@@@@@@</td>
		<td>작성자입니다</td>
		<td>날짜입니다</td>
		<td>1313131</td>
		</tr>
		<tr>
		<td>공지</td>
		<td>제목입니다@@@@@@@@@@@@@@@@@@@@@@@@@@@</td>
		<td>작성자입니다</td>
		<td>날짜입니다</td>
		<td>1313131</td>
		</tr>
	</tbody>
</table>
</body>
</html>