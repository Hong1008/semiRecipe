<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../jsp/menu.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<title></title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:200i,300,300i,400,400i"
	rel="stylesheet">
<link rel="stylesheet" href="/semiRecipe/fontello-icon/css/fontello.css">
<script src="/semiRecipe/js/primList.js"></script>
<style>
body {
	background-color: #FFF5E6;
}

#wrapper {
	max-width: 68rem;
	margin: 0 auto;
	margin-top: 10px;
	text-align: center;
}

#main .thumbnails {
	display: block;
}

#main .thumbnails>div {
	flex-grow: 1;
	margin-right: 1rem;
	margin-left: 1rem;
}

#main .thumbnails a {
	margin-bottom: 1.5rem;
	display: block;
	text-decoration: none;
}

#main .thumbnails img {
	width: 200px;
	max-width: 350px;
	border-top-left-radius: 0.2rem;
	border-top-right-radius: 0.2rem;
	display: block;
}

#main .thumbnails h3 {
	border-left: 2px solid rgba(247, 206, 173, 0.3);
	border-right: 2px solid rgba(247, 206, 173, 0.3);
	border-bottom: 2px solid rgba(247, 206, 173, 0.3);
	border-bottom-left-radius: 0.2rem;
	border-bottom-right-radius: 0.2rem;
	padding: 1rem;
	text-align: center;
	background-color: rgba(255, 255, 255, 0.1);
	color: #8D4738;
	font-size: 0.8rem;
	font-weight: 200;
	max-width: 314px;
}

#main .thumbnails h3:hover {
	background-color: rgba(247, 206, 173, 0.8);
}

#main .thumbnails .listLine {
	display: block;
}

#buttonLine {
	max-width: 68rem;
	margin: 0 auto;
	margin-bottom: 50px;
}

#buttonLine .icon-th-large-outline {
	padding: 3px;
	float: left;
	text-decoration: none;
	color: #FFF5E6;
	background-color: rgba(141, 71, 56, 0.7);
	border: 1px solid rgba(141, 71, 56, 0.3);
	border-radius: 0.2rem;
	font-size: 20px;
}

#buttonLine .icon-comment {
	padding: 3px;
	float: right;
	text-decoration: none;
	color: #FFF5E6;
	background-color: rgba(141, 71, 56, 0.7);
	border: 1px solid rgba(141, 71, 56, 0.3);
	border-radius: 0.2rem;
	font-size: 20px;
}

#searchLine {
	text-align: center;
}

#search_button {
	padding: 3px;
	text-decoration: none;
	color: #FFF5E6;
	background-color: rgba(141, 71, 56, 0.7);
	border: 1px solid rgba(141, 71, 56, 0.3);
	border-radius: 0.2rem;
	font-size: 20px;
}

.button {
	display: inline-block;
	width: 200px;
	height: 54px;
	text-align: center;
	text-decoration: none;
	line-height: 54px;
	outline: none;
	position: relative;
	z-index: 2;
	background-color: #333;
	border: 2px solid #333;
	color: #fff;
	line-height: 50px;
	margin-bottom: 4rem;
}

.js-load {
	display: none;
}

.js-load.active {
	display: inline-block;
}
#quick_menu{
	width:95px;text-align:center;
	position:absolute;
	top:80%;
	left:90%;
	
}
</style>

</head>
<body>
	<!-- hidden div -->
	<div id="hidden"></div>

	<form action="#" method="post">
		<div id="searchLine">
			<p class="search_icon"></p>
			<select name="searchDrop">
				<option value="title_content" selected>전체</option>
				<option value="title">제목</option>
				<option value="content">검색</option>
			</select> <input type="text" name="search_keyword" /> <input
				type="submit" id="search_button" value="검색">
		</div>
	</form>
	<a href="#" class="sort" id="sortView"> <input type="hidden"
		class="column" value="prim_views"> <input type="hidden"
		class="order" value="desc"> 조회수순
	</a>
	<a href="#" class="sort" id="sortRate"> <input type="hidden"
		class="column" value="rating"> <input type="hidden"
		class="order" value="desc"> 별점순
	</a>
	<a href="#" class="sort" id="sortNM"> <input type="hidden"
		class="column" value="recipe_nm_ko"> <input type="hidden"
		class="order" value="asc"> 가나다순
	</a>

	<!-- Wrapper -->
	<div id="wrapper">
		

		<!-- Main -->
		<section id="main">

			<!-- Thumbnails -->

			<section class="thumbnails">

				<%@include file="../ajax/listResult.jsp"%>


			</section>

			<!-- <div id="js-btn-wrap" class="btn-wrap">
				<a href="javascript:;" class="button">더보기</a>
			</div> -->
			</section>
	</div>
<div id="quick_menu"><a href="#"><img src="/semiRecipe/img/quick_top_btn.png" 
     alt="상단으로 이동" /></a></div>

	<!-- Scripts -->
	<script src="/semiRecipe/assets/js/jquery.min.js"></script>
	<script src="/semiRecipe/assets/js/jquery.poptrox.min.js"></script>
	<script src="/semiRecipe/assets/js/skel.min.js"></script>
</body>
</html>