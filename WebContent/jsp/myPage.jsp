<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/semiRecipe/js/myPage.js"></script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

#myPageBody {
	margin: 0 auto;
	width: 800px;
	height: 700px;
	border-top: 1px solid #e8e9ed;
	border-collapse: collapse;
}

#myPageBody caption {
	font-size: 30px;
	font-weight: bold;
}

#myPageBody > tbody > tr > td {
	border-bottom: 1px solid #e8e9ed;
	width: 100px;
	height: 150px;
	color: #a4a4a4;
    font-size: 13px;
}

#myPageBody td.profile, #myPageBody td.private, #myPageBody td.myPageList {
	font-weight: bold;
	width: 12%;
	color: black;
    font-size: 15px;
}

input {
	height: 23px;
}

#finish {
	text-align: center;
	padding: 15px;
}

td p {
	height: 25px;
}

td p input {
	height : 20px;
}

#yy, #mm, #dd {
	width: 100px;
	height: 30px;
	float: left;
	margin-right: 10px;
}

#user_icon > img{
	width: 100px;
	height: 100px;
}

.btns {
	border-radius: 5px;
	color: #ffffff;
	text-decoration: none;
    border: 1px solid #0199df;
    background-color: #0199df;
    padding: 6px 24px;
}

.iconHide img {
    width: 35px;
    height: 35px;
    border: 1px solid white;
}



tr:nth-child(2) {
	width: 10px;
}

</style>
</head>
<body>
<%
	String file = application.getRealPath("/img/icon");
	File f = new File(file);
	String[] fileNames = f.list();
	File[] fileObjects = f.listFiles();
	
	ArrayList<String> fList = new ArrayList<String>(); 
	
	for (int i = 0; i < fileObjects.length; i++) {
		if (!fileObjects[i].isDirectory()) {
			if(fileNames[i].indexOf("cooker")>-1)
				fList.add(fileNames[i]);
		}
	}
%>
	<c:set value="${requestScope.dto}" var="dto" />
	<table id="myPageBody">
		<caption>마이페이지</caption>
		<tr class="content">	<!-- 아이디 -->
			<td class="profile" rowspan="3">프로필</td>
			<td class="myPageList">아이디</td>
			<td id="loginId" colspan="3">${dto.user_id}</td>
		</tr>
		<tr class="content">	<!-- 아이콘 -->
			<td class="myPageList">아이콘 </td>
			<td id="user_icon"><img src="/semiRecipe/img/icon/${dto.user_icon}"></td>
			<td>
				<div class="iconHide">
					<c:forEach var="iconArr" items="<%=fList %>" varStatus="status" begin="0" end="<%=fList.size() %>" >
						<c:if test="${iconArr!=dto.user_icon}">
							<a class="icon" href=""><img alt="" src="/semiRecipe/img/icon/${iconArr}"></a>
						</c:if>
						<c:if test="${status.index==(status.end-1)/2}">
							<br/>
						</c:if>
					</c:forEach>
				</div>
			</td>
			<td>
				<a href="" id="iconChangeBtn" class="btns">변경</a>
				<a href="" id="iconChangeCancleBtn" class="iconHide btns">취소</a>
			</td>
		</tr>
		<tr class="content">	<!-- 닉네임 -->
			<td class="myPageList">닉네임</td>
			<td id="nickname">
				<span id="nValue">${dto.user_nickname}</span>
			</td>
			<td>	
				<input type="text" class="nHide" id="nBox" value="${dto.user_nickname}"/>
			</td>
			<td>
				<a href="" id="nChangeBtn" class="btns">변경</a>
				<a href="" id="nChangeCancleBtn" class="nHide btns">되돌리기</a>
			</td>
		</tr>
		<tr class="content">	<!-- 비밀번호 -->
			<td class="private" rowspan="2">개인정보</td>
			<td class="myPageList">비밀번호</td>
			<td colspan="1">
				<div class="pwHide">
					<p>현재 비밀번호 :</p>
					<p>변경 후 비밀번호 :</p>
					<p>변경 후 비밀번호 확인 :</p>
				</div>
			</td>
			<td>
				<div class="pwHide">
					<p><input type="password" id="now_pw"></p>
					<p><input type="password" id="new_pw"></p>
					<p><input type="password" id="new_pw_confirm"></p>
				</div>
			</td>
			<td>
				<a href="" id="pwChangeBtn" class="btns">변경</a>
				<a href="" id="pwChangeCancleBtn" class="pwHide btns">취소</a>
			</td>
		</tr>
		
		<tr class="content">	<!-- 생년월일 -->
			<td class="myPageList">생년월일</td>
			<td>
				<span id="birthValue">${dto.user_birthday}</span>
			</td>
			
			<td class="birthHide">
				<div class="bir_wrap">
			    	<div class="bir_yy">
						<span class="ps_box">
							<select id="yy" name="yy" class="sel" aria-label="년" required="required" >
								<option value="">년</option>
							</select>
						</span>
					</div>
				
					<div class="bir_mm">
						<span class="ps_box">
							<select id="mm" name="mm" class="sel" aria-label="월" required="required" >
								<option value="">월</option>
							</select>
						</span>
					</div>
				
					<div class=" bir_dd">
						<span class="ps_box">
							<select id="dd" name="dd" class="sel" aria-label="일" required="required" >
								<option value="">일</option>
							</select>
						</span>
					</div>
				</div>
			</td>
			
			<td>
				<a href="" id="birthChangeBtn" class="btns">변경</a>
				<a href="" id="birthChangeCancleBtn" class="birthHide btns">취소</a>
			</td>
			
		</tr>
	</table>

	<div id="finish">
		<input type="button" value="취소" id="modCancleBtn" />
		<input type="button" value="수정 완료" id="modFinishBtn" />
	</div>
	
	
	
	
	
</body>
</html>