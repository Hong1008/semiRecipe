
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
   	height : 300px;
   	width : 68rem;
   	margin : 0 auto;
   	border : 2px solid red;
   	margin-bottom : 20px;
   	margin-top : 20px;
   }
   .review_title{
     width : 68rem;
   	 display:inline-block;
   	 height :40px;
   	 
   }
   
   #revTit, #revTitle {
   	float:left;
   	text-align: center;
   }
   
   #revViewcount, #revCommcount, #revWriter{
   	float:right;
   }
</style>

</head>
<body>

	<div>
		<jsp:include page="../jsp/menu.jsp"></jsp:include>
	</div>
	
	<div id="view_post" >
		<div class="review_title">
		<span id="revTit" style="width : 80px; font-weight:bold;">제목</span><span id="revTitle" style="width : 250px; font-weight:bold;">${param.subject}</span>
		<span id="revViewcount">조회수</span><span id="revCommcount">댓글수</span>
		<br/>
		<span id="revWriter">글쓴이</span><span></span>
		</div>
		<hr/>
	</div>
	
	<div>
		<jsp:include page="../review/reviewBoard.jsp"></jsp:include>
	</div>
</body>

</html>