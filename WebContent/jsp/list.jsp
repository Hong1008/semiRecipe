<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../jsp/menu.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1" />
<title></title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:200i,300,300i,400,400i" rel="stylesheet">
<link rel="stylesheet" href="/semiRecipe/fontello-ea422c41/css/fontello.css">
<script src="/semiRecipe/js/primList.js"></script>
<style>
   body{
     background-color: #FFF5E6;
   }
   
   #wrapper{
   	 max-width : 68rem;
   	 margin : 0 auto;
   	 margin-top : 10px;
   }
   
   #main .thumbnails{
     display : flex;
   }
   #main .thumbnails>div{
     flex-grow : 1;
     margin-right: 1rem;
     margin-left: 1rem;
   }
   
   #main .thumbnails a{
      margin-bottom: 1.5rem;
      display : block;
      text-decoration: none;
    }
   
   
   #main .thumbnails img{
     width : 100px;
     max-width: 350px;
     border-top-left-radius: 0.2rem; 
     border-top-right-radius: 0.2rem; 
     display : block;
   }
   
   #main .thumbnails h3{
   	border-left : 2px solid rgba(247,206,173,0.3);
   	border-right : 2px solid rgba(247,206,173,0.3);
   	border-bottom : 2px solid rgba(247,206,173,0.3);
   	border-bottom-left-radius: 0.2rem; 
    border-bottom-right-radius: 0.2rem; 
    padding : 1rem;
    text-align : center;
    background-color: rgba(255,255,255,0.1);
    color : #8D4738;
    font-size : 0.8rem;
    font-weight : 200;
    max-width: 314px;
   }
   
   #main .thumbnails h3:hover{
    background-color: rgba(247,206,173,0.8);
   }
   
   #buttonLine{
   	 max-width : 68rem;
   	 margin : 0 auto;
   	 margin-bottom : 50px;
   }
      
   #buttonLine .icon-th-large-outline{
   	 padding : 3px;
   	 float : left; 
   	 text-decoration: none;
   	 color : #FFF5E6;
   	 background-color : rgba(141,71,56,0.7);
   	 border : 1px solid rgba(141,71,56,0.3);
   	 border-radius: 0.2rem; 
   	 font-size : 20px;
   }
   #buttonLine .icon-comment {
   	 padding : 3px;
   	 float : right; 
   	 text-decoration: none;
   	 color : #FFF5E6;
   	 background-color : rgba(141,71,56,0.7);
   	 border : 1px solid rgba(141,71,56,0.3);
   	 border-radius: 0.2rem; 
   	 font-size : 20px;
   }
</style>

</head>
<body>
	<!-- hidden div -->
	<div id="hidden"></div>
	
	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main">

			<!-- Thumbnails -->
			
			<section class="thumbnails">
				
				<%@include file="../ajax/listResult.jsp"%>
				
			</section>
			<div id = "buttonLine">
				<a href="#" class="icon-th-large-outline"> 목록</a>
				<a href="#" class="icon-comment"> 쓰기</a>
			</div>
		</section>

	</div>

	<input type="text" id="search">
	
	<a href="#" class="sort" id="sortView">
		<input type="hidden" class="column" value="prim_view">
		<input type="hidden" class="order" value="desc">
	조회수순</a>
	<a href="#" class="sort" id="sortRate">
		<input type="hidden" class="column" value="rating">
		<input type="hidden" class="order" value="desc">
	별점순</a>
	<a href="#" class="sort" id="sortNM">
		<input type="hidden" class="column" value="recipe_nm_ko">
		<input type="hidden" class="order" value="asc">
	가나다순</a>
	<!-- Scripts -->
	<script src="/semiRecipe/assets/js/jquery.min.js"></script>
	<script src="/semiRecipe/assets/js/jquery.poptrox.min.js"></script>
	<script src="/semiRecipe/assets/js/skel.min.js"></script>
</body>
</html>