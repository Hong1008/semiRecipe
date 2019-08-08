<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${requestScope.irdntMain }" var="dto" varStatus="status">
	<li class="irdnts" id="${dto.RECIPE_ID}" width="80px" style="font-size:15px;">${dto.IRDNT_NM } 
		<input type="radio" name="${status.count}" id="${status.count}" value="필수"/><label for ="${status.count}">필수</label>
		<input type="radio" name="${status.count}" id="${status.count}" value="상"/><label for ="${status.count}">상</label>
		<input type="radio" name="${status.count}" id="${status.count}" value="중"/><label for ="${status.count}">중</label>
		<input type="radio" name="${status.count}" id="${status.count}" value="하"/><label for ="${status.count}">하</label>

	</li>

</c:forEach>
<hr/>
<c:forEach items="${requestScope.irdntSub }" var="dto">
	<li class="irdnts" id="${dto.RECIPE_ID}"  width="80px" style="font-size:15px;">${dto.IRDNT_NM }
		<input type="radio" name="${status.count}" id="${status.count}" value="필수"><label for ="${status.count}">필수</label>
		<input type="radio" name="${status.count}" id="${status.count}" value="상"><label for ="${status.count}">상</label>
		<input type="radio" name="${status.count}" id="${status.count}" value="중"><label for ="${status.count}">중</label>
		<input type="radio" name="${status.count}" id="${status.count}" value="하"><label for ="${status.count}">하</label>
		</li>

</c:forEach>