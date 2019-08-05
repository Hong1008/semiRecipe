<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>


<div id='R_header' class='R_div'>
	<div id='R_image'>
		<img src="http://file.okdab.com/UserFiles/searching/recipe/002800.jpg">
	</div>
	<h1>열무김치냉면</h1>
	<h2>한여름 더위를 싹~가시게할 시원한 여름별미 열무김치냉면!</h2>
</div>
<div id='R_info' class='R_div'>
	<!------------------ 아이디바꿔야함 -------------->
	<div id='음식분류'>
		<p>음식분류</p>
		<span>한식?</span>
	</div>
	<div id='조리시간'>
		<p>조리시간</p>
		<span id="R_hour">0</span> <span>h</span> <span id="R_minute">0</span>
		<span>m</span>
	</div>
	<div id='칼로리'>
		<p>칼로리</p>
		<span id="R_cal">0000</span> <span>kcal</span>
	</div>
	<div id='난이도'>
		<p>난이도</p>
		<span>상</span>
	</div>
</div>


<div id='R_importance' class='R_div'>
	<h3>재료 중요도</h3>
	<div id='일반재료' class='R_ig_div'>
		<div class='R_ing'>
			<span>재료1</span>
			<div class='R_rate_div'>
				<div class="R_rate"></div>
			</div>
		</div>
		<div class='R_ing'>
			<span>재료2</span>
			<div class='R_rate_div'>
				<div class="R_rate"></div>
			</div>
		</div>
		<div class='R_ing'>
			<span>재료3</span>
			<div class='R_rate_div'>
				<div class="R_rate"></div>
			</div>
		</div>
		<div class='R_ing'>
			<span>재료4</span>
			<div class='R_rate_div'>
				<div class="R_rate"></div>
			</div>
		</div>
	</div>
	<div id='양념' class='R_ig_div'>
		<div class='R_ing'>
			<span>양념1</span>
			<div class='R_rate_div'>
				<div class="R_rate"></div>
			</div>
		</div>

		<div class='R_ing'>
			<span>양념2</span>
			<div class='R_rate_div'>
				<div class="R_rate"></div>
			</div>
		</div>
	</div>
</div>

<div id='R_recipeDiv' class='R_div'>
	<div class='R_recipe'>
		<span class='R_num'>1.</span><span>일번은 이렇게합니다 야호야호하하하</span>
		<hr>
	</div>
	<div class='R_recipe'>
		<span class='R_num'>2.</span><span>이번은 이렇게합니다 우히</span>
		<hr>
	</div>
	<div class='R_recipe'>
		<span class='R_num'>3.</span><span>삼번은 이렇게합니다 에헤에헤헤헤</span>
		<hr>
	</div>
	<div class='R_recipe'>
		<span class='R_num'>4.</span><span>사번은 이렇게합니다 이이이이이렇게긴게 나올 수도 있어요</span>
		<hr>
	</div>
	<div class='R_recipe'>
		<span class='R_num'>5.</span><span>오번은 이렇게짧</span>
		<hr>
	</div>
</div>
