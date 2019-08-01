<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1" />
<title>Review Board</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:200i,300,300i,400,400i" rel="stylesheet">
<link rel="stylesheet" href="/semiRecipe/review/fontello-ea422c41/css/fontello.css">
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
     width : 100%;
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
    padding : 2rem;
    text-align : center;
    background-color: rgba(255,255,255,0.1);
    color : #8D4738;
    font-size : 0.8rem;
    font-weight : 200;
    max-width: 320px;
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
	<div>
		<%@ include file="../jsp/menu.jsp" %>
	</div>
	<!-- hidden div -->
	<div id="hidden"></div>
	
	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main">

			<!-- Thumbnails -->
			
			<section class="thumbnails">
				
				<c:forEach items="${requestScope.aList}" var="revList">
				<div>
					<a href="/semiRecipe/review/view.jsp"> <img src="${revList.review_url}" alt="" />
						<h3>${revList.review_subject}</h3>
					</a> 
				</div>
				</c:forEach>
				
			</section>
			<div id = "buttonLine">
				<a href="../review/reviewBoard.jsp" class="icon-th-large-outline"> 목록</a>
				<a href="../review/write.jsp" class="icon-comment"> 쓰기</a>
			</div>
		</section>

	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.poptrox.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
</body>
</html>