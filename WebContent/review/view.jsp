

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
   	height : 100%;
   	min-height :300px;
   	width : 800px;
   	margin : 0 auto;
   	border: solid 1px rgb(200, 200, 200);
   	background: rgb(250, 250, 250);
   	margin-bottom : 20px;
   	margin-top : 20px;
   }
   .viewerTable, .viewerTableSecond{
   	margin-left: 20px;
   	margin-right: 20px;
    width :760px;
	text-align: center;
   }
   #review_title_first{
   	 height: 50px;
   }
   #review_title_second{
   	 height:50px;
   }
   
   #review_content{
   	 height: 100%;
   	 min-height : 300px;
   }
   
   #revTitle, #revTitleSpace{
     font-weight: 800;
   }
   #revContentSpace{
   	 border-top: solid 1.5px #000000;
   	 padding-top : 8px;
   	}
</style>

</head>
<body>
<%-- 
	<div>
		<jsp:include page="../jsp/menu.jsp"></jsp:include>
	</div> --%>
	
	<div id="view_post" >
	<c:set value="${requestScope.viewer}" var="viewer"/>
		<table class="viewerTable">
		<tr id="review_title_first">
			<td width = "555px" colspan="2" align ="left" id="revTitleSpace">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${viewer.review_subject}</td>  
			<td width = "50px" id="revViewcount" class="icon-book-open"></td>
			<td width = "50px" id="revViewcountSpace">${viewer.review_views}</td>
			<td width = "50px" id="revCommcount" class="icon-chat"></td>
			<td width = "50px" id="revCommcountSpace"></td>		
		</tr>
		</table>
		<table class="viewerTableSecond">
		<tr id="review_title_second">
			<td id="revWriter" class="icon-pencil" width = "48px"></td>
			<td id="revWriterSpace" width = "195px" align ="left">&nbsp;&nbsp;${viewer.user_nickname}</td>
			<td id="recipeID" width = "300px">레시피 아이디 ${viewer.recipe_id}</td>
			<td id="revRate" class="icon-star" width ="50px"></td> 
			<td id="revRateSpace" width="50px">${viewer.review_rate}</td> 			
			<td id="revDate" width = "100px">${viewer.review_date}</td>
		</tr>		
		<tr id="review_content">
			<td id="revContentSpace" align = "center" colspan = "6" >${viewer.review_content}</td>
		</tr>
		</table>
	</div>
	
	<%--<div>
		<jsp:include page="../review/reviewBoard.jsp"></jsp:include>
	</div> --%>
</body>

</html>