<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<title>Review Board</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('.listLine a').on('click', function() {
		console.log($(this).attr('id'));
		$.ajax({
 			type: 'POST',
 			dataType: 'text',
 			data : 'review_num='+$(this).attr('id'),
 			url: 'reviewview',
 			success: function(res){
 				$('#view').html(res);
				$('html, body').animate( { scrollTop : 500 }, 400);
 			}
 		});
		return false;
	});
	
	
});
</script>
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:200i,300,300i,400,400i" rel="stylesheet">
<link rel="stylesheet" href="/semiRecipe/fontello-icon/css/fontello.css">
<link rel="stylesheet" href="/semiRecipe/css/reviewBoardcss.css">



</head>

<%
	String uri = request.getRequestURI();
	String path = uri.substring(uri.lastIndexOf("/"));
%>
<body>
	<div>
		<c:set value="<%=path%>" var="i" />
		<c:if test="${i == '/reviewBoard.jsp'}">
			<%@ include file="../jsp/menu.jsp"%>
		</c:if>
		
	</div>
	<!-- hidden div -->
	<div id="hidden"></div>

	<div id="view">
		
	</div>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main">

			<!-- Thumbnails -->

			<section class="thumbnails">
				<%-- 
				<c:forEach items="${requestScope.aList}" var="revList" varStatus="status">
					<c:if test =${status.count%3==0} ></c:if>
					
				<div>
					<a href="/semiRecipe/review/view.jsp"> <img src="${revList.review_url}" alt="" />
						<h3>${revList.review_subject}</h3>
					</a> 
				</div>
				
				</c:forEach>
				 --%>
				<div class="listLine">
					<c:forEach items="${sessionScope.reviewList}" var="revList">
						<c:if test="${revList.review_num%4==1}">
							<a href="#" id="${revList.review_num}"> <img src="${revList.review_url}" alt="" />				
								<h3>${revList.review_subject}
								<br/><font color="#BC8F8F">${revList.user_nickname}</font></h3>
							
							</a>
						</c:if>
					</c:forEach>
				</div>
				<div class="listLine">
					<c:forEach items="${sessionScope.reviewList}" var="revList">
						<c:if test="${revList.review_num%4==2}">
							<a href="#" id="${revList.review_num}"> <img src="${revList.review_url}" alt="" />
								<h3>${revList.review_subject} 
								<br/><font color="#BC8F8F">${revList.user_nickname}</font></h3>
							</a>
						</c:if>
					</c:forEach>
				</div>

				<div class="listLine">
					<c:forEach items="${sessionScope.reviewList}" var="revList">
						<c:if test="${revList.review_num%4==3}">
							<a href="#" id="${revList.review_num}"> <img src="${revList.review_url}" alt="" />
								<h3>${revList.review_subject}
								<br/><font color="#BC8F8F">${revList.user_nickname}</font></h3>
							</a>
						</c:if>
					</c:forEach>
				</div>
				

				<div class="listLine">
					<c:forEach items="${sessionScope.reviewList}" var="revList">
						<c:if test="${revList.review_num%4==0}">
							<a href="#" id="${revList.review_num}"><img src="${revList.review_url}" alt="" /> 
								<h3>${revList.review_subject}   
								<br/><font color="#BC8F8F">${revList.user_nickname}</font></h3>
							</a>
						</c:if>
					</c:forEach>
				</div>


			</section>
			<form action="#" method="post" id="searchform">
			<div id="searchLine">
				<select name="searchDrop" id="searchDrop">
					<option value="title_content" selected>전체</option>
					<option value="title">제목</option>
					<option value="content">검색</option>
				</select>
				<input type ="text" name = "search_keyword" id ="searchText"/>
				<button class="icon-search">검색</button>
			</div>
			</form>
			<div id="buttonLine">
				<a href="review" class="icon-th-thumb-empty"> 목록</a> 
				<a href="reviewwrite" class="icon-comment"> 쓰기</a>
			</div>
		</section>
	</div>

	<!-- Scripts -->
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/jquery.poptrox.min.js"></script>
	<script src="../assets/js/skel.min.js"></script>
	<script src="/semiRecipe/assets/js/jquery.min.js"></script>
	<script src="/semiRecipe/assets/js/jquery.poptrox.min.js"></script>
	<script src="/semiRecipe/assets/js/skel.min.js"></script>
</body>
</html>