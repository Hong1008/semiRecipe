<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:forEach items="${requestScope.viewPrim }" var="dto">
	<div class="selected_recipe" id="${dto.RECIPE_ID }" onclick="">
		<img alt="" src="${dto.IMG_URL }">
		<p>${dto.RECIPE_NM_KO }</p>
	</div>
</c:forEach>

<script src="/semiRecipe/js/showRecipe.js"></script>
<script type="text/javascript">

$('#selected_recipe_view>div').on('click',function(){
	showRecipe($(this).attr('id'),"#selected_recipe");
});
</script>
