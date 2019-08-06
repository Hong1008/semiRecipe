<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>writeSelfRecipe</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" >

$(document).ready(function() {
	var o = 0;
	var x = 0;
	var y = 0;
	var z = 0;
	var h = 0;
	var m = 0;
	/* 시간 */
	$('#R_hour').click(function() {
		if (h >= 12) {
			h = -1;
		}
		$('#R_hour').text(h += 1);
	});
	/* 분 */
	$('#R_minute').click(function() {
		if (m >= 59) {
			m = -1;
		}
		$('#R_minute').text(x += 1);
	});
	
	/* 천단위 */
	$('#R_cal_four').click(function() {
		if (o >= 9) {
			o = -1;
		}
		$('#R_cal_four').text(o += 1);
	});
	/* 백단위 */
	$('#R_cal_three').click(function() {
		if (x >= 9) {
			x = -1;
		}
		$('#R_cal_three').text(x += 1);
	});
	/* 십단위 */
	$('#R_cal_two').click(function() {
		if (y >= 9) {
			y = -1;
		}
		$('#R_cal_two').text(y += 1);
	});
	/* 일단위 */
	$('#R_cal_one').click(function() {
		if (z >= 9) {
			z = -1;
		}
		$('#R_cal_one').text(z += 1);
	});
	
	selfRecipeDivAddDel();

});
/* 세부 레시피 목록 추가 삭제 */
function selfRecipeDivAddDel(){
	var i = 1;
	$('#w_add').on('click', function(){
		if(i == 20){return};
		i++;
			$('#self_recipeDiv').append("<li>"+i+"<textarea class='R_recipe' rows='3' cols='100'></textarea></li>");
		});

	$('#w_del').on('click', function(){
		if(i==1){return};
		$("#self_recipeDiv li").last().remove();
		i--
	});
}

</script>
</head>

<body>
	<!-- 이미지 요리이름 간략소개 -->
	<section id='R_header' class='R_div'>
		<div id='R_image'>
			<img src="#">
		</div>
		<input type="text" name="self_title" id="self_tilte"
			placeholder="요리 이름을 알려주세요!" /><br />
		<textarea name="self_sumry" id="self_sumry" rows="3" cols="100">요리를 소개해 주세요!</textarea>
	</section>
	
	<!-- 세부항목 -->
	<section id='R_info' class='R_div'>
		<!------------------ 아이디 바꿔야함 -------------->
		<div id='음식분류'>
			<p>음식분류 <select name="self_food">
				<option>한식</option>
				<option>중식</option>
				<option>일식</option></select>
			</p>
		</div>
		<div id='조리시간'>
			<p>조리시간</p>
			<span id="R_hour">-</span> <span>h</span> <span id="R_minute">--</span>
			<span>m</span>
		</div>
		<div id='R_cal'>
			<p>칼로리</p>
			<span id="R_cal_four">-</span> <span id="R_cal_three">-</span> 
			<span id="R_cal_two">-</span> <span id="R_cal_one">-</span> 
			<span>kcal</span>
		</div>
		<div id='난이도'>
			<p>
				난이도 <select name="self_grade">
				<option>상</option>
					<option>중</option>
					<option>하</option></select>
			</p>

		</div>
	</section>
	
	<!-- 재료 작성란 -->
	<section id='R_importance' class='R_div'>
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
	</section>
	
	<!-- 세부레시피 작성란 -->
	<section id='R_recipeDiv' class='R_div'>
	<h3>세부 레시피</h3>
		<button class="w_btn" id="w_add" >추가</button>
		<button class="w_btn" id="w_del" >삭제</button>
		<ul id="self_recipeDiv">
		<li>1<textarea class='R_recipe' rows="3" cols="100"></textarea></li>
		
	</ul>
		
	</section>
</body>
</html>