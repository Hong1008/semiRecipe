<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table id="irdntsTable">
<tr>
	<td colspan ="2" align ="center" height="30px"> 재료 중요도 선택 </td>
</tr>

<c:forEach items="${requestScope.irdntMain }" var="dto" varStatus="status">
<tr>
	<td width = "150px">
	<li class="irdnts" id="${dto.RECIPE_ID}" style="font-size:15px;">${dto.IRDNT_NM } </li>
	</td>
	<td>
		<input type="radio" name="main${status.count}" id="1" value="필수"/>필수
		<input type="radio" name="main${status.count}" id="2" value="상"/>상
		<input type="radio" name="main${status.count}" id="3" value="중"/>중
		<input type="radio" name="main${status.count}" id="4" value="하"/>하
	</td>
</tr>

</c:forEach>
<tr>
	<td colspan ="2" align ="center" height="30px"> 부재료 </td>
</tr>
<c:forEach items="${requestScope.irdntSub }" var="dto" varStatus="status">
<tr>
	<td width = "150px">
	<li class="irdnts" id="${dto.RECIPE_ID}" style="font-size:15px;">${dto.IRDNT_NM }</li>
	</td>
	<td>
		<input type="radio" name="sub${status.count}" id="5" value="필수"/>필수
		<input type="radio" name="sub${status.count}" id="6" value="상"/>상
		<input type="radio" name="sub${status.count}" id="7" value="중"/>중
		<input type="radio" name="sub${status.count}" id="8" value="하"/>하
	</td>
</c:forEach>
</table>