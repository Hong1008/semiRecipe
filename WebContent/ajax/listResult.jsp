<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${requestScope.recList}" var="recList" varStatus="i">
	<div class="js-load" id="${recList.RECIPE_ID }">
		<a href="#"> <img src="${recList.IMG_URL}" alt="" />
			<h3>${recList.RECIPE_NM_KO}</h3>
			<input type="hidden" id="${recList.NATION_NM }" value="${recList.NATION_NM }">
			
		</a>
	</div>
</c:forEach>
<script src="/semiRecipe/js/showRecipe.js"></script>
<script type="text/javascript">
$('.thumbnails .js-load').on('click',function(){
	showRecipe($(this).attr('id'),"#selected_recipe");
});

</script>
