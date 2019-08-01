<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<link rel="stylesheet" href="/semiRecipe/css/menu.css">
<script type="text/javascript" src="/semiRecipe/js/menu.js"></script>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
	<header id='header'>
		<div id='menu_bar'>
			<img src="/semiRecipe/img/menu_back.jpg">
		</div>
		<div id='logo_container'>
			<img src="/semiRecipe/img/LOGO_white.png">
		</div>
		<div id='log_container'>
			<!--------------------------- 이미지 수정해야됨 ------------------------->
			<img src="/semiRecipe/img/food_test.jpg" id='myPage'>
			<c:choose>
				<c:when test="${empty sessionScope.logOk}">
					<img src="/semiRecipe/img/menu_login.png" id='login'>
				</c:when>
				<c:otherwise>
					<img src="/semiRecipe/img/menu_logout.png" id='logout'>
				</c:otherwise>
			</c:choose>
		</div>
		<div id='menu_container'>
			<ul id="menu_ul">
				<li class="menu_li" id='home'><img
					src="/semiRecipe/img/menu01.PNG">
					</li>
				<li class="menu_li" id='review'><img
					src="/semiRecipe/img/menu02.PNG"></li>
				<li class="menu_li" id='myRecipe'><img
					src="/semiRecipe/img/menu03.PNG"></li>
				<li class="menu_li" id='notice'><img
					src="/semiRecipe/img/menu04.PNG"></li>
			</ul>
		</div>

	</header>

</body>
</html>