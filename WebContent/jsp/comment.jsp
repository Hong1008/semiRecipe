<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="URI" value="${pageContext.request.requestURI}" />

<link rel="stylesheet" href="/semiRecipe/css/comment.css">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100&display=swap"
	rel="stylesheet">
<script type="text/javascript" src="/semiRecipe/js/comment.js"></script>

<%-- <c:forEach items="" var='cmt'>
	<div class='CMT_comment'>
		<div class='CMT_icon'>${cmt.아이콘 }</div>
		<div class='CMT_info'>
			<span class='CMT_ID'>${cmt.user_id }</span><span class='CMT_date'>${cmt.수정된날짜}</span>
		</div>
		<p class='CMT_text'>${cmt.댓글내용 }</p>
	</div>
</c:forEach> --%>

<c:out value="${URI }"></c:out>
<form action="">
	<div class='CMT_icon'></div>
	<textarea id='CMT_input' autocomplete='off' placeholder="댓글을 추가하세요"
		wrap="hard"></textarea>
	<br>
	<div id='star_div'>
		<img src="/semiRecipe/img/cupcake.png" class='star' > <img
			src="/semiRecipe/img/cupcake.png" class='star'> <img
			src="/semiRecipe/img/cupcake.png" class='star'> <img
			src="/semiRecipe/img/cupcake.png" class='star'> <img
			src="/semiRecipe/img/cupcake.png" class='star'>
	</div>
	<input type='submit' id='CMT_submit' value='댓글'> <input
		type="reset" id='CMT_reset' value='취소'>
</form>
