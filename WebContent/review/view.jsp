<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1" />
<title>Review Board</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="/semiRecipe/fontello-ea422c41/css/fontello.css">
<style>
   #view_post{
   	height : 900px;
   	max-width : 68rem;
   	margin : 0 auto;
   	border : 2px solid red;
   	margin-bottom : 15px;
   }
</style>

</head>
<body>
	<div>
		<%@ include file="../jsp/menu.jsp" %>
	</div>
	<div id="view_post" >
		<h1>게시글 본문</h1>
	</div>
	
</body>
</html>