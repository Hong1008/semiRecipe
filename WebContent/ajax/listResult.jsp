<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${requestScope.recList}" var="recList" varStatus="i">
	<div>
		<a href="#"> <img src="${recList.IMG_URL}" alt="" />
			<h3>${recList.RECIPE_NM_KO}</h3>
		</a>
	</div>
</c:forEach>