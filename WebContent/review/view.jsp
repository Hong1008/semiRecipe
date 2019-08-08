
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1" />
<title>Review Board</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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

	<div>
		<jsp:include page="../jsp/menu.jsp"></jsp:include>
	</div>
	
	<div id="view_post" >
		<table class="review_title">
		<tr id="review_title_first">
			<td width = "80px" id="revTitle">제목</td>
			<td width = "350px" id="revTitleSpace">${param.subject}</td>  
			<td width = "70px" id="revViewcount">조회수</td>
			<td width = "70px" id="revViewcountSpace"></td>
			<td width = "70px" id="revCommcount">댓글수</td>
			<td width = "70px" id="revCommcountSpace"></td>		
		</tr>
		<tr id="review_title_second">
			<td id="revWriter">글쓴이</td>
			<td id="revWriterSpace"></td>			
		</tr>		
		</table>
	</div>
	
	<div>
		<jsp:include page="../review/reviewBoard.jsp"></jsp:include>
	</div>
</body>

</html>