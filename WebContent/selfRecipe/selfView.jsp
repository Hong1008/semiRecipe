<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/semiRecipe/css/recipe.css">
<link rel="stylesheet" href="/semiRecipe/selfRecipe/css/self_recipe.css">

<div id='R_header' class='R_div'>
		
	<span id='user_id'>${requestScope.srdto.user_id } </span>
	<span id='recipe_id'>${requestScope.prdto.RECIPE_ID } </span>
	<span id='self_views'><img width="14" height="14" alt="조회수" src="/semiRecipe/img/views.png"> ${requestScope.srdto.self_views } </span>
	<span id='self_date'>D:${requestScope.srdto.self_date } </span>

	<div id='R_image'>
		<img src="/semiRecipe/selfRecipe/img_self/${requestScope.prdto.IMG_URL }">
	
	</div>
	<h1>${requestScope.prdto.RECIPE_NM_KO }</h1>
	<h2>${requestScope.prdto.SUMRY }</h2>
</div>
<div id='R_info' class='R_div'>
	<!------------------ 아이디바꿔야함 -------------->
	<div id='food_class'>
		<p>음식분류</p>
		<span>${requestScope.prdto.NATION_NM }</span>
	</div>
	<div id='cook_time'>
		<p>조리시간</p>
		<span id="R_hour">${requestScope.hour }</span> <span>h</span> 
		<span id="R_minute">${requestScope.miute }</span> <span>m</span>
	</div>
	<div id='calorie'>
		<p>칼로리</p>
		<span id="R_cal">${requestScope.prdto.CALORIE }</span> <span>kcal</span>
	</div>
	<div id='grade'>
		<p>난이도</p>
		<span>${requestScope.prdto.LEVEL_NM }</span>
	</div>
</div>



<div id='R_importance' class='R_div'>
	<h3>재료 중요도</h3>
	<div id='normal_irdnt' class='R_ig_div'>
		<c:forEach items="${requestScope.irList }" var="irList">
		<div class='R_ing'>
			<span>${irList.IRDNT_NM }</span>
			<div class='R_rate_div'>
				<div class="R_rate" id="${irList.IMPORTANCE }"></div>
			</div>
		</div>
		</c:forEach>
	</div>
	<div id='seasonig' class='R_ig_div'>
	<c:forEach items="${requestScope.irList }" var="irList">
		<div class='R_ing'>
			<span>${irList.IRDNT_NM }</span>
			<div class='R_rate_div'>
				<div class="R_rate" id="${irList.IMPORTANCE }"></div>
			</div>
		</div>
		</c:forEach>
	</div>
</div>

<div id='R_recipeDiv' class='R_div'>
	<c:forEach items="${requestScope.stList }" var="stList">
		<div class='R_recipe'>
		<span class='R_num'>${stList.COOKING_NO }</span><span>${stList.COOKING_DC }</span>
		<hr>
	</div>
	</c:forEach>
</div>
