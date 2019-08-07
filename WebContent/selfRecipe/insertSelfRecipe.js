$(document).ready(function() {
	var o = 0;
	var x = 0;
	var y = 0;
	var z = 0;
	var h = 0;
	var m = 0;
	
	$('#R_hour').click(function() {
		if (o >= 12) {
			o = -1;
		}
		$('#R_hour').text(o += 1);
	});

	$('#R_minute').click(function() {
		if (x >= 59) {
			x = -1;
		}
		$('#R_minute').text(x += 1);
	});
	

	$('#R_cal_four').click(function() {
		if (o >= 9) {
			o = -1;
		}
		$('#R_cal_four').text(o += 1);
	});

	$('#R_cal_three').click(function() {
		if (x >= 9) {
			x = -1;
		}
		$('#R_cal_three').text(x += 1);
	});

	$('R_cal_two').click(function() {
		if (y >= 9) {
			y = -1;
		}
		$('R_cal_two').text(y += 1);
	});

	$('R_cal_one').click(function(e) {
		if (z >= 9) {
			z = -1;
		}
		$('R_cal_one').text(z += 1);
	});
	
	selfRecipeDivAdd();
	selfRecipeDivDel();
});

function selfRecipeDivAdd(){
	var i = 2;
	$('#w_add').on('click', function(){
		if(i <= 20){
			$("div.R_recipe").next().after().append("<div class='R_recipe'>"+i+"<textarea rows='3' cols='80'></textarea></div>");
			i += 1; 
		}
	});
}

function selfRecipeDivDel(){
	$('#w_del').on('click', function(){
		$('this').remove();
	});
}
