$(document).ready(function() {
	numberClick();
	selfRecipeDivAddDel(); 
	cookingTime();
	irdntDrop();
	totalCalorie();
	
	
	$("#imgInput").change(function(){
		imagePreview(this);
	});
});// end ready()


/* 이미지 업로드시 이미지 바로보이기 */
function imagePreview(input) {	
	if (input.files && input.files[0]) {		
		var reader = new FileReader();
		
		reader.onload = function(e) {
			console.log("test:"+e.target.result)
			$('#image_preview').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}





	/*칼로리*/
function totalCalorie(){
	$('.R_cal').on('mousedown', function(){
		var totalCalorie;
		var four = $('#R_cal_four').text();
		var three = $('#R_cal_three').text();
		var two = $('#R_cal_two').text();
		var one = $('#R_cal_one').text();
		totalCalorie = four + three + two + one;
		$('#calorie').val(totalCalorie);
		console.log(totalCalorie);
	});
};
	/*요리시간*/
function cookingTime(){
	$('.cook_time').on('mousedown', function(){
		var time = 0;
		if($('#R_hour').text()!='0' && $('#R_minute').text()!='0' )
		time = $('#R_hour').text()*60 +$('#R_minute').text()*1;
		$('#cooking_time').val(time);
		console.log(time);
		})	
}

	/*재료 드랍하기*/
function irdntDrop(){
	var select_num = 0;			
	$('#R_importance').droppable({				
		drop : function(event, ui) {
		select_num++;
		if (select_num < 10) {
		alert("재료추가테스트");
					}
				}
			});
};

	/* 세부 레시피 입력창 추가 삭제 */
function selfRecipeDivAddDel() {
		var i = 1;
		$('#w_add').on('click', function() {
			if (i == 20) {return};
			i++;
			$('.self_recipeDiv_part').append("<tr><td><span>"+ i +"</span></td><td><textarea class='R_recipe' rows='3' cols='100' name='recipe_dc"+i+"'></textarea></td></tr>");
					
		});
		
		$('#w_del').on('click', function() {
			if (i == 1) {return};
			$(".self_recipeDiv_part tr").last().remove();
			i--
		});
	}
	/*서브밋*/
function submitAction() {
		$('#rg_add').on('click', function(){
			$('#frm').submit();
		});	
}

	/*시간, 칼로리 클릭 업다운*/
function numberClick(){
	var o = 0;
	var x = 0;
	var y = 0;
	var z = 0;
	var h = 0;
	var m = 0;
	
	/* 시간 */
	$('#R_hour').mousedown(function(e) {
		if (e.which == 1) {
			$('#R_hour').text(h += 1);
		}
		if (h > 0) {
			if (e.which == 3) {
				$('#R_hour').text(h -= 1);
			}
		}
	});
	/* 분 */
	$('#R_minute').mousedown(function(e) {
		if(m >= 59 ) {
			m = -1;
		}
		if (e.which == 1){
			$('#R_minute').text(m += 1);
		}
		
		if (m > 0) {
			if (e.which == 3){
				$('#R_minute').text(m -= 1);
			}
		}
	});

	/* 천단위 */
	$('#R_cal_four').mousedown(function(e) {
		if (o >= 9) {
			o = -1;
		}
		if (e.which == 1) {
			$('#R_cal_four').text(o += 1);
		}
		if (o > 0) {
			if (e.which == 3) {
				$('#R_cal_four').text(o -= 1);
			}
		}
	});
	
	/* 백단위 */
	$('#R_cal_three').mousedown(function(e) {
		if (x >= 9) {
			x = -1;
		}
		if (e.which == 1) {
			$('#R_cal_three').text(x += 1);
		}
		if (x > 0) {
			if (e.which == 3) {
				$('#R_cal_three').text(x -= 1);
			}
		}
	});
	
	/* 십단위 */
	$('#R_cal_two').mousedown(function(e) {
		if (y >= 9) {
			y = -1;
		}
		if (e.which == 1) {
			$('#R_cal_two').text(y += 1);
		}
		if (y > 0) {
			if (e.which == 3) {
				$('#R_cal_two').text(y -= 1);
			}
		}
	});
	
	/* 일단위 */
	$('#R_cal_one').mousedown(function(e) {
		if (z >= 9) {
			z = -1;
		}
		if (e.which == 1) {
			$('#R_cal_one').text(z += 1);
		}
		if (z > 0) {
			if (e.which == 3) {
				$('#R_cal_one').text(z -= 1);
			}
		}
	});
	
	$('#self_tilte').on('click', function() {
		$(this).val("");
	});
	$('#self_sumry').on('click', function(){
		$(this).val("");
	})
}


		  






