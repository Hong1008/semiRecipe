<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../menu/menu.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">


<link rel="stylesheet" href="/semiRecipe/css/home_container.css">
<!-- container연결-->

<script src="/semiRecipe/js/plugin/jquery-ui.min.js"></script>
<script src="/semiRecipe/js/plugin/hangul.js"></script>
<!--플러그인 연결-->


<script type="text/javascript" src="/semiRecipe/js/home_container.js"></script>

<script type="text/javascript">

</script>



</head>


<body>
	<div id='wrap' class='test'>
	<!-- hr -->
	<!-- hong -->
	<!-- ram -->
		<!--여기서부터 컨테이너시작-->
		<div id='container' class='test'>
			<div id='main_contents'>
				<div id='selected_ing_div'></div>
				<div id='selected_recipe_view'></div>
			</div>
			<div id='side_menu'>
				<div id='side_btn'>
					<img src="/semiRecipe/img/home_open_btn.png">
				</div>
				<div id='search_div'>
					<label for='search'><img
						src="/semiRecipe/img/home_search_img.PNG"></label><input type="text"
						name="search" autocomplete="off">
				</div>
				<div id='ingredients_div'>
					<div id='ingredients_menu_div'>
						<img src="/semiRecipe/img/home_arrow.png" id='ing_m_left'>
						<img src="/semiRecipe/img/home_arrow.png" id='ing_m_right'>
						<ul id='ing_menu'>
							<c:forEach items="${requestScope.tList }" var="ty">
								<li class='ing_menu_li' id="${ty }">${ty }</li>							
							</c:forEach>
						</ul>
					</div>
					<div id='ing_maker'>
						<c:forEach items="${requestScope.aList }" var="dto">
							<div class='ingredients' id='${dto.irdnt_nm}'>${dto.irdnt_nm}<input type="hidden" id='${dto.ty_nm }' /></div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<!--여기까지 컨테이너 끝-->
		<footer></footer>
	</div>
</body>
</html>