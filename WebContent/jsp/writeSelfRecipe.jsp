<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div id='R_header' class='R_div'>
	<div id='R_image'>
		<img src="http://file.okdab.com/UserFiles/searching/recipe/002800.jpg">
	</div>
	<input type="text" name="self_title" id="self_tilte" placeholder="요리 이름을 알려주세요!"/>
	<textarea name="self_sumry" id="self_sumry">요리를 소개해 주세요!</textarea>
</div>
<div id='R_info' class='R_div'>
	<!------------------ 아이디 바꿔야함 -------------->
	<div id='음식분류'>
		<p>음식분류 <select name="self_food"><option>한식</option><option>중식</option><option>일식</option></select></p>
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
		<p>난이도 <select name="self_grade"><option>상</option><option>중</option><option>하</option></select></p>
		
	</div>
</div>


<div id='R_importance' class='R_div'>
<h3>재료 중요도</h3>
	<div id='일반재료'  class='R_ig_div'>
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
	<div class='R_recipe'>1</div>
	<div class='R_recipe'>2</div>
	<div class='R_recipe'>3</div>
	<div class='R_recipe'>4</div>
	<div class='R_recipe'>5</div>
</div>
