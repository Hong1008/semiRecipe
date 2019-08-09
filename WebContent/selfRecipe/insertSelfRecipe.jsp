<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@include file="../jsp/menu.jsp"%>
<%@include file="../jsp/sideMenu.jsp" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">

<title>insertSelfRecipe</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!--웹폰트-->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100&display=swap" rel="stylesheet">

<!--sidemenu js/css  -->
<link rel="stylesheet" href="/semiRecipe/css/sideMenu.css">
<script type="text/javascript" src="/semiRecipe/js/sideMenu.js"></script>


<!-- 드래그/드랍 + 한글-->
<script src="/semiRecipe/js/plugin/jquery-ui.min.js"></script>

<!-- insertSelfTecipe js/css-->
<script type="text/javascript" src="/semiRecipe/selfRecipe/js/insertSelfRecipe.js"></script>
<link rel="stylesheet" href="/semiRecipe/selfRecipe/css/self_recipe.css">

</head>

<body onContextmenu="return false">
	
	<form name="frm" method="post" enctype="multipart/form-data" action="insertSelfRecipe">
		<c:if test="${!empty param.user_id }">
			<input type="text" name="user_id" value="${param.user_id }"/>
		</c:if>
		
		<!-- 이미지 요리이름 간략소개 -->
		<section id='SR_header' class='SR_div'>
				<ul id='SR_image'>
					<li class="image_part" ><img id='image_preview' class="image_preview" src='#' /></li>
					<li class="image_part"><input type="file" id='imgInput' name="img_url" required="required" /></li>
					<li class="image_part"><input type="text" id="recipe_nm_ko" name="recipe_nm_ko" required="required" placeholder="나만의 요리 이름을 지어주세요!" /></li>
					<li class="image_part"><textarea id="sumry" name="sumry" rows="4" cols="100" required="required" placeholder="요리를 소개해 주세요!" required="required" /></textarea></li>
				</ul>
		</section>
	
		<!-- 세부항목 -->
		<section id='SR_info' class='SR_div'>
			<!------------------ 아이디 바꿔야함 -------------->
			<div id='food_class_part'>
				<p>음식분류 </p>
				<input type="radio" name="natioin_nm" value="한식" checked="checked"> 한식&nbsp;
	  			<input type="radio" name="natioin_nm" value="중식"> 중식&nbsp;&nbsp;&nbsp;&nbsp;<br>
	  			<input type="radio" name="natioin_nm" value="일식"> 일식&nbsp;
	  			<input type="radio" name="natioin_nm" value="서양"> 양식&nbsp;&nbsp;&nbsp;&nbsp;<br>
	  			<input type="radio" name="natioin_nm" value="퓨전"> 퓨전&nbsp;
	  			<input type="radio" name="natioin_nm" value="퓨전"> 동남아&nbsp;<br>
	  			
			</div>
			
			<div id='cooking_time_part'>
				<p>조리시간</p>
				<input type="hidden" id="cooking_time" name="cooking_time" required="required"/>
				<span id="R_hour"  class='cook_time'>0</span> <span>h</span> 
				<span id="R_minute_two" class='cook_time'>0</span>	
				<span id="R_minute_one" class='cook_time'>0</span>
				<span>m</span>
			</div>
		
			<div id='SR_cal_part' >
				<input type="hidden" id="calorie" name="calorie" required="required"/>
				<p>칼로리</p>
				<span id="R_cal_four" class="R_cal">0</span> <span id="R_cal_three" class="R_cal">0</span> 
				<span id="R_cal_two" class="R_cal">0</span> <span id="R_cal_one" class="R_cal">0</span> <span>kcal</span>
			</div>
			
			<div id='level_nm_part' >
				<p>난이도 </p>
				<input type="radio" name="level_nm" value="초보환영" checked="checked"> 초보환영<br>
	  			<input type="radio" name="level_nm" value="보통"> 보통&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
	  			<input type="radio" name="level_nm" value="어려움"> 어려움&nbsp;&nbsp;&nbsp;&nbsp;<br>
			</div>
		</section>
	
		<!-- 재료 작성란 -->
		<section id='SR_importance' class='SR_div'>
			<h3>장바구니</h3>
			<div id='container' class='test'>
				<div id='main_contents'>
					<div id='selected_ing_div'>
						<span id='tt'>이곳에 재료를 끌어다 놓으세요!</span>
						<div id='selected_ing_list'></div>
					</div>
					<div id='selected_recipe_view'></div>
					<div id='selected_recipe'></div>
				</div>
			</div>
			<div id='normal_irdnt' class='R_ig_div'>
				<div class='R_ing'>
					<span></span>
					<div class='R_rate_div'>
						<div class="R_rate" id="${irdto.IMPORTANCE }"></div>
					</div>
					</div>
	
				<div id='' class='R_ig_div'>
					<div class='R_ing'>
						<span></span>
						<div class='R_rate_div'>
							<div class="R_rate" id=""></div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!-- 세부레시피 작성란 -->
		<section id='SR_recipeDiv' class='SR_div'>
			<h3>조리 순서</h3>
			<table class="self_recipeDiv_part">
				<tr>
					<th colspan="2"  class="self_recipeDiv" id="self_recipeDiv_btn">
					<input type="button" class="w_btn" id="w_add" value="추가"/>
					<input type="button" class="w_btn" id="w_del" value="삭제"/>
					</th>
				</tr>
				<tr>
					<td><span>1</span></td>
					<td><textarea class='R_recipe' rows="3" cols="100" name="recipe_dc"></textarea></td>
				</tr>
			</table>
		</section>
		
		
		
		<section id='rg_section' class='SR_div'>
		<input type="submit" class="w_btn" id="submit" value="등록"/>
		<input type="reset" class="w_btn" id="reset" value="취소"/>
		</section>
	</form>

</body>
</html>