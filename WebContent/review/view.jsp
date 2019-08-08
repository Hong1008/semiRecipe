
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1" />
<title>Review Board</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="/semiRecipe/fontello-icon/css/fontello.css">
<style>
   #view_post{
   	height : 400px;
   	width : 760px;
   	margin : 0 auto;
   	border: solid 1px rgb(200, 200, 200);
   	background: rgb(250, 250, 250);
   	margin-bottom : 20px;
   	margin-top : 20px;
   }
   .review_title{
     width : 760px;
	 text-align: center;
   }
   #review_title_first{
   	 height: 50px;
   }
   #review_title_second{
   	 height: 40px;
   }
   
   #revTitle, #revTitleSpace{
     font-weight: 800;
   }
   #revWriter, #revWriterSpace{
   	 border-bottom: solid 1px rgb(200, 200, 200);
   	}
</style>

</head>
<body>
<%-- 
	<div>
		<jsp:include page="../jsp/menu.jsp"></jsp:include>
	</div> --%>
	
	<div id="view_post" >
	<c:set value="${sessionScope.viewer}" var="viewer"/>
		<table class="review_title">
		<tr id="review_title_first">
			<td width = "80px" id="revTitle">제목</td>
			<td width = "350px" id="revTitleSpace">${viewer.review_subject}</td>  
			<td width = "70px" id="revViewcount" class="icon-book-open"></td>
			<td width = "70px" id="revViewcountSpace">${viewer.review_views}</td>
			<td width = "70px" id="revCommcount" class="icon-chat"></td>
			<td width = "70px" id="revCommcountSpace"></td>		
		</tr>
		<tr id="review_title_second">
			<td id="revWriter">글쓴이</td>
			<td id="revWriterSpace">${viewer.user_nickname}</td>
			<td id="recipeID" colspan="2">레시피 아이디 ${viewer.recipe_id}</td>
			<td id="revDate" colspan="2">${viewer.review_date}</td> 			
		</tr>		
		<tr id="review_content">
			<td id="revContent">내용</td>
			<td id="revContentSpace" align = "center">${viewer.review_content}</td>
		</tr>
		</table>
	</div>
	
	<%-- <div>
		<jsp:include page="../review/reviewBoard.jsp"></jsp:include>
	</div> --%>
</body>

</html>