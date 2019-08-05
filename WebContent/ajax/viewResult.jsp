<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:forEach items="${requestScope.viewPrim }" var="dto">
	<div class="selected_recipe" id="${dto.RECIPE_ID }" onclick="">
		<img alt="" src="${dto.IMG_URL }">
		<p>${dto.RECIPE_NM_KO }</p>
	</div>
</c:forEach>

<script type="text/javascript">

$('#selected_recipe_view>div').on('click',function(){
	var selected = $(this).attr('id');
	console.log(selected);
	$.ajax({
		type:'POST',
		dataType:'text',
		data:'recipe_id='+selected,
		url:'showRecipe',
		success: function(res){
			$("#selected_recipe").html(res);
		}
	})
})
</script>
